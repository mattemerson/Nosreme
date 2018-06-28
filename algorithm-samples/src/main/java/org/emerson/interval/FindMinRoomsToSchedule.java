package org.emerson.interval;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Actually two: two flavors of findMinRooms to schedule and one of finding an overlap.
 * findMinRooms is fundamentally O(nlogn) (although my first attempt was n^2) using a priority queue.
 * my room overlap was O(nlogn) because I had to sort it followed by an O(n) process to check if overlapping rooms overlapped 
 * @link http://blog.gainlo.co/index.php/2016/07/12/meeting-room-scheduling-problem/
 */
public class FindMinRoomsToSchedule {

	public static void main(String[] args)
	{
		FindMinRoomsToSchedule scheduler = new FindMinRoomsToSchedule();
		
		//List<IntMeeting> meetings = scheduler.loadOverlapMeetings();
		List<IntMeeting> meetings = scheduler.loadNonOverlapMeetings();
		
		boolean doTheMeetingsOverlap = scheduler.doTheMeetingsOverlap(meetings);
		
		int minRooms = scheduler.findMinRoomsToSchedule(meetings);
		int minRooms2 = scheduler.findMinRoomsToScheduleSecondAttempt(meetings);
		
		System.out.println("for meetings=(" + meetings + ")");
		System.out.println("do the meetings overlap='" + doTheMeetingsOverlap + "'");
		System.out.println("minRooms='" + minRooms + "'");
		System.out.println("minRooms2='" + minRooms2 + "'");
	}

	/**
	 * The time complexity of sorting is O(NlogN) and the checking overlap is O(N). So overall it’s O(NlogN).
	 * 
	 * Sort, followed by inorder enumeration
	 * @param meetings
	 * @return
	 */
	private boolean doTheMeetingsOverlap(List<IntMeeting> meetings)
	{
		Comparator<IntMeeting> startComparator = (a,b) -> { return a.getStart() - b.getEnd(); };
		
		// If there are no meetings or only meeting, there can be no overlap
		if ( (meetings == null) ||
		     (meetings.isEmpty()) ||
		     (meetings.size() < 2) )
		{
			return false;
		}
		
		// Under them by startDate
		Collections.sort(meetings, startComparator);
		
		// And then check each meeting in order
		for (int ii=0;ii<(meetings.size() -1);ii++)
		{
			IntMeeting firstMeeting = meetings.get(ii);
			IntMeeting secondMeeting = meetings.get(ii+1);
			if (secondMeeting.getStart() < firstMeeting.getEnd())
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This orders the meetings in reverse by end date (nlogn)
	 * Then it inserts the start date into the queue (ordeer by start time descending)
	 * Pre-populate
	 * Go through the list of meetings in reverse
	 * 
	 * @param meetings
	 * @return
	 */
	private int findMinRoomsToScheduleSecondAttempt(List<IntMeeting> meetings)
	{
		// If there are no meetings or only meeting, there can be no overlap
		if ( (meetings == null) ||
		     (meetings.isEmpty()) )
		{
			return 0;
		}
		else if (meetings.size() == 1)
		{
			return 1;
		}
		else
		{
			// Sort in reverse order by endtime
			Collections.sort(meetings, (a,b) -> { return b.getEnd() - a.getEnd(); } );
			//System.out.println(meetings);			
			
			PriorityQueue<Integer> startDateQueue = new PriorityQueue<Integer>((a,b) -> { return b-a; });
			int minRoom = 0;
			
			// Loop over all the meetings
			for (int ii=0;ii<meetings.size();ii++)
			{
				// Get the meeting to consider
				IntMeeting currentMeeting = meetings.get(ii);
				
				// And get its start and end dates
				int currentStart = currentMeeting.getStart();
				int currentEnd = currentMeeting.getEnd();
				
				// See what's in the queue; if it's not empty and the top of the queue is greater than the current end
				while (!startDateQueue.isEmpty() &&
						startDateQueue.peek() >= currentEnd)
				{
					// We know there's nothing after the current end, so since we have passed it in the queue, remove it.
					startDateQueue.poll();
				}

				// Add the current start to the queue for the next iteration
				startDateQueue.offer(currentStart);
				
				// Our minRoom is equal to the size of the queue at this time 
				minRoom = Math.max(minRoom, startDateQueue.size());	
			}
			
			return minRoom;			
		}
	}
	
	/**
	 * Option
	 * #1: This method.  This sorts things, and then enumerates what's left.  O(n^2); not the best
	 * #2: Another option for a bounded set of space time is to create an array and then increment the minimal time component.  The largest time component is the minimum number of meeting rooms, O(n-ish)
	 * #3: 
	 * @param meetings
	 * @return
	 */
	private int findMinRoomsToSchedule(List<IntMeeting> meetings)
	{
		int minRoom;
		
		// If no meetings, we don't need rooms
		if ( (meetings == null) || (meetings.isEmpty()) )
		{
			minRoom = 0;
		}
		// If there is only 1, we will need only 1 room
		else if (meetings.size() == 1)
		{
			minRoom = 1;
		}
		else
		{	
			minRoom = 0;
			// Sorts the meeting in descending order
			List<IntMeeting> sortedMeetings = meetings.stream().sorted((a,b)-> { return (b.getEnd() - a.getEnd());}).collect(Collectors.toList());

			Set<IntMeeting> scheduledMeetings = new HashSet<IntMeeting>();
			
			// Add the first meeting to our hash and increment the mininum room count by 1
			scheduledMeetings.add(sortedMeetings.get(0));
			minRoom++;
			
			// Iterate over the rest of the rooms, process each meeting...if they overlap with more 
			for (int ii=1;ii<sortedMeetings.size();ii++)
			{
				int numberOfOverlaps = 0;
				IntMeeting currentMeeting = sortedMeetings.get(ii);
				for (IntMeeting possibleOverlap : scheduledMeetings)
				{
					if (currentMeeting.overlapsWith(possibleOverlap))
					{
						numberOfOverlaps++;
						if (numberOfOverlaps >= minRoom)
						{
							minRoom++;
							break;
						}
					}
				}				
				scheduledMeetings.add(currentMeeting);
			}			
		}
		
		return minRoom;
	}
	
	private List<IntMeeting> loadNonOverlapMeetings()
	{		
		List<IntMeeting> meetings = new ArrayList<IntMeeting>();
		meetings.add(new IntMeeting(1,2));
		meetings.add(new IntMeeting(2,3));
		meetings.add(new IntMeeting(3,4));
		meetings.add(new IntMeeting(4,5));
		
		return meetings;		
	}
	
	private List<IntMeeting> loadOverlapMeetings()
	{
		//(1, 4), (5, 6), (8, 9), (2, 6).
		List<IntMeeting> meetings = new ArrayList<IntMeeting>();
		/*meetings.add(new IntMeeting(1,4));
		meetings.add(new IntMeeting(5,6));
		meetings.add(new IntMeeting(8,9));
		meetings.add(new IntMeeting(2,6));*/
		
		meetings.add(new IntMeeting(1,7));
		meetings.add(new IntMeeting(2,4));
		meetings.add(new IntMeeting(4,6));
		meetings.add(new IntMeeting(3,4));
		meetings.add(new IntMeeting(3,4));
		
		return meetings;
	}
}
