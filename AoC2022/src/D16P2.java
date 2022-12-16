import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class D16P2 {
	/* 
	 * Gigantic ups to the problem creator. Excellent problem, lots of fun to solve.
	 */
	
	private static class Valve {
		String name;
		int flow;
		String[] conn;
		
		public Valve(String s) {
			String[] parse = s.split(" ");
			name = parse[1];
			flow = Integer.parseInt(parse[4].split("=")[1].split(";")[0]);
			conn = new String[parse.length - 9];
			for (int i = 9; i < parse.length; i++) {
				conn[i - 9] = parse[i].substring(0, 2);
			}
		}
		
		public String toString() {
			return name + ", flow rate " + flow + ", conns " + Arrays.toString(conn);
		}
	}
	
	private static class State {
		HashSet<String> on;
		int pressure;
		int perTurn;
		int turns;
		String loc;
		
		public State() {
			on = new HashSet<String>();
			pressure = 0;
			perTurn = 0;
			turns = 0;
			loc = "AA";
		}
		
		public State moveAndOpen(Valve v, int dist) {
			State s = new State();
			s.on.addAll(on);
			s.on.add(v.name);
			s.perTurn = perTurn + v.flow;
			s.turns = turns + dist;
			if (s.turns >= 30) {
				s.pressure = pressure + (30 - turns) * perTurn;
			} else {
				s.pressure = pressure + dist * perTurn;	
			}
			s.loc = v.name;
			
			return s;
		}
	}
	
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-16.txt");

		HashMap<String, Valve> valves = new HashMap<String, Valve>();
		ArrayList<String> pos = new ArrayList<String>();
		
		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				
				Valve v = new Valve(st);
				valves.put(v.name, v);
				if (v.flow > 0) {
					pos.add(v.name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ArrayList<String> starts = new ArrayList<String>();
		starts.addAll(pos);
		starts.add("AA");
		HashMap<String, HashMap<String, Integer>> grid = new HashMap<String, HashMap<String, Integer>>();
		for (String valve : starts) {
			HashMap<String, Integer> dists = new HashMap<String, Integer>();
			HashSet<String> rem = new HashSet<String>();
			rem.addAll(pos);
			
			HashSet<String> curr = new HashSet<String>();
			curr.add(valve);
			int dist = 0;
			while (rem.size() > 0) {
				HashSet<String> next = new HashSet<String>();
				
				for (String s : curr) {
					for (String v : valves.get(s).conn) {
						next.add(v);
					}
				}
				dist++;
				
				for (String s : next) {
					if (rem.contains(s)) {
						rem.remove(s);
						dists.put(s, dist + 1);
					}
				}
				curr = next;
			}
			grid.put(valve, dists);
		}
		
		// now bfs the ans
		HashMap<HashSet<String>, State> eStates = new HashMap<HashSet<String>, State>();
		
		// move the elephant
		ArrayDeque<State> q = new ArrayDeque<State>();
		State eleph = new State();
		eleph.turns = 4;
		q.add(eleph);
		while (!q.isEmpty()) {
			State curr = q.pop();
			if (curr.turns >= 30) {
				if (eStates.containsKey(curr.on)) {
					if (eStates.get(curr.on).pressure < curr.pressure) {
						eStates.put(curr.on, curr);
					}
				} else {
					eStates.put(curr.on, curr);
				}
				continue;
			}
			HashSet<String> canMove = new HashSet<String>();
			canMove.addAll(grid.get(curr.loc).keySet());
			for (String s : curr.on) {
				canMove.remove(s);
			}
			for (String s : canMove) {
				State n = curr.moveAndOpen(valves.get(s), grid.get(curr.loc).get(s));
				q.add(n);
			}
		}

		for (HashSet<String> e : eStates.keySet()) {
			HashSet<String> rem = new HashSet<String>();
			rem.addAll(pos);
			for (String s : e) {
				rem.remove(s);
			}
			
			ArrayDeque<State> qq = new ArrayDeque<State>();
			State me = new State();
			me.turns = 4;
			qq.add(me);
			while (!qq.isEmpty()) {
				State curr = qq.pop();
				if (curr.turns >= 30) {
					ans = Math.max(ans, curr.pressure + eStates.get(e).pressure);
					continue;
				}
				HashSet<String> canMove = new HashSet<String>();
				canMove.addAll(rem);
				for (String s : curr.on) {
					canMove.remove(s);
				}
				for (String s : canMove) {
					State n = curr.moveAndOpen(valves.get(s), grid.get(curr.loc).get(s));
					qq.add(n);
				}
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
		long start = System.nanoTime();
		System.out.println(ans());
		long end = System.nanoTime();
		System.out.println((end - start) / 1000 + " us");
	}
}
