import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Template {
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-xx.txt");

		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
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