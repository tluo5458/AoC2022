import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;

public class D09P1 {
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-09.txt");

		BufferedReader br;
		HashMap<Integer, HashSet<Integer>> coords = new HashMap<Integer, HashSet<Integer>>();
		int[] h = {0, 0};
		int[] t = {0, 0};
		coords.put(0, new HashSet<Integer>());
		coords.get(0).add(0);
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				String[] parse = st.split(" ");
				int d = Integer.parseInt(parse[1]);
				for (int i = 0; i < d; i++) {
					if (parse[0].equals("R")) {
						h[0]++;
					} else if (parse[0].equals("L")) {
						h[0]--;
					} else if (parse[0].equals("U")) {
						h[1]++;
					} else if (parse[0].equals("D")) {
						h[1]--;
					}
					if (Math.abs(h[0] - t[0]) > 1) {
						// adjust on x axis primary
						t[0] = (h[0] > t[0] ? 1 : -1) + t[0];
						if (h[1] != t[1]) {
							t[1] = (h[1] > t[1] ? 1 : -1) + t[1];
						}
					} else if (Math.abs(h[1] - t[1]) > 1) {
						// adjust on y axis primary
						t[1] = (h[1] > t[1] ? 1 : -1) + t[1];
						if (h[0] != t[0]) {
							t[0] = (h[0] > t[0] ? 1 : -1) + t[0];
						}
					}
					if (!coords.containsKey(t[0])) {
						coords.put(t[0], new HashSet<Integer>());
					}
					coords.get(t[0]).add(t[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (HashSet<Integer> s : coords.values()) {
			ans += s.size();
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
