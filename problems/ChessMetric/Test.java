import java.util.*;

public class Test {

	public static void main(String[] args){
		
		runTest(3, new int[]{0,0}, new int[]{1,0}, 1, 1);
		runTest(3, new int[]{0,0}, new int[]{1,2}, 1, 1);
		runTest(3, new int[]{0,0}, new int[]{2,2}, 1, 0);
		runTest(3, new int[]{0,0}, new int[]{0,0}, 2, 5);
		runTest(100, new int[]{0,0}, new int[]{0,99}, 50, 243097320072600l);
	}

	private static boolean runTest(int m, int[] start, int[] end, int moves, long expectedResult){
		ChessMetric instance = new ChessMetric(m);
		long[][][] memo = new long[m][m][moves+1];
		long res = instance.countWays(start, end, moves, memo);
		if(expectedResult != res){
			System.out.println("Error for: {" + Arrays.toString(start) + "},{" + Arrays.toString(end) + "} with " + moves + " moves.");

			System.out.println("Expected result: " + expectedResult + ", actual result: " + res);
			return false;
		}
		return true;
	}
}