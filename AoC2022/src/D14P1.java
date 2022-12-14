import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class D14P1 {
	private static class Pair {
		int a;
		int b;
		
		public Pair(String s) {
			int[] x = toInt(s.split(","));
			a = x[0];
			b = x[1];
		}
		
		public Pair(int x, int y) {
			a = x;
			b = y;
		}
		
		public String toString() {
			return a + "," + b;
		}
	}
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-14.txt");

		ArrayList<String> lines = new ArrayList<String>();
		
		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				lines.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// turn it into walls
		ArrayList<ArrayList<Pair>> walls = new ArrayList<ArrayList<Pair>>();
		for (String s : lines) {
			String[] parse = s.split(" -> ");
			ArrayList<Pair> curr = new ArrayList<Pair>();
			for (String p : parse) {
				curr.add(new Pair(p));
			}
			walls.add(curr);
		}
		
		// get grid bounds
		int xMax = 0;
		int xMin = Integer.MAX_VALUE;
		int yMax = 0;
		for (ArrayList<Pair> wall: walls) {
			for (Pair p : wall) {
				xMax = Math.max(xMax, p.a);
				xMin = Math.min(xMin, p.a);
				yMax = Math.max(yMax, p.b);
			}
		}
		
		// fill with empty
		char[][] grid = new char[yMax + 1][xMax - xMin + 1];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = '.';
			}
		}
		
		// add walls to grid
		for (ArrayList<Pair> wall : walls) {
			for (int i = 0; i < wall.size() - 1; i++) {
				Pair f = wall.get(i);
				Pair b = wall.get(i + 1);
				if (f.a == b.a) {
					int min = Math.min(f.b, b.b);
					int max = Math.max(f.b, b.b);
					for (int j = min; j <= max; j++) {
						grid[j][f.a - xMin] = '#';
					}
				} else {
					int min = Math.min(f.a, b.a);
					int max = Math.max(f.a, b.a);
					for (int j = min; j <= max; j++) {
						grid[f.b][j - xMin] = '#'; 
					}
				}
			}
		}

		// drop sand
		while (true) {
			Pair curr = new Pair(500 - xMin, 0);
			// move a single sand particle
			while (true) {
				boolean moved = false;
				boolean out = false;
				try {
					if (grid[curr.b + 1][curr.a] == '.') {
						moved = true;
						curr.b++;
					} 
				} catch (IndexOutOfBoundsException e) {out = true; curr.b++;}

				try {
					if (!moved && !out && grid[curr.b + 1][curr.a - 1] == '.') {
						moved = true;
						curr.b++;
						curr.a--;
					} 
				} catch (IndexOutOfBoundsException e) {out = true; curr.b++; curr.a--;}

				try {
					if (!moved && !out && grid[curr.b + 1][curr.a + 1] == '.') {
						moved = true;
						curr.b++;
						curr.a++;
					} 
				} catch (IndexOutOfBoundsException e) {out = true; curr.b++; curr.a++;}
				if (!moved || out) {
					break;
				}
			}
			try {
				grid[curr.b][curr.a] = 'o'; 
				ans++;
			} catch (IndexOutOfBoundsException e) {
				return ans;
			}
		}
	}
	
	public static int[] toInt(String[] s) {
		int[] ret = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			ret[i] = Integer.parseInt(s[i]);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(ans());
	}
}
