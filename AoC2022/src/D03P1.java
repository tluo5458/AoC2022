import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

public class D03P1 {
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-03.txt");

		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				char[] st1 = st.substring(0, st.length() / 2).toCharArray();
				char[] st2 = st.substring(st.length() / 2).toCharArray();
				HashSet<Character> c1 = new HashSet<Character>();
				for (char a : st1) {
					c1.add(a);
				}
				for (char a : st2) {
					if (c1.contains(a)) {
						if (a >= 'a') {
							ans += (a - 'a' + 1);
						} else {
							ans += (26 + a - 'A' + 1);
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}
	
	public static void main(String[] args) {
		System.out.println(ans());
	}
}
