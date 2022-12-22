import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class D22P2 {
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
					int[] newFacing = {0, 0};
					
					int xSq = (xCon - 1) / 50;
					int ySq = (yCon - 1) / 50;
					if (xSq == 0 && ySq == 1) {
						if (facing[0] == -1) {
							// A
							yCon = 1;
							xCon = y + 100;
							newFacing[0] = 0;
							newFacing[1] = 1;
						} else if (facing[1] == -1) {
							// B
							yCon = 1;
							xCon = 151 - x;
							newFacing[0] = 0;
							newFacing[1] = 1;
						} else {
							System.out.println("Face bad 0 1");
						}
					} else if (xSq == 0 && ySq == 2) {
						if (facing[0] == -1) {
							// E
							xCon = x + 199;
							yCon = y - 100;
							newFacing[0] = -1;
							newFacing[1] = 0;
						} else if (facing[1] == 1) {
							// H
							xCon = 151 - x;
							yCon = 100;
							newFacing[0] = 0;
							newFacing[1] = -1;
						} else if (facing[0] == 1) {
							// G
							xCon = y - 50;
							yCon = 100;
							newFacing[0] = 0;
							newFacing[1] = -1;
						} else {
							System.out.println("Face bad 0 2");
						}
					} else if (xSq == 1 && ySq == 1) {
						if (facing[1] == 1) {
							// G
							xCon = 50;
							yCon = x + 50;
							newFacing[0] = -1;
							newFacing[1] = 0;
						} else if (facing[1] == -1) {
							// C
							xCon = 101;
							yCon = x - 50;
							newFacing[0] = 1;
							newFacing[1] = 0;
						} else {
							System.out.println("Face bad 1 1");
						}
					} else if (xSq == 2 && ySq == 0) {
						if (facing[0] == -1) {
							// C
							xCon = y + 50;
							yCon = 51;
							newFacing[0] = 0;
							newFacing[1] = 1;
						} else if (facing[1] == -1) {
							// B
							xCon = 151 - x;
							yCon = 51;
							newFacing[0] = 0;
							newFacing[1] = 1;
						} else {
							System.out.println("Face bad 2 0");
						}
					} else if (xSq == 2 && ySq == 1) {
						if (facing[0] == 1) {
							// F
							xCon = y + 100;
							yCon = 50;
							newFacing[0] = 0;
							newFacing[1] = -1;
						} else if (facing[1] == 1) {
							// H
							xCon = 151 - x;
							yCon = 150;
							newFacing[0] = 0;
							newFacing[1] = -1;
						} else {
							System.out.println("Face bad 2 1");
						}
					} else if (xSq == 3 && ySq == 0) {
						if (facing[1] == -1) {
							// A
							xCon = 1;
							yCon = x - 100;
							newFacing[0] = 1;
							newFacing[1] = 0;
						} else if (facing[1] == 1) {
							// F
							xCon = 150;
							yCon = x - 100;
							newFacing[0] = -1;
							newFacing[1] = 0;
						} else if (facing[0] == 1) {
							// E
							xCon = x - 199;
							yCon = y + 100;
							newFacing[0] = 1;
							newFacing[1] = 0;
						} else {
							System.out.println("Face bad 0 2");
						}
					} else {
						System.out.println("Face bad all " + x + " " + y + " " + xSq + " " + ySq);
					}
					
					if (grid[xCon][yCon] == '.') {
						x = xCon;
						y = yCon;
						facing[0] = newFacing[0];
						facing[1] = newFacing[1];
					} else if (grid[xCon][yCon] == '#') {
						break;
					} else {
						System.out.println("Very stinky a " + xCon + " " + yCon + " " + facing[0] + " " + facing[1]);
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
