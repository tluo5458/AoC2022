import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class D13P2 {
	private static class Node implements Comparable<Node> {
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

		@Override
		public int compareTo(D13P2.Node b) {
			if (empty) {
				if (b.empty) {
					return 0;
				}
				return -1;
			}
			if (b.empty) {
				if (empty) {
					return 0;
				}
				return 1;
			}
			
			if (nodes.size() == 0 && b.nodes.size() == 0) {
				return data - b.data;
			}
			if (nodes.size() > 0 && b.nodes.size() > 0) {
				for (int i = 0; i < Math.min(nodes.size(), b.nodes.size()); i++) {
					int curr = nodes.get(i).compareTo(b.nodes.get(i));
					if (curr != 0) {
						return curr;
					}
				}
				return nodes.size() - b.nodes.size();
			}
			if (nodes.size() == 0) {
				return new Node("[" + data + "]").compareTo(b);
			}
			if (b.nodes.size() == 0) {
				return compareTo(new Node("[" + b.data + "]"));
			}
			return 0;
		}
	}
	
	public static int ans() {
		int ans = 1;
		
		File file = new File("inputs/d-13.txt");

		BufferedReader br;
		String st;
		
		ArrayList<Node> lines = new ArrayList<Node>();
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				if (st.length() > 0) {
					lines.add(new Node(st));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		lines.add(new Node("[[2]]"));
		lines.add(new Node("[[6]]"));
		Collections.sort(lines);
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).toString().equals("[[2]]")) {
				ans *= (i + 1);
			} else if (lines.get(i).toString().equals("[[6]]")) {
				ans *= (i + 1);
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
