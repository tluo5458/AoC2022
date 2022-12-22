import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class D22P1 {
	public static int ans() {
		int ans = 0;

		File file = new File("inputs/d-22.txt");

		ArrayList<String> lines = new ArrayList<String>();

		lines.add("");

		BufferedReader br;
		String st;
		boolean stop = false;
		String end = "";
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				if (!stop && st.length() > 0) {
					lines.add(" " + st + " ");
				} else if (st.length() == 0) {
					stop = true;
				} else if (stop) {
					end = st;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		lines.add("");

		int[] xLen = new int[lines.size()];
		int[] yLen = new int[lines.get(1).length()];

		char[][] grid = new char[lines.size()][lines.get(1).length()];
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < grid[0].length; j++) { 
				if (j < lines.get(i).length()) {
					grid[i][j] = lines.get(i).charAt(j);
				} else {
					grid[i][j] = ' ';
				}
			}
		}

		for (int i = 0; i < grid.length; i++) {
			boolean started = false;
			int min = 0;
			for (int j = 0; j < grid[i].length; j++) {
				if (!started && grid[i][j] != ' ') {
					started = true;
					min = j;
				}
				if (started && grid[i][j] == ' ') {
					xLen[i] = j - min;
					started = false;
					break;
				}
			}
			if (started) {
				xLen[i] = grid[i].length - min;
			}
		}
		for (int i = 0; i < grid[0].length; i++) {
			boolean started = false;
			int min = 0;
			for (int j = 0; j < grid.length; j++) {
				if (!started && grid[j][i] != ' ') {
					started = true;
					min = j;
				}
				if (started && grid[j][i] == ' ') {
					yLen[i] = j - min;
					started = false;
					break;
				}
			}
			if (started) {
				yLen[i] = grid.length - min;
			}
		}

		int[] len = toInt(end.split("[LR]"));
		boolean[] turnRight = new boolean[len.length - 1];
		String[] parse = end.split("\\d+");
		for (int i = 1; i < len.length; i++) {
			turnRight[i - 1] = parse[i].equals("R");
		}

		int[] facing = {0, 1};
		int x = 1;
		int y = 51;

		for (int i = 0; i < len.length; i++) {
			for (int j = 0; j < len[i]; j++) {
				if (grid[x + facing[0]][y + facing[1]] == '.') {
					x += facing[0];
					y += facing[1];
				} else if (grid[x + facing[0]][y + facing[1]] == '#') {
					break;
				} else if (grid[x + facing[0]][y + facing[1]] == ' ') {
					int xCon = x;
					int yCon = y;
					if (facing[0] > 0) {
						xCon = x + facing[0] - yLen[y];
					} else if (facing[0] < 0) {
						xCon = x + facing[0] + yLen[y];
					} else if (facing[1] > 0) {
						yCon = y + facing[1] - xLen[x];
					} else if (facing[1] < 0) {
						yCon = y + facing[1] + xLen[x];
					}
					if (grid[xCon][yCon] == '.') {
						x = xCon;
						y = yCon;
					} else if (grid[xCon][yCon] == '#') {
						break;
					} else {
						System.out.println("Very stinky a");
					}
				} else {
					System.out.println("Very stinky b (should be oob)");
				}
			}
			
			if (i < turnRight.length) {
				if (turnRight[i]) {
					int[] next = new int[] {facing[1], -1 * facing[0]};
					facing[0] = next[0];
					facing[1] = next[1];
				} else {
					int[] next = new int[] {-1 * facing[1], facing[0]};
					facing[0] = next[0];
					facing[1] = next[1];
				}
			}
		}

		System.out.println(x + ", " + y + ", facing " + Arrays.toString(facing));

		ans += 1000 * x;
		ans += y * 4;
		if (facing[0] > 0) {
			ans++;
		} else if (facing[0] < 0) {
			ans += 3;
		} else if (facing[1] < 0) {
			ans += 2;
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
