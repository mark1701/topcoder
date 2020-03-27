import java.util.*;

public class BadNeighbors{
	
	public int maxDonations(int[] donations){
		int takeFirst = findDonations(donations, 0, donations.length-2);
		int doNotTakeFirst = findDonations(donations, 1, donations.length-1);
		return Math.max(takeFirst, doNotTakeFirst);
	}

	private int findDonations(int[] donations, int start, int end){		
		int N = end-start+1;
		int[] dp = new int[N];

		dp[0] = donations[start];
		if(N==1) return dp[0];

		dp[1] = Math.max(dp[0], donations[start+1]);
		if(N==2) return dp[1];

		for(int i = 2; i < N; i++)
			dp[i] = Math.max(dp[i-1], donations[start+i] + dp[i-2]);	

		return dp[N-1];		
	}	
}