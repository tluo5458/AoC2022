import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class D21P2 {
	static HashMap<String, String> monkeys = new HashMap<String, String>();
	static HashMap<String, Long> eval = new HashMap<String, Long>();
	static HashMap<String, Boolean> hasHumn = new HashMap<String, Boolean>();
	static HashMap<String, Long> need = new HashMap<String, Long>();
	
	public static boolean hasHumn(String monkey) {
		if (hasHumn.containsKey(monkey)) {
			return hasHumn.get(monkey);
		}
		if (monkey.equals("humn")) {
			hasHumn.put("humn", true);
			return true;
		}
		String e = monkeys.get(monkey);;
		String[] parse = e.split(" ");;
		if (parse.length == 1) {
			hasHumn.put(monkey, false);
			return false;
		}
		boolean ret = hasHumn(parse[0]) || hasHumn(parse[2]);
		hasHumn.put(monkey, ret);
		return ret;
	}
	
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
		} else if (parse[1].equals("/")){
			res = a / b;
		} else if (parse[1].equals("=")){
			res = (a == b ? 1 : 0);
		}
		
		eval.put(monkey, res);
		return res;
	}
	
	public static long backEval(String monkey, long curr) {
		if (monkey.equals("humn")) {
			System.out.println(curr);
			return curr;
		}
		String[] parse = monkeys.get(monkey).split(" ");
		if (hasHumn.get(parse[0])) {
			long res = 0;
			long other = eval.get(parse[2]);
			if (parse[1].equals("+")) {
				res = curr - other;
			} else if (parse[1].equals("-")) {
				res = curr + other;
			} else if (parse[1].equals("*")) {
				res = curr / other;
			} else if (parse[1].equals("/")){
				res = curr * other;
			}
			backEval(parse[0], res);
			return res;
		} else {
			long res = 0;
			long other = eval.get(parse[0]);
			if (parse[1].equals("+")) {
				res = curr - other;
			} else if (parse[1].equals("-")) {
				res = other - curr;
			} else if (parse[1].equals("*")) {
				res = curr / other;
			} else if (parse[1].equals("/")){
				res = other / curr;
			}
			backEval(parse[2], res);
			return res;
		}
	}
	
	public static long ans() {
		long ans = 0;
		
		File file = new File("inputs/d-21.txt");
		
		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				String[] parse = st.split(": ");
				if (parse[0].equals("root")) {
					monkeys.put(parse[0], "lrnp = ptnb");
				} else {
					monkeys.put(parse[0], parse[1]);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		eval("root", monkeys, eval);
		
		hasHumn("root");
		
		backEval("lrnp", eval.get("ptnb"));
		
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
		ans();
	}
}
