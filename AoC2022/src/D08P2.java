import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class D08P2 {
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
				int prod = 1;
				// up
				int temp = 0;
				for (int k = i - 1; k >= 0; k--) {
					temp++;
					if (grid[k][j] >= grid[i][j]) {
						break;
					}
				}
				prod *= temp;
				// down
				temp = 0;
				for (int k = i + 1; k < grid.length; k++) {
					temp++;
					if (grid[k][j] >= grid[i][j]) {
						break;
					}
				}
				prod *= temp;
				// right
				temp = 0;
				for (int k = j + 1; k < grid[0].length; k++) {
					temp++;
					if (grid[i][k] >= grid[i][j]) {
						break;
					}
				}
				prod *= temp;
				// left
				temp = 0;
				for (int k = j - 1; k >= 0; k--) {
					temp++;
					if (grid[i][k] >= grid[i][j]) {
						break;
					}
				}
				prod *= temp;
				if (prod > ans) {
					ans = prod;
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
