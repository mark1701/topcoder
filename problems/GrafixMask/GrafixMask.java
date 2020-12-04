import java.util.*;

public class GrafixMask {
	
	private int[][] dirs = new int[][] {{0,-1},{-1,0},{0,1},{1,0}};
	
	public int[] sortedAreas(String[] masks){
		
		int M = 400, N = 600;
		boolean[][] grid = new boolean[M][N];
		
		for(String mask : masks) appyMask(grid, mask);
		
		List<Integer> areas = new ArrayList<>();
		
		for(int i = 0; i < M; i++){
			for(int j = 0; j < N; j++){
				if(grid[i][j]) continue;
				areas.add(paint(grid, i, j));
			}
		}
		
		int[] result = new int[areas.size()];
		for(int i = 0; i < areas.size(); i++) result[i] = areas.get(i);

		Arrays.sort(result);
		return result;
	}
	
	private int paint(boolean[][] grid, int startX, int startY){
		
		Deque<Integer[]> stack = new ArrayDeque<>();
		stack.push(new Integer[] {startX, startY});
		int area = 0;
		
		while(!stack.isEmpty()){
			Integer[] cell = stack.pop();
			if(grid[cell[0]][cell[1]]) continue;			
			for(int[] dir : dirs){
				int x = cell[0] + dir[0];
				int y = cell[1] + dir[1];
				if(valid(x,y) && !grid[x][y]) stack.push(new Integer[]{x,y});			
			}
			grid[cell[0]][cell[1]] = true;
			area++;
		}
		
		return area;
	}
	
	private boolean valid(int x, int y){
		if(x < 0 || x >= 400) return false;
		if(y < 0 || y >= 600) return false;
		return true;
	}	
	
	private void appyMask(boolean[][] grid, String mask){
		String[] coordinates = mask.split(" ");
		int topX = Integer.parseInt(coordinates[0]);
		int topY = Integer.parseInt(coordinates[1]);
		int bottomX = Integer.parseInt(coordinates[2]);
		int bottomY = Integer.parseInt(coordinates[3]);
		
		for(int i = topX; i <= bottomX; i++)
			for(int j = topY; j <= bottomY; j++)
				grid[i][j] = true;
	}
}