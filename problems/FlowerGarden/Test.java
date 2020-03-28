import java.util.*;

public class Test {

	public static void main(String[] args){
		
		runTest(new int[]{5,4,3,2,1}, new int[]{1,1,1,1,1}, new int[]{365,365,365,365,365}, new int[] { 1,  2,  3,  4,  5 });
		runTest(new int[]{5,4,3,2,1}, new int[]{1,5,10,15,20}, new int[]{4,9,14,19,24}, new int[] { 5,  4,  3,  2,  1 });
		runTest(new int[]{5,4,3,2,1}, new int[]{1,5,10,15,20}, new int[]{5,10,15,20,25}, new int[] { 1,  2,  3,  4,  5 });
		runTest(new int[]{5,4,3,2,1}, new int[]{1,5,10,15,20}, new int[]{5,10,14,20,25}, new int[] { 3,  4,  5,  1,  2 });
		runTest(new int[]{1,2,3,4,5,6}, new int[]{1,3,1,3,1,3}, new int[]{2,4,2,4,2,4}, new int[] { 2,  4,  6,  1,  3,  5 });
		runTest(new int[]{3,2,5,4}, new int[]{1,2,11,10}, new int[]{4,3,12,13}, new int[] { 4,  5,  2,  3 });
	}

	private static boolean runTest(int[] input0, int[] input1, int[] input2, int[] expectedResult){
		FlowerGarden instance = new FlowerGarden();
		int[] res = instance.getOrdering(input0, input1, input2);
		if(expectedResult[0] == res[0])
			return true;
		else{
			System.out.println("Error for: " + Arrays.toString(input0));

			System.out.println("expected result: " + Arrays.toString(expectedResult) + ", actual result: " + Arrays.toString(res));
			return false;
		}
	}
}