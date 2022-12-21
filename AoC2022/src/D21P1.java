import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class D21P1 {
	public static long eval(String monkey, HashMap<String, String> monkeys, HashMap<String, Long> eval) {
		if (eval.containsKey(monkey)) {
			return eval.get(monkey);
		}
		String e = monkeys.get(monkey);
		if (e.split(" ").length == 1) {
			eval.put(monkey, Long.parseLong(e));
			return Long.parseLong(e);
		}
		
		String[] parse = e.split(" ");
		long a = eval(parse[0], monkeys, eval);
		long b = eval(parse[2], monkeys, eval);
		
		long res = 0;
		if (parse[1].equals("+")) {
			res = a + b;
		} else if (parse[1].equals("-")) {
			res = a - b;
		} else if (parse[1].equals("*")) {
			res = a * b;
		} else {
			res = a / b;
		}
		
		eval.put(monkey, res);
		return res;
	}
	
	public static long ans() {
		long ans = 0;
		
		File file = new File("inputs/d-21.txt");

		HashMap<String, String> monkeys = new HashMap<String, String>();
		HashMap<String, Long> eval = new HashMap<String, Long>();
		
		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				String[] parse = st.split(": ");
				monkeys.put(parse[0], parse[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ans = eval("root", monkeys, eval);
		
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
