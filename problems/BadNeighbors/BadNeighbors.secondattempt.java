public class BadNeighbors{

	public int maxDonations(int[] donations){
		 // 10, 3, 2, 5, 7, 8 

		//		  0  1  2  3  4  5 
		//taken		| 10  0 12 15 19 24   
		//not taken	|  0 10 10 12 15 19<-

		//		  0  1  2  3  4  5 
		//taken		| 0  3  2  8 10 16 <-    
		//not taken	| 0  0  3  3  8 10

		int N = donations.length;
		
		int[][] dp = new int[N][2];
		dp[0][0] = donations[0];
		dp[0][1] = -donations[1];

		for(int i = 1; i < N; i++){
			dp[i][0] = dp[i-1][1]+donations[i];
			dp[i][1] = Math.max(dp[i-1][0],dp[i-1][1]);	
		}
		int resTaken = dp[N-1][1];

		dp[0][0] = dp[0][1] = 0;		
		
		for(int i = 1; i < N; i++){
			dp[i][0] = dp[i-1][1]+donations[i];
			dp[i][1] = Math.max(dp[i-1][0],dp[i-1][1]);	
		}
		int resNotTaken = Math.max(dp[N-1][0], dp[N-1][1]);

		return Math.max(resTaken, resNotTaken);		
	}
}