import java.util.*;

public class Jewelry{

	public static long howMany(int[] values){ 		
		return countWays(0, values, Integer.MAX_VALUE, 0, 0, 0);
	}

	private static long countWays(int idx, int[] values, int minF, int maxB, int sumF, int sumB){

		if(idx == values.length) {
			if(minF < maxB) return 0;
			if(sumF == sumB && sumF > 0) return 1;
			else return 0;
		}

		long waysF = countWays(idx+1, values, Math.min(minF, values[idx]), maxB, sumF+values[idx], sumB);
		long waysB = countWays(idx+1, values, minF, Math.max(maxB, values[idx]), sumF, sumB+values[idx]);
		long waysNone = countWays(idx+1, values, minF, maxB, sumF, sumB);

		return waysF + waysB + waysNone;
	}
}
