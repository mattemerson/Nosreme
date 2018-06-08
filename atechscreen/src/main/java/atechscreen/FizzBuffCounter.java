package atechscreen;

public class FizzBuffCounter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * For count is 1,100
		 */
		for (int ii=1;ii<=100;ii++)
		{
			if (((ii % 3)==0) && ((ii % 5) == 0))
			{
				System.out.println("FizzBuzz");				
			}
			else if ((ii%3)==0)
			{
				System.out.println("Fizz");
			}
			else if ((ii%5)==0)
			{
				System.out.println("Buzz");
			}
			else
			{
				System.out.println(ii);
			}
		}
	}
}
