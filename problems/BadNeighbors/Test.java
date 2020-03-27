import java.util.*;

public class Test {

	public static void main(String[] args){
		
		runTest(new int[]{10, 3, 2, 5, 7, 8}, 19);
		runTest(new int[]{11, 15}, 15);
		runTest(new int[]{7, 7, 7, 7, 7, 7, 7 }, 21);
		runTest(new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5}, 16);
		runTest(new int[]{94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72}, 2926);
	}

	private static boolean runTest(int[] input, int expectedResult){
		BadNeighbors instance = new BadNeighbors();
		int res = instance.maxDonations(input);
		if(expectedResult == res)
			return true;
		else{
			System.out.println("Error for: " + Arrays.toString(input));

			System.out.println("expected result: " + expectedResult + ", actual result: " + res);
			return false;
		}
	}
}