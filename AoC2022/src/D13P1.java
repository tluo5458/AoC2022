import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class D13P1 {
	private static class Node {
		ArrayList<Node> nodes;
		int data;
		boolean empty;
		
		public Node(String s) {
//			System.out.println(s);
			nodes = new ArrayList<Node>();
			if (!s.contains(",") && !s.contains("[") && !s.contains("]")) {
				data = Integer.parseInt(s);
				empty = false;
			} else {
				if (s.charAt(0) == '[') {
					ArrayList<String> l = new ArrayList<String>();
					int lastZero = 0;
					int diff = 0;
					for (int i = 1; i < s.length() - 1; i++) {
						if (s.charAt(i) == '[') {
							diff++;
						} else if (s.charAt(i) == ']') {
							diff--;
						}
						if (diff == 0 && s.charAt(i) == ',') {
							l.add(s.substring(lastZero + 1, i));
							lastZero = i;
						}
					}
					String end = s.substring(lastZero + 1, s.length() - 1);
					if (end.length() > 0) {
						l.add(end);
					}
					if (l.size() == 0) {
						empty = true;
					} else {
						for (String st : l) {
							nodes.add(new Node(st));
						}
					}
				}
			}
		}
		
		public String toString() {
			if (nodes.size() == 0) {
				if (empty) {
					return "[]";
				} else {
					return Integer.toString(data);
				}
			}
			return nodes.toString();
		}
	}
	
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-13.txt");

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
		for (int i = 0; i < lines.size() / 2; i++) {
			if (compare(new Node(lines.get(2 * i)), new Node(lines.get(2 * i + 1))) < 0) {
				ans += i + 1;
			}
		}
		return ans;
	}
	
	public static int compare(Node a, Node b) {
		if (a.empty) {
			if (b.empty) {
				return 0;
			}
			return -1;
		}
		if (b.empty) {
			if (a.empty) {
				return 0;
			}
			return 1;
		}
		
		if (a.nodes.size() == 0 && b.nodes.size() == 0) {
			return a.data - b.data;
		}
		if (a.nodes.size() > 0 && b.nodes.size() > 0) {
			for (int i = 0; i < Math.min(a.nodes.size(), b.nodes.size()); i++) {
				int curr = compare(a.nodes.get(i), b.nodes.get(i));
				if (curr != 0) {
					return curr;
				}
			}
			return a.nodes.size() - b.nodes.size();
		}
		if (a.nodes.size() == 0) {
			return compare(new Node("[" + a.data + "]"), b);
		}
		if (b.nodes.size() == 0) {
			return compare(a, new Node("[" + b.data + "]"));
		}
		return Integer.MAX_VALUE;
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
