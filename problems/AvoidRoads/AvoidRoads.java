import java.util.*;

public class AvoidRoads{

	public static long numWays(int width, int height, String[] badBlocks){

		int N = width;
		int M = height;

		Set<String> bad = parseBadBlocks(badBlocks, N, M);
		
		long[][] dp = new long[N+1][M+1];

		for(int i = N; i >= 0; i--){
			for(int j = 0; j <= M; j++){
				if(i == N && j == 0)
					dp[i][j] = 1;
				else if(i == N)					
					dp[i][j] = bad.contains(i+":"+j+","+i+":"+(j-1)) ? 0 : dp[i][j-1];				
				else if(j == 0)	
					dp[i][j] = bad.contains(i+":"+j+","+(i+1)+":"+j) ? 0 : dp[i+1][j];				
				else
					dp[i][j] = (bad.contains(i+":"+j+","+(i+1)+":"+j) ? 0 : dp[i+1][j]) + (bad.contains(i+":"+j+","+i+":"+(j-1)) ? 0 : dp[i][j-1]);
				
			}
		}
		return dp[0][M];
	}

	private static Set<String> parseBadBlocks(String[] blocks, int N, int M){
		Set<String> bad = new HashSet<>();

		for(String b : blocks){
			int idx = b.indexOf(" ");
			int val = Integer.parseInt(b.substring(0, idx));
			String x = (N - val) + ""; //normalise the number since we are using 0,0 in position N,0
			
			int idx2 = b.indexOf(" ", idx+1);
			val = Integer.parseInt(b.substring(idx+1,idx2));
			String y = (val) + "";
			
			idx = b.indexOf(" ", idx2+1);
			val = Integer.parseInt(b.substring(idx2+1, idx));
			String x1 = (N - val) + "";

			val = Integer.parseInt(b.substring(idx+1));
			String y1 = (val) + "";
			
			bad.add(x+":"+y+","+x1+":"+y1);
			bad.add(x1+":"+y1+","+x+":"+y);
		}

		return bad;
	}
}
