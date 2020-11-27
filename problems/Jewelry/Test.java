import java.util.*;

public class Test {

	public static void main(String[] args){		
		runTest(new int[]{1,2,5,3,4,5}, 9l);
		runTest(new int[]{1,2,3,4,5}, 4l);
		runTest(new int[]{7,7,8,9,10,11,1,2,2,3,4,5,6}, 607l);
		runTest(new int[]{123,217,661,678,796,964,54,111,417,526,917,923}, 0l);
	}

	private static boolean runTest(int[] values,  long expectedResult){
		
		long res = Jewelry_StackOverflow.howMany(values);
		if(expectedResult != res){
			System.out.println("Error! Expected result: " + expectedResult + ", actual result: " + res);
			return false;
		}
		return true;
	}
}