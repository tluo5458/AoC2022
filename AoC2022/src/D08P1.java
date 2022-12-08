import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class D08P1 {
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-08.txt");

		BufferedReader br;
		String st;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				lines.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		char[][] grid = new char[lines.size()][lines.get(0).length()];
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(0).length(); j++) {
				grid[i][j] = lines.get(i).charAt(j);
			}
		}
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(0).length(); j++) {
				// up
				boolean temp = true;
				for (int k = i - 1; k >= 0; k--) {
					if (grid[k][j] >= grid[i][j]) {
						temp = false;
						break;
					}
				}
				if (temp) {
					ans++;
					continue;
				}
				// down
				temp = true;
				for (int k = i + 1; k < grid.length; k++) {
					if (grid[k][j] >= grid[i][j]) {
						temp = false;
						break;
					}
				}
				if (temp) {
					ans++;
					continue;
				}
				// right
				temp = true;
				for (int k = j + 1; k < grid[0].length; k++) {
					if (grid[i][k] >= grid[i][j]) {
						temp = false;
						break;
					}
				}
				if (temp) {
					ans++;
					continue;
				}
				// left
				temp = true;
				for (int k = j - 1; k >= 0; k--) {
					if (grid[i][k] >= grid[i][j]) {
						temp = false;
						break;
					}
				}
				if (temp) {
					ans++;
					continue;
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
