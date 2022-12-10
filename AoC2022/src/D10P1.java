import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class D10P1 {
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-10.txt");

		BufferedReader br;
		int cycle = 0;
		int x = 1;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				if (st.equals("noop")) {
					cycle++;
					if (cycle % 40 == 20) {
						ans += cycle * x;
					}
				} else {
					String[] parse = st.split(" ");
					int add = Integer.parseInt(parse[1]);
					cycle++;
					if (cycle % 40 == 20) {
						ans += cycle * x;
					}
					cycle++;
					if (cycle % 40 == 20) {
						ans += cycle * x;
					}
					x += add;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
