import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class D07P1 {
	private static class Node {
		HashMap<String, Node> children;
		Node parent;
		int size;
		
		Node() {
			children = new HashMap<String, Node>();
			size = 0;
			parent = null;
		}
		
		Node(Node n) {
			children = new HashMap<String, Node>();
			size = 0;
			parent = n;
		}
		
		public void addNode(String s) {
			children.put(s, new Node(this));
		}
		
		public void addFile(String s, int size) {
			children.put(s, new Node(this));
			children.get(s).size = size;
		}
		
		public int totalSize() {
			int ret = size;
			for (String s : children.keySet()) {
				ret += children.get(s).totalSize();
			}
			return ret;
		}
		
		public int ans() {
			int ret = 0;
			int curr = this.totalSize();
			if (curr <= 100000 && curr > 0 & children.size() > 0) {
				ret += curr;
			}
			for (Node n : children.values()) {
				ret += n.ans();
			}
			return ret;
		}
	}
	
	public static int ans() {
		File file = new File("inputs/d-07.txt");

		Node head = new Node();
		Node curr = head;
		
		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				String[] parse = st.split(" ");
				if (parse[0].equals("$")) {
					// is a command
					if (parse[1].equals("cd")) {
						if (parse[2].equals("/")) {
							curr = head;
						} else if (parse[2].equals("..")) {
							curr = curr.parent;
						} else {
							if (!curr.children.containsKey(parse[2])) {
								curr.addNode(parse[2]);
							}
							curr = curr.children.get(parse[2]);
						}
					} else if (parse[1].equals("ls")) {
						// do nothing
					}
				} else {
					if (parse[0].equals("dir")) {
						curr.addNode(parse[1]);
					} else {
						curr.addFile(parse[1], Integer.parseInt(parse[0]));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return head.ans();
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
