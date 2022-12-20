import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class D20P2 {
	public static long ans() {
		long ans = 0;

		File file = new File("inputs/d-20.txt");

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

		ArrayList<Long> orig = new ArrayList<Long>();
		ArrayList<Long> move = new ArrayList<Long>();

		long key = 811589153L;
		for (int i = 0; i < lines.size(); i++) {
			move.add((long) i);
			orig.add(Long.parseLong(lines.get(i)) * key);
		}

		for (int a = 0; a < 10; a++) {
			for (int i = 0; i < orig.size(); i++) {
				long start = move.get(i);
				long curr = orig.get(i);
				long next = (curr + start) % (orig.size() - 1);
				if (next < 0) {
					next += orig.size() - 1;
				}

				if (next > start) {
					for (int j = 0; j < move.size(); j++) {
						if (move.get(j) > start && move.get(j) <= next) {
							move.set(j, move.get(j) - 1);
						}
					}
				}

				if (next < start) {
					for (int j = 0; j < move.size(); j++) {
						if (move.get(j) >= next && move.get(j) < start) {
							move.set(j, move.get(j) + 1);
						}
					}
				}

				move.set(i, next);
			}
			long[] temp = new long[orig.size()];
			for (int i = 0; i < orig.size(); i++) {
				temp[move.get(i).intValue()] = orig.get(i);
			}
		}
		
		long zero = move.get(orig.indexOf(0L));
		for (int i : new int[] {1000, 2000, 3000}) {
			//			System.out.println(move.get((zero + i) % move.size()));
			ans += orig.get(move.indexOf((zero + i) % move.size()));
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
