import java.util.*;

public class Test {

	public static void main(String[] args){
		runTest(6, 6, new String[]{"0 0 0 1","6 6 5 6"}, 252);
		runTest(1, 1, new String[]{}, 2);
		runTest(2, 2, new String[]{"0 0 1 0", "1 2 2 2", "1 1 2 1"}, 0);
		runTest(35, 31, new String[]{}, 6406484391866534976L);
	}

	private static boolean runTest(int input0, int input1, String[] input2, long expectedResult){
		AvoidRoads instance = new AvoidRoads();
		long res = instance.numWays(input0, input1, input2);
		if(expectedResult == res)
			return true;
		else{
			System.out.println("Error for: " + input0);

			System.out.println("expected result: " + expectedResult + ", actual result: " + res);
			return false;
		}
	}
}