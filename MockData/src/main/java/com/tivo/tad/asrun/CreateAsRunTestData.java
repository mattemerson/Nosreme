package com.tivo.tad.asrun;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;


/**
 * {@link https://confluence.corporate.local/display/SD/As+Run+Injest++-+Canonical+Version}
 * @author memerson
 *
 */
public class CreateAsRunTestData {

	public static void main(String[] args)
	{
		CreateAsRunTestData createAsRunTestData = new CreateAsRunTestData();

		String output = createAsRunTestData.createData();
		System.out.println(output);
	}
	
	/**
	 * no-op constructor
	 */
	public CreateAsRunTestData()
	{
	}
	
	public String createData()
	{
		ProgramAiring programAiring = loadProgramAiring();
		
		ProgramSpotFactory programSpotFactory = new ProgramSpotFactory(programAiring);
		
		AsRunSpotToCSVTransformer transformer = new AsRunSpotToCSVTransformer();
		
		List<AsRunSpot> spots = programSpotFactory.getSpots(ProgramSpotOption.TYPICAL);
		System.out.println("num spots=" + spots.size());
		
		StringBuilder builder = new StringBuilder();
		for (AsRunSpot spot : spots)
		{
			builder.append(transformer.transform(spot)).append("\n");
		}
				
		return builder.toString();
	}
	
	public enum ProgramSpotOption
	{
		TYPICAL
		{
			public ProgramConfig getSpotCreationStrategy()
			{
				List<SpotConfig> spotConfigs = Arrays.asList(new SpotConfig(30),
															new SpotConfig(30),
															new SpotConfig(30),
															new SpotConfig(30),
															new SpotConfig(30),
															new SpotConfig(30),
															new SpotConfig(30),
															new SpotConfig(30),
															new SpotConfig(30),
															new SpotConfig(30));
				BreakConfig breakConfig = new BreakConfig(600, spotConfigs);
				ProgramConfig programConfig = new ProgramConfig(breakConfig);
				
				return programConfig;
			}
		},
		PATHOLOGICAL
		{
			public ProgramConfig getSpotCreationStrategy()
			{
				List<SpotConfig> spotConfigs = Arrays.asList(new SpotConfig(30), new SpotConfig(30));
				BreakConfig breakConfig = new BreakConfig(30, spotConfigs);
				ProgramConfig programConfig = new ProgramConfig(breakConfig);
				
				return programConfig;
			}			
		};
				
		ProgramSpotOption()
		{			
		}
		
		public abstract ProgramConfig getSpotCreationStrategy();
	}
	
	
	private static class ProgramSpotFactory
	{
		private ProgramAiring programAiring;
				
		private String breakNameTemplate = "break_%d";
		private String podNameTemplate = "pod_%d";
		private String tvCreativeTemplate = "tvcreative_%d";
		
		public ProgramSpotFactory(ProgramAiring programAiring)
		{
			this.programAiring = programAiring;
		}
				
		public String getBreakName(int breakPosition)
		{
			return String.format(breakNameTemplate, breakPosition);
		}
		
		public String getPodName(int breakPosition)
		{
			return String.format(podNameTemplate, breakPosition);
		}
		
		public String getTVCreative(int totalSpotPosition)
		{
			return String.format(tvCreativeTemplate, totalSpotPosition);
		}
		
		public List<AsRunSpot> getSpots(ProgramSpotOption option)
		{
			List<AsRunSpot> spots = new ArrayList<AsRunSpot>();
			
			ProgramContext programContext = new ProgramContext();
			
			ProgramConfig programConfig = option.getSpotCreationStrategy();
			BreakConfig breakConfig = programConfig.getBreakConfig();
			
			ZonedDateTime programStartTimestamp = this.programAiring.getStartTimestamp();
			ZonedDateTime programEndTimestamp = this.programAiring.getEndTimestamp();
			
			ZonedDateTime currentTimestamp = programStartTimestamp;
			while(currentTimestamp.isBefore(programEndTimestamp))
			{

				List<SpotConfig> spotConfigs = breakConfig.getSpotConfigs();
				for (SpotConfig spotConfig : spotConfigs)
				{
					int spotDuration = spotConfig.getSpotDuration();
					
					AsRunSpot spot = newSpot(currentTimestamp, spotDuration, programContext);
					
					spots.add(spot);
					
					// This advances our position once this spot was added
					currentTimestamp = currentTimestamp.plusSeconds(spotDuration);
					
					programContext.nextPosition();
				}
				
				
				// This will insert a space between this segment and the next
				// We add another break
				programContext.nextBreak();
				int segmentWindow = breakConfig.getProgramSegmentDuration();
				currentTimestamp = currentTimestamp.plusSeconds(segmentWindow);								
			}
			
			return spots;
		}
		
		private AsRunSpot newSpot(ZonedDateTime currentTimeStamp, int spotDuration, ProgramContext programContext)
		{
			AsRunSpot spot = new AsRunSpot(programAiring.getChannelID(),
										   programAiring.getName(),
										   "externalAdvertiserName",
										   currentTimeStamp,
										   spotDuration,
										   "product",
										   getPodName(programContext.currentBreak()),
										   Integer.toString(programContext.currentPosition()),
										   getBreakName(programContext.currentBreak()),
										   "test_brand",
										   getTVCreative(programContext.programSpotPosition()));
			return spot;
		}		
		
		public class ProgramContext
		{
			int breakPosition;
			int spotPosition;
			int programSpotPosition;
			
			public ProgramContext()
			{
				breakPosition = 0;
				spotPosition = 0;
				programSpotPosition = 0;
			}
			
			public int currentBreak()
			{
				return breakPosition;
			}
			
			public int currentPosition()
			{
				return spotPosition;
			}
			
			public int programSpotPosition()
			{
				return this.programSpotPosition;
			}
			
			public void nextBreak()
			{
				breakPosition++;
				spotPosition = 0;
			}
			
			public void nextPosition()
			{
				spotPosition++;
				programSpotPosition++;
			}
		}
	}
	
	public static class ProgramConfig
	{
		private BreakConfig breakConfig;
		
		public ProgramConfig(BreakConfig config)
		{
			this.breakConfig = config;
		}
		
		public BreakConfig getBreakConfig()
		{
			return this.breakConfig;
		}
	}
	
	public static class BreakConfig
	{
		private int programSegmentDuration;
		private List<SpotConfig> spotConfigs = new ArrayList<SpotConfig>();
		
		public BreakConfig(int segmentDuration, List<SpotConfig> spotConfigs)
		{
			this.programSegmentDuration = segmentDuration;
			this.spotConfigs = spotConfigs;
		}
				
		public int getProgramSegmentDuration()
		{
			return this.programSegmentDuration;
		}
		
		public List<SpotConfig> getSpotConfigs()
		{
			return this.spotConfigs;
		}
	}
	
	public static class SpotConfig
	{
		private int spotDuration;
		
		public SpotConfig(int spotDuration)
		{
			this.spotDuration = spotDuration;
		}
		
		public int getSpotDuration()
		{
			return this.spotDuration;
		}
	}
	
	private ProgramAiring loadProgramAiring()
	{
		// Optionally, could all be loaded from Qubole
		long programAiringID = 690314691803231600L;
		int channelID = 69031469;
		String programName = "NCAA March Madness Bracket Breakdown";
		ZonedDateTime startTimestamp = ZonedDateTime.of(2018, 3, 23, 20, 0, 0, 0, TimeZone.getTimeZone("UTC").toZoneId());
		ZonedDateTime endTimestamp = ZonedDateTime.of(2018, 3, 24, 0, 0, 0, 0, TimeZone.getTimeZone("UTC").toZoneId());

		ProgramAiring programAiring = new ProgramAiring(programAiringID, channelID, programName, startTimestamp, endTimestamp);
		
		return programAiring;		
	}
	
	
	public static class ProgramAiring
	{
		private long id;
		private int channelID;
		private String name;
		private ZonedDateTime startTimestamp;
		private ZonedDateTime endTimestamp;
		
		
		public ProgramAiring(long id, int channelID, String programName, ZonedDateTime programStartTimestamp, ZonedDateTime programEndTimestamp)
		{
			this.id=id;
			this.channelID = channelID;
			this.name = programName;
			this.startTimestamp = programStartTimestamp;
			this.endTimestamp = programEndTimestamp;
		}
		
		public long getID()
		{
			return this.id;
		}
		
		public int getChannelID()
		{
			return this.channelID;
		}
		
		public String getName()
		{
			return this.name;
		}
		
		public ZonedDateTime getStartTimestamp()
		{
			return this.startTimestamp;	
		}
		
		public ZonedDateTime getEndTimestamp()
		{
			return this.endTimestamp;
		}
	}
	
	// Need a break
	// Need spots within break
	// 
	
	private interface Transform<I,O>
	{
		O transform(I i);
	}
	
	public class AsRunSpotToCSVTransformer implements Transform<AsRunSpot,String>
	{

		@Override
		public String transform(AsRunSpot spot)
		{
			if (spot == null)
			{
				return "";
			}
			
			final StringBuilder builder = new StringBuilder();
			builder.append(spot.channelID).append(",")
			       .append(appendOptional(spot.externalProgramName())).append(",")
			       .append(appendOptional(spot.externalAdvertiserName)).append(",")
			       .append(spot.spotStartTimestamp()).append(",")
			       .append(spot.spotDuration()).append(",")
			       .append(appendOptional(spot.product())).append(",")
			       .append(appendOptional(spot.pod())).append(",")
			       .append(appendOptional(spot.position())).append(",")
			       .append(appendOptional(spot.spotBreak())).append(",")
			       .append(appendOptional(spot.brand())).append(",")
			       .append(appendOptional(spot.tvCreative()));
			
			return builder.toString();
		}
		
		private String appendOptional(String value)
		{
			if (value == null)
				return "";
			else
				return value;
		}
		
	}
	
	
	public static class AsRunSpot
	{
		private int channelID;
		private String externalProgramName;
		private String externalAdvertiserName;
		private ZonedDateTime spotStartTimestamp;
		private int spotDurationInSeconds;
		private String product;
		private String pod;
		private String position;
		private String spotBreak;
		private String brand;
		private String tvCreative;	
		
		public AsRunSpot(int channelID,
						 String externalProgramName,
						 String externalAdvertiserName,
						 ZonedDateTime spotStartTimestamp,
						 int spotDurationInSeconds,
						 String product,
						 String pod,
						 String position,
						 String spotBreak,
						 String brand,
						 String tvCreative)
		{
			this.channelID = channelID;
			this.externalProgramName = externalProgramName;
			this.externalAdvertiserName = externalAdvertiserName;
			this.spotStartTimestamp = spotStartTimestamp;
			this.spotDurationInSeconds = spotDurationInSeconds;
			this.product = product;
			this.pod = pod;
			this.position = position;
			this.spotBreak = spotBreak;
			this.brand = brand;
			this.tvCreative = tvCreative;
		}
		
		public int channelID()
		{
			return this.channelID;
		}
		
		public String externalProgramName()
		{
			return this.externalProgramName;
		}
		
		public String externalAdvertiserName()
		{
			return this.externalAdvertiserName;
		}
		
		public ZonedDateTime spotStartTimestamp()
		{
			return this.spotStartTimestamp;
		}
		
		public int spotDuration()
		{
			return this.spotDurationInSeconds;
		}
		
		public String product()
		{
			return this.product;
		}
		
		public String pod()
		{
			return this.pod;
		}
		
		public String position()
		{
			return this.position;
		}
		
		public String spotBreak()
		{
			return this.spotBreak;
		}
		
		public String brand()
		{
			return this.brand;			
		}
		
		public String tvCreative()
		{
			return this.tvCreative;
		}
	}
}
