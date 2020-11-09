import java.util.*;

public class Roads{

	private static Set<String> blocks = new HashSet<>();

	public static long countRoads(int w, int h, String[] forbidden){

		for(String s : forbidden) blocks.add(s);

		long[][] dp = new long[w+2][h+2];
		
		for(int i = w; i >= 0; i--){
			for(int j = h; j >= 0; j--){
				if(i == w && j == h) dp[i][j]=1;
				else {
					long top = !isBlocked(i,j,i+1,j) ? dp[i+1][j] : 0;
					long right= !isBlocked(i,j,i,j+1) ? dp[i][j+1] : 0;
					dp[i][j] = top + right;
 				}
			}
		}
		return dp[0][0];
	}

	
	private static boolean isBlocked(int i1, int j1, int i2, int j2){
		return blocks.contains(i1 + " " + j1 + " " + i2 + " " + j2) || blocks.contains(i2 + " " + j2 + " " + i1 + " " + j1);
	}
}