import java.util.*;

public class KiloManX{
	
	public int leastShots(String[] damageChart, int[] bossHealth){
		
		int N = damageChart.length;
		boolean[] visited = new boolean[(int)Math.pow(2,N)];
		
		int[][] chart = new int[N][N];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				chart[i][j] = damageChart[i].charAt(j)-'0';
		
		PriorityQueue<Node> queue = new PriorityQueue<>(11, (a,b) -> a.hits - b.hits);
		queue.add(new Node(0,0));
		
		while(!queue.isEmpty()){
			Node node = queue.poll();
			if(node.weapons == Math.pow(2,N)-1) return node.hits;

			if(visited[node.weapons]) continue;
			visited[node.weapons] = true;
			
			for(int i = 0; i < N; i++){
				int minHits = bossHealth[i];
				if(((node.weapons >> i) & 1) == 1) continue;
				for(int j = 0; j < N; j++){
					if(i == j) continue;
					if(((node.weapons >> j) & 1) == 1 && chart[j][i] != 0) { //we have the weapon and it does damage to this boss
						int hits = bossHealth[i]/chart[j][i];
						double residue = bossHealth[i] % chart[j][i];
						if(residue > 0) hits++;
						minHits = Math.min(minHits, hits);
					}					
				}
				Node next = new Node((node.weapons | (1 << i)),node.hits+minHits);
				queue.add(next);
			}
			
		}
		return 0;				
	}

	private class Node{
		public int weapons, hits;

		public Node(int w, int s){
			weapons = w;
			hits = s;
		}
	}
}