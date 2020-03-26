import java.util.*;

public class ZigZag{

	public int longestZigZag(int[] sequence){
		
		int N = sequence.length;
		int[][] dp = new int[N][2];

		int maxLen = 1;

		//base case
		dp[0][0] = 1;
		dp[0][1] = 0;

		for(int i = 1; i < N; i++){
			for(int j = 0; j < i; j++){		
				int diff = sequence[i] - sequence[j];
				if(j == 0 || diff * dp[j][1] < 0){
					boolean longer = dp[j][0] + 1 > dp[i][0];
					if(longer) {
						dp[i][0] = dp[j][0] + 1;
						dp[i][1] = Integer.signum(diff); 
						maxLen = Math.max(maxLen, dp[i][0]);
					}
	 
				}
			}
		}

		return maxLen;
	}

	//this is a O(n) implementation taken from the topcoder discussion forum
	public int longestZigZag2(int[] sequence) {
		ArrayList<Integer> seq = new ArrayList<>();

    		for (int item: sequence) {
        		seq.add((Integer)item);
    		}

		int n = seq.size();
		if(n==1) return 1;
		int i=1, prev=seq.get(0);
		while(i<n && seq.get(i) == prev)
			i++;
		if(i==n) return 1;
		Boolean b=false;
		int len=2;
		if(seq.get(i)-prev >0) b=true;
		i+=1;
		for(;i<n;i++){
			int s=seq.get(i)-seq.get(i-1);
			if(b==false && s>0){
				b=true; len++;
			}
			else if(b==true && s<0){
				b=false; len++;
			}
		}
		return len;
	}

}