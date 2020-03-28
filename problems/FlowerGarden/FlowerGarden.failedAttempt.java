import java.util.*;

public class FlowerGarden{
	public int[] getOrdering(int[] height, int[] bloom, int[] wilt){
		
		int N =  height.length;

		Flower[] flowers = new Flower[N];
		for(int i = 0; i < N; i++)
			flowers[i] = new Flower(height[i], bloom[i], wilt[i]);
		
		int[] dp = new int[N];
		Arrays.fill(dp, -1);
		
		boolean[] visited = new boolean[N];

		for(int i = 0; i < N; i++){
			if(!visited[i]){				
				findPre(flowers, visited, i, dp);				
			}
		}
		
		return dp;
		
	}

	private int findPre(Flower[] flowers, boolean[] visited, int idx, int[] dp){
		if(dp[idx] > -1) return dp[idx];

		visited[idx] = true;

		for(int i = 0; i < flowers.length; i++){
			if(i == idx) continue;
			if(flowers[i].compareTo(flowers[idx]) < 0)
				dp[idx] += findPre(flowers, visited, i,dp) + 1;
		}

		return dp[idx];
	}

	class Flower implements Comparable<Flower> {
		int height;
		int bloom;
		int wilt;

		public Flower(int h, int b, int w){
			height = h;
			bloom = b;
			wilt = w;
		}


		public int compareTo(Flower that){
			if (that.bloom > this.wilt || that.wilt < this.bloom)
				return that.height - this.height; // this flower is not blocking the other so, I come first only if my height is bigger 				
			else
			 	return this.height - that.height; // this flower is blocking the other so, I  come first only if my height is smaller	
		}
	}
}