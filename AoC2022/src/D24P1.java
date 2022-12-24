import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class D24P1 {
	private static class State {
		int x;
		int y;
		int t;
		
		public State(int a, int b) {
			x = a;
			y = b;
			t = 0;
		}
		
		public State(State o) {
			x = o.x;
			y = o.y;
			t = o.t;
		}
	}
	
	private static class Point {
		int x;
		int y;
		int[] move;
		
		int maxX;
		int maxY;
		
		char d;
		
		public Point(int a, int b, int ma, int mb, char c) {
			x = a;
			y = b;
			maxX = ma;
			maxY = mb;
			if (c == '^') {
				move = new int[] {-1, 0};
			} else if (c == 'v') {
				move = new int[] {1, 0};
			} else if (c == '>') {
				move = new int[] {0, 1};
			} else if (c == '<') {
				move = new int[] {0, -1};
			} else {
				System.out.println("Very stinky");
			}
			d = c;
		}
		
		public Point(Point o) {
			x = o.x;
			y = o.y;
			move = new int[2];
			move[0] = o.move[0];
			move[1] = o.move[1];
			maxX = o.maxX;
			maxY = o.maxY;
			d = o.d;
		}
		
		public String toString() {
			return Character.toString(d) + " " + x + ", " + y;
		}
	}
	
	private static HashSet<Integer> toSet(ArrayList<Point> points) {
		HashSet<Integer> ret = new HashSet<Integer>();
		for (Point p : points) {
			ret.add(p.x * 10000 + p.y);
		}
		return ret;
	}
	
	private static ArrayList<Point> move(ArrayList<Point> points) {
		ArrayList<Point> ret = new ArrayList<Point>();
		for (Point p : points) {
			Point n = new Point(p);
			n.x += n.move[0];
			n.y += n.move[1];
			if (n.x >= n.maxX) {
				n.x -= n.maxX;
				n.x += 1;
			} else if (n.x <= 0) {
				n.x += n.maxX - 1;
			}
			if (n.y >= n.maxY) {
				n.y -= n.maxY;
				n.y += 1;
			} else if (n.y <= 0) {
				n.y += n.maxY - 1;
			}
			ret.add(n);
		}
		return ret;
	}
	
	public static int toInt(int x, int y) {
		return 10000 * x + y;
	}
	
	public static int ans() {
		int ans = -1;
		
		File file = new File("inputs/d-24.txt");

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
		int height = lines.size();
		int length = lines.get(0).length();
		
		ArrayList<Point> blizz = new ArrayList<Point>();
		for (int i = 0; i < height; i++) {
			String s = lines.get(i);
			for (int j = 0; j < length; j++) {
				if (s.charAt(j) != '#' && s.charAt(j) != '.') {
					blizz.add(new Point(i, j, height - 1, length - 1, s.charAt(j)));
				}
			}
		}
		
		int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {0, 0}};
		
		int goalX = height - 1;
		int goalY = length - 2;
		
		HashMap<Integer, ArrayList<Point>> blizzT = new HashMap<Integer, ArrayList<Point>>();

		blizzT.put(0, move(blizz));
		
		HashMap<Integer, HashSet<Integer>> added = new HashMap<Integer, HashSet<Integer>>();
		
		added.put(0, new HashSet<Integer>());
		
		ArrayDeque<State> q = new ArrayDeque<State>();
		q.add(new State(0, 1));
		added.get(0).add(1);
		while (!q.isEmpty()) {
			State curr = q.pop();
			if (curr.x == goalX && curr.y == goalY) {
				return curr.t;
			}
			
			if (!blizzT.containsKey(curr.t)) {
				blizzT.put(curr.t, move(blizzT.get(curr.t - 1)));
			}
			HashSet<Integer> points = toSet(blizzT.get(curr.t));
			for (int[] m : move) {
				State next = new State(curr);
				next.x += m[0];
				next.y += m[1];
				if (next.x <= 0 || next.x >= height - 1) {
					if ((next.x == 0 && next.y == 1) || (next.x == goalX && next.y == goalY)) {
						// yea it's fine
					} else {
						continue;
					}
				}
				if (next.y <= 0 || next.y >= length - 1) {
					continue;
				}
				if (points.contains(toInt(next.x, next.y))) {
					continue;
				}
				next.t++;
				if (!added.containsKey(next.t)) {
					added.put(next.t, new HashSet<Integer>());
				}
				if (added.get(next.t).contains(toInt(next.x, next.y))) {
					continue;
				}
				added.get(next.t).add(toInt(next.x, next.y));
				q.add(next);
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
