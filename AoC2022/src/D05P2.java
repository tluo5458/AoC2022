import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class D05P2 {
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-05.txt");

		BufferedReader br;
		String st;
		ArrayList<String> init = new ArrayList<String>();
		ArrayList<String> proc = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(file));
			boolean start = true;
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				if (st.equals("")) {
					start = false;
				} else if (start) {
					init.add(st);
				} else {
					proc.add(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] nums = init.get(init.size() - 1).substring(ans).split("   ");
		String[] stacks = new String[Integer.parseInt(nums[nums.length - 1].substring(0, nums[nums.length - 1].length() - 1))];
		for (int i = 0; i < nums.length; i++) {
			String curr = "";
			for (int j = 0; j < init.size() - 1; j++) {
				if (init.get(j).charAt(4 * i + 1) != ' ') {
					curr += init.get(j).charAt(4 * i + 1);
				}
			}
			stacks[i] = curr;
		}
		for (String s : proc) {
			String[] parse = s.split(" ");
			int[] ints = toInt(new String[] {parse[1], parse[3], parse[5]});
			String move = stacks[ints[1] - 1].substring(0, ints[0]);
			stacks[ints[1] - 1] = stacks[ints[1] - 1].substring(ints[0]);
			stacks[ints[2] - 1] = move + stacks[ints[2] - 1];
		}
		System.out.println(Arrays.toString(stacks));
		return ans;
	}
	
	public static String reverse(String s) {
		String ret = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			ret += s.charAt(i);
		}
		return ret;
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
