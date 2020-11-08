import java.util.*;

public class FlowerGarden{
	public int [] getOrdering (int[] height, int[] bloom, int[] wilt){
 		int N = height.length;
		Flower[] flowers = new Flower[N];
		for(int i = 0; i < N; i++) flowers[i] = new Flower(height[i],bloom[i],wilt[i]);

		Arrays.sort(flowers, (a,b) -> b.compareTo(a));	

		boolean[] visited = new boolean[N];
		int[] res = new int[N];
		int j = 0;
		for(int i = 0; i < N; i++){
			if(visited[i]) continue;
			List<Flower> group = new ArrayList<Flower>();
			findRelated(i, flowers, visited, group);
			Collections.sort(group);
			for(Flower f : group) res[j++] = f.h;
		}

		return res;
	}

	private void findRelated(int idx, Flower[] flowers, boolean[] visited, List<Flower> group){
		visited[idx] = true;
		group.add(flowers[idx]);
		for(int i = 0; i < flowers.length; i++){
			if(visited[i]) continue;
			if(flowers[idx].overlaps(flowers[i])) findRelated(i, flowers, visited, group);
		}
	}

	private class Flower implements Comparable<Object>{
		int h, b, w;
		public Flower(int h, int b, int w){
			this.h = h;
			this.b = b;
			this.w = w;
		}
		
		@Override
		public int compareTo(Object o){
			Flower that = (Flower) o;
			return Integer.compare(this.h, that.h);
		}

		public boolean overlaps(Flower f){
			return !(f.w < this.b || f.b > this.w);
		}
	}
}
