import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class D23P2 {
	public static int ans() {
		File file = new File("inputs/d-23.txt");

		BufferedReader br;
		String st;
		
		int[] direcs = {9999, 10000, 10001, -1, 1, -10001, -10000, -9999};
		
		int[][] checks = {{-10001, -10000, -9999}, {9999, 10000, 10001}, {9999, -1, -10001}, {10001, 1, -9999}};
		
		HashSet<Integer> elves = new HashSet<Integer>();
		
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
		
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(0).length(); j++) {
				if (lines.get(i).charAt(j) == '#') {
					elves.add(10000 * (5000 + i) + (5000 + j));
				}
			}
		}
		
		for (int i = 0; i < 100000; i++) {
			HashMap<Integer, Integer> proposals = new HashMap<Integer, Integer>();
			int checkStart = i % 4;
			for (int e : elves) {
				boolean good = false;
				for (int c : direcs) {
					if (elves.contains(e + c)) {
						good = true;
						break;
					}
				}
				if (good) {
					boolean proposed = false;
					for (int j = checkStart; j < checkStart + 4; j++) {
						int[] check = checks[j % 4];
						boolean empty = true;
						for (int c2 : check) {
							if (elves.contains(e + c2)) {
								empty = false;
								break;
							}
						}
						if (empty) {
							proposed = true;
							proposals.put(e, e + check[1]);
							break;
						}
					}
					if (!proposed) {
						proposals.put(e, e);
					}
				} else {
					proposals.put(e, e);
				}
			}
			
			HashMap<Integer, Integer> proposalCounts = new HashMap<Integer, Integer>();
			for (int p : proposals.values()) {
				proposalCounts.put(p, proposalCounts.getOrDefault(p, 0) + 1);
			}
			
			int moved = 0;
			
			HashSet<Integer> nextElves = new HashSet<Integer>();
			for (int p : proposals.keySet()) {
				if (proposalCounts.get(proposals.get(p)) > 1) {
					nextElves.add(p);
				} else {
					if (proposals.get(p) != p) {
						moved++;
					}
					nextElves.add(proposals.get(p));
				}
			}
			
			if (moved == 0) {
				return i + 1;
			}
			
			elves = nextElves;
		}
		
		return -1;
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
