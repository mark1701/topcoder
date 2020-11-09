import java.util.*;

public class Test {

	public static void main(String[] args){		
		int[][] m1 = new int[15][15];
		for(int[] m : m1) Arrays.fill(m,1);

		m1[0][1] = 2;
		m1[1][0] = 3;
		m1[4][1] = 3;
		runTest(m1, 33);		
	}

	private static boolean runTest(int[][] input, int expectedResult){
		MaxApples instance = new MaxApples();
		int res = instance.findBottomUp(input);
		if(expectedResult != res){
			System.out.println("Error for: " + Arrays.toString(input));

			System.out.println("expected result: " + expectedResult + ", actual result: " + res);
			return false;
		}
		return true;	
	}
}