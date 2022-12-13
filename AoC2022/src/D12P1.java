import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class D12P1 {
	private static class Pair {
		int c;
		int moves;
		
		public Pair(int x) {
			c = x;
			moves = 0;
		}
		
		public Pair(int x, int m) {
			c = x;
			moves = m;
		}
		
		public String toString() {
			return c + " " + moves;
		}
	}
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-12.txt");

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
		char[][] map = new char[lines.size()][lines.get(0).length()];
		for (int i = 0; i < lines.size(); i++) {
			map[i] = lines.get(i).toCharArray();
		}
		int s = 0;
		int e = 0;
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(0).length(); j++) {
				if (map[i][j] == 'S') {
					s = 10000 * i + j;
					map[i][j] = 'a';
				} else if (map[i][j] == 'E') {
					e = 10000 * i + j;
					map[i][j] = 'z';
				}
			}
		}
		HashSet<Integer> visited = new HashSet<Integer>();
		visited.add(s);
		ArrayDeque<Pair> q = new ArrayDeque<Pair>();
		q.add(new Pair(s));
		while (!q.isEmpty()) {
			Pair curr = q.pop();
			if (curr.c == e) {
				return curr.moves;
			}
			int x = curr.c / 10000;
			int y = curr.c % 10000;
			try {
				int n = (x + 1) * 10000 + y;
				if (map[x + 1][y] - map[x][y] <= 1 && !(visited.contains(n))) {
					visited.add(n);
					q.add(new Pair(n, curr.moves + 1));
				}
			} catch (ArrayIndexOutOfBoundsException eeee) {}
			try {
				int n = (x - 1) * 10000 + y;
				if (map[x - 1][y] - map[x][y] <= 1 && !(visited.contains(n))) {
					visited.add(n);
					q.add(new Pair(n, curr.moves + 1));
				}
			} catch (ArrayIndexOutOfBoundsException eeee) {}
			try {
				int n = x * 10000 + y + 1;
				if (map[x][y + 1] - map[x][y] <= 1 && !(visited.contains(n))) {
					visited.add(n);
					q.add(new Pair(n, curr.moves + 1));
				}
			} catch (ArrayIndexOutOfBoundsException eeee) {}
			try {
				int n = x * 10000 + y - 1;
				if (map[x][y - 1] - map[x][y] <= 1 && !(visited.contains(n))) {
					visited.add(n);
					q.add(new Pair(n, curr.moves + 1));
				}
			} catch (ArrayIndexOutOfBoundsException eeee) {}
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
