import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class D11P1 {
	private static class Monkey {
		ArrayList<Integer> items;
		int test;
		int tThrow;
		int fThrow;
		char op;
		int opNum;
		int ct = 0;
		
		public Monkey() {
			items = new ArrayList<Integer>();
		}
		
		public String toString() {
			return items.toString() + " ct " + ct + " div " + test + " tru " + tThrow + " fal " + fThrow + " op " + op + " " + opNum;
		}
	}
	
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-11.txt");

		ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
		
		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			Monkey temp = new Monkey();
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				String[] parse = st.trim().split(" ");
				if (parse[0].equals("Monkey")) {
					temp = new Monkey();
				} else if (st.trim().equals("")) {
					monkeys.add(temp);
				} else if (parse[0].equals("Starting")) {
					int[] mItem = toInt(st.trim().substring(16).split(", "));
					for (int i : mItem) {
						temp.items.add(i);
					}
				} else if (parse[0].equals("Operation:")) {
					if (parse[5].equals("old")) {
						temp.op = '^';
						temp.opNum = 2;
					} else {
						temp.op = parse[4].charAt(0);
						temp.opNum = Integer.parseInt(parse[5]);
					}
				} else if (parse[0].equals("Test:")) {
					temp.test = Integer.parseInt(parse[3]);
				} else if (parse[1].equals("true:")) {
					temp.tThrow = Integer.parseInt(parse[5]);
				} else if (parse[1].equals("false:")) {
					temp.fThrow = Integer.parseInt(parse[5]);
				} else {
					System.out.println("Very bad " + st);
				}
			}
			monkeys.add(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 20; i++) {
			for (int m = 0; m < monkeys.size(); m++) {
				Monkey c = monkeys.get(m);
				int num = c.items.size();
				for (int j = 0; j < num; j++) {
					int item = c.items.remove(0);
					if (c.op == '*') {
						item *= c.opNum;
					} else if (c.op == '+') {
						item += c.opNum;
					} else if (c.op == '^') {
						item *= item;
					} else {
						System.out.println("Stinky op " + c.op);
					}
					item /= 3;
					if (item % c.test == 0) {
						monkeys.get(c.tThrow).items.add(item);
					} else {
						monkeys.get(c.fThrow).items.add(item);
					}
					c.ct++;
				}
			}
		}
		
		int[] active = new int[monkeys.size()];
		for (int i = 0; i < monkeys.size(); i++) {
			active[i] = monkeys.get(i).ct;
		}
		Arrays.sort(active);
		ans = active[active.length - 1] * active[active.length - 2];
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
