import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

public class D15P1 {
	private static class Pair {
		int x;
		int y;
		
		public Pair(int a, int b) {
			x = a;
			y = b;
		}
		
		public String toString() {
			return x + ", " + y;
		}
	}
	
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-15.txt");

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
		
		int l = 2000000;
		HashSet<Integer> on = new HashSet<Integer>();
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		for (String s : lines) {
			String[] parse = s.split(" ");
			int x1 = Integer.parseInt(parse[2].split("=")[1].split(",")[0]);
			int y1 = Integer.parseInt(parse[3].split("=")[1].split(":")[0]);
			int x2 = Integer.parseInt(parse[8].split("=")[1].split(",")[0]);
			int y2 = Integer.parseInt(parse[9].split("=")[1]);
			
			// beacon on the line
			if (y2 == l) {
				on.add(x2);
			}
			
			int dist = manDist(x1, y1, x2, y2);
			if (dist < Math.abs(l - y1)) {
				continue;
			} else {
				if (y1 <= l) {
					pairs.add(new Pair(x1 - y1 - dist + l, x1 + y1 + dist - l));
				} else {
					pairs.add(new Pair(x1 + y1 - dist - l, x1 - y1 + dist + l));
				}
			}
			for (Pair p : pairs) {
				if (p.y < p.x) {
					System.out.println("STINKY STINKY STINKY " + p + " " + l);
				}
			}
		}
		
		ArrayList<Pair> finInt = new ArrayList<Pair>();
		if (pairs.size() > 0) {
			finInt.add(pairs.get(0));
			for (int i = 1; i < pairs.size(); i++) {
				Pair curr = pairs.get(i);
				if (curr.y + 1 < finInt.get(0).x) {
					finInt.add(0, curr);
				} else if (curr.x - 1 > finInt.get(finInt.size() - 1).y) {
					finInt.add(curr);
				} else {
					int currInd = 0;
					for (currInd = 0; currInd < finInt.size(); currInd++) {
						if (finInt.get(currInd).y >= curr.x - 1) {
							break;
						}
					}
					int endInd = 0;
					for (endInd = finInt.size() - 1; endInd >= 0; endInd--) {
						if (finInt.get(endInd).x <= curr.y + 1) {
							break;
						}
					}

					int x = Math.min(finInt.get(currInd).x, curr.x);
					int y = Math.max(finInt.get(endInd).y, curr.y);

					// remove currInd to endInd
					for (int j = currInd; j <= endInd; j++) {
						finInt.remove(currInd);
					}
					finInt.add(currInd, new Pair(x, y));
				}
			}
		}
		
		for (Pair p : finInt) {
			ans += p.y - p.x + 1;
		}
		ans -= on.size();
		return ans;
	}
	
	public static int manDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
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
