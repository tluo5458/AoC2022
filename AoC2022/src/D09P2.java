import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class D09P2 {
	private static class Pair {
		int a;
		int b;
		
		public Pair(int x, int y) {
			a = x;
			b = y;
		}
		
		public Pair(Pair x) {
			a = x.a;
			b = x.b;
		}
		
		public String toString() {
			return a + "," + b;
		}
	}
	
	private static ArrayList<Pair> tail(ArrayList<Pair> h) {
		ArrayList<Pair> ret = new ArrayList<Pair>();
		Pair t = new Pair(h.get(0));
		ret.add(t);
		for (int i = 1; i < h.size(); i++) {
			Pair n = new Pair(ret.get(ret.size() - 1));
			if (Math.abs(h.get(i).a - n.a) > 1) {
				// adjust on x axis primary
				n.a = (h.get(i).a > n.a ? 1 : -1) + n.a;
				if (h.get(i).b != n.b) {
					n.b = (h.get(i).b > n.b ? 1 : -1) + n.b;
				}
			} else if (Math.abs(h.get(i).b - n.b) > 1) {
				// adjust on y axis primary
				n.b = (h.get(i).b > n.b ? 1 : -1) + n.b;
				if (h.get(i).a != n.a) {
					n.a = (h.get(i).a > n.a ? 1 : -1) + n.a;
				}
			}
			ret.add(n);
		}
		return ret;
	}
	
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-09.txt");

		BufferedReader br;
		ArrayList<Pair> head = new ArrayList<Pair>();
		Pair h = new Pair(0, 0);
		head.add(h);
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				String[] parse = st.split(" ");
				int d = Integer.parseInt(parse[1]);
				for (int i = 0; i < d; i++) {
					Pair c = new Pair(head.get(head.size() - 1));
					if (parse[0].equals("R")) {
						c.a++;
					} else if (parse[0].equals("L")) {
						c.a--;
					} else if (parse[0].equals("U")) {
						c.b++;
					} else if (parse[0].equals("D")) {
						c.b--;
					}
					head.add(c);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 9; i++) {
			head = tail(head);
		}
		
		HashMap<Integer, HashSet<Integer>> coords = new HashMap<Integer, HashSet<Integer>>();
		for (Pair p : head) {
			if (!coords.containsKey(p.a)) {
				coords.put(p.a, new HashSet<Integer>());
			}
			coords.get(p.a).add(p.b);
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
