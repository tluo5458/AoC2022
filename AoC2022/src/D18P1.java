import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class D18P1 {
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
		
		boolean[][][] grid = new boolean[maxX + 1][maxY + 1][maxZ + 1];
		for (String s : lines) {
			int[] coords = toInt(s.split(","));
			grid[coords[0]][coords[1]][coords[2]] = true;
			ans += 6;
		}
		for (int i = 0; i <= maxX; i++) {
			for (int j = 0; j <= maxY; j++) {
				for (int k = 0; k < maxZ; k++) {
					if (grid[i][j][k] && grid[i][j][k + 1]) {
						ans -= 2;
					}
				}
			}
		}
		for (int i = 0; i <= maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				for (int k = 0; k <= maxZ; k++) {
					if (grid[i][j][k] && grid[i][j + 1][k]) {
						ans -= 2;
					}
				}
			}
		}
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j <= maxY; j++) {
				for (int k = 0; k <= maxZ; k++) {
					if (grid[i][j][k] && grid[i + 1][j][k]) {
						ans -= 2;
					}
				}
			}
		}
		return ans;
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
