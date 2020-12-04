import java.util.*;

public class ChessMetric{

	private static int[][] possibleMoves = new int[][]{{-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-2}, {-2,-1}, {-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}};
	private static int N;

	public ChessMetric(int n){
		this.N = n;
	}

	public static long countWays(int[] start, int[] end, int totMoves, long[][][] memo){
 	
		if(start[0] == end[0] && start[1] == end[1] && totMoves == 0) return 1;
		else if(totMoves == 0) return 0;

		if(memo[start[0]][start[1]][totMoves] >= 1) return memo[start[0]][start[1]][totMoves]-1;
		long result = 0;
		for(int[] move : possibleMoves){
			int destX = start[0] + move[0];
			int destY = start[1] + move[1];
		
			if(destX < 0 || destX >= N || destY < 0 || destY >= N) continue; //invalid landing position
			result += countWays(new int[]{destX, destY}, end,totMoves-1, memo);			
		}
		memo[start[0]][start[1]][totMoves] = result+1;
		return result;
	}
}
