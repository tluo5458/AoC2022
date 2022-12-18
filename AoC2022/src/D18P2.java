import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class D18P2 {
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-18.txt");

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
		
		int maxX = 0;
		int maxY = 0;
		int maxZ = 0;
		for (String s : lines) {
			int[] coords = toInt(s.split(","));
			maxX = Math.max(maxX, coords[0]);
			maxY = Math.max(maxY, coords[1]);
			maxZ = Math.max(maxZ, coords[2]);
		}
		maxX += 2;
		maxY += 2;
		maxZ += 2;
		
		char[][][] grid = new char[maxX + 1][maxY + 1][maxZ + 1];
		for (int i = 0; i <= maxX; i++) {
			for (int j = 0; j <= maxY; j++) {
				for (int k = 0; k <= maxZ; k++) {
					grid[i][j][k] = '.';
				}
			}
		}
		
		for (String s : lines) {
			int[] coords = toInt(s.split(","));
			grid[coords[0] + 1][coords[1] + 1][coords[2] + 1] = 'r';
		}
		
		int[][] direcs = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
		
		// bfs the outside
		HashSet<Integer> visited = new HashSet<Integer>();
		visited.add(0);
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		q.add(0);
		while (!q.isEmpty()) {
			int curr = q.pop();
			int[] coord = unhash(curr);
			grid[coord[0]][coord[1]][coord[2]] = 'o';
			for (int[] d : direcs) {
				int[] newCoord = add(coord, d);
				if (!visited.contains(hash(newCoord))) {
					try {
						if (grid[newCoord[0]][newCoord[1]][newCoord[2]] == '.') {
							visited.add(hash(newCoord));
							q.add(hash(newCoord));
						}
					} catch (Exception e) {}
				}
			}
		}
		
		for (int i = 0; i <= maxX; i++) {
			for (int j = 0; j <= maxY; j++) {
				for (int k = 0; k < maxZ; k++) {
					if (grid[i][j][k] == 'o' && grid[i][j][k + 1] == 'r') {
						ans++;
					}
					if (grid[i][j][k] == 'r' && grid[i][j][k + 1] == 'o') {
						ans++;
					}
				}
			}
		}
		for (int i = 0; i <= maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				for (int k = 0; k <= maxZ; k++) {
					if (grid[i][j][k] == 'r' && grid[i][j + 1][k] == 'o') {
						ans++;
					}
					if (grid[i][j][k] == 'o' && grid[i][j + 1][k] == 'r') {
						ans++;
					}
				}
			}
		}
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j <= maxY; j++) {
				for (int k = 0; k <= maxZ; k++) {
					if (grid[i][j][k] == 'r' && grid[i + 1][j][k] == 'o') {
						ans++;
					}
					if (grid[i][j][k] == 'o' && grid[i + 1][j][k] == 'r') {
						ans++;
					}
				}
			}
		}
		return ans;
	}
	
	public static int[] add(int[] a, int[] b) {
		int[] ret = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			ret[i] = a[i] + b[i];
		}
		return ret;
	}
	
	public static int hash(int a, int b, int c) {
		return 1000000 * a + 1000 * b + c;
	}
	
	public static int hash(int[] a) {
		return 1000000 * a[0] + 1000 * a[1] + a[2];
	}
	
	public static int[] unhash(int a) {
		return new int[] {a / 1000000, (a / 1000) % 1000, a % 1000};
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
