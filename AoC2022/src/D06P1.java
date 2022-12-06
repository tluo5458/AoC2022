import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

public class D06P1 {
	@SuppressWarnings("resource")
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-06.txt");

		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				for (int i = 0; i < st.length(); i++) {
					HashSet<Character> set = new HashSet<Character>();
					for (int j = i; j < i + 4; j++) {
						set.add(st.charAt(j));
					}
					if (set.size() == 4) {
						return i + 4;
					}
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
