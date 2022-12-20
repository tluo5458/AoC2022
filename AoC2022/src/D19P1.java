import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class D19P1 {
	private static class Blueprint {
		int[] res;
		int[] prod;
		int[][] costs;
		int time;
		int[] did;
		
		public Blueprint(String s) {
			time = 0;
			res = new int[4];
			prod = new int[4];
			prod[0] = 1;
			costs = new int[4][4];
			
			String[] parse = s.split(" ");
			costs[0][0] = Integer.parseInt(parse[6]);
			costs[1][0] = Integer.parseInt(parse[12]);
			costs[2][0] = Integer.parseInt(parse[18]);
			costs[2][1] = Integer.parseInt(parse[21]);
			costs[3][0] = Integer.parseInt(parse[27]);
			costs[3][2] = Integer.parseInt(parse[30]);
			
			did = new int[24];
		}
		
		public Blueprint(Blueprint other) {
			res = new int[4];
			for (int i = 0; i < 4; i++) {
				res[i] = other.res[i];
			}
			
			prod = new int[4];
			for (int i = 0; i < 4; i++) {
				prod[i] = other.prod[i];
			}
			
			costs = new int[4][4];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					costs[i][j] = other.costs[i][j];
				}
			}
			
			time = other.time;
			
			did = new int[24];
			for (int i = 0; i < 24; i++) {
				did[i] = other.did[i];
			}
		}
		
		public String toString() {
			return "Did: " + Arrays.toString(did) + ", Resources: " + Arrays.toString(res) + ", Production: " + Arrays.toString(prod) + ", o " + Arrays.toString(costs[0]) + ", c " + Arrays.toString(costs[1]) + ", ob " + Arrays.toString(costs[2]) + ", g " + Arrays.toString(costs[3]) + ", time: " + time;
		}
	}
	
	public static int solve(Blueprint init, int t) {
		int max = 0;
		
		Stack<Blueprint> s = new Stack<Blueprint>();
		s.add(init);
		while (!s.isEmpty()) {
			Blueprint br = s.pop();
			if (br.time == t) {
//				if (br.res[3] > max) {
//					System.out.println(br);
//					System.out.println(max);
//				}
				max = Math.max(max, br.res[3]);
				continue;
			}
			// 2 is a threshold here
			if (max > br.res[3] + (br.prod[3] + 1) * (t - br.time) + 5) {
				continue;
			}
			
			// add do nothing
			if (true) {
				Blueprint b = new Blueprint(br);
				for (int j = 0; j < 4; j++) {
					b.res[j] += b.prod[j];
				}
				b.did[b.time] = -1; 
				b.time++;
				s.add(b);
			}
			
			// produce each robot
			for (int i = 3; i >= 0; i--) {
				Blueprint b = new Blueprint(br);
				boolean good = true;
				for (int j = 0; j < 4; j++) {
					if (b.res[j] < b.costs[i][j]) {
						good = false;
						break;
					}
				}
				if (good) {
					for (int j = 0; j < 4; j++) {
						b.res[j] += b.prod[j];
					}
					for (int j = 0; j < 4; j++) {
						b.res[j] -= b.costs[i][j];
					}
					b.prod[i]++;
					b.did[b.time] = i; 
					b.time++;
					s.add(b);
				}
			}

		}
		
		return max;
	}
	
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-19.txt");

		BufferedReader br;
		String st;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				if (st.length() > 0) {
					lines.add(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Blueprint[] bps = new Blueprint[lines.size()];
		for (int i = 0; i < lines.size(); i++) {
			bps[i] = new Blueprint(lines.get(i));
		}
		// CHANGE HERE
		for (int i = 0; i < lines.size(); i++) {
//			System.out.println("============= SOLVING BLUEPRINT " + (i + 1));
//			System.out.println(bps[i]);
			ans += (i + 1) * solve(bps[i], 24);
//			System.out.println(ans);
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
		// yes this takes a couple hours to run
		System.out.println(ans());
	}
}
