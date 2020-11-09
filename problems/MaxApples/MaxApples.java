import java.util.*;

public class MaxApples{	
	public int findTopDown(int[][] grid, int i, int j){
		int M = grid.length, N = grid[0].length;
		
		if(i == M-1 && j == N-1) return grid[i][j];
		else if(i == M-1) return grid[i][j] + findTopDown(grid, i, j+1);
		else if(j == N-1) return grid[i][j] + findTopDown(grid, i+1,j); 
		else return grid[i][j] + Math.max(findTopDown(grid, i+1,j),findTopDown(grid,i,j+1));
	}

	public int findBottomUp(int[][] grid){
		int M = grid.length, N = grid[0].length;
		
		int[][] dp = new int[M+1][N+1];
		for(int i = 1; i < dp.length; i++)
			for(int j = 1; j < dp[0].length; j++)
				dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) + grid[i-1][j-1];			
		
		return dp[M][N];
	}	
}