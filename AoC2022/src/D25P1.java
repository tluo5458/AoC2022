import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class D25P1 {
	public static String toSnafu(long l) {
		char[] c = {'=', '-', '0', '1', '2'};
		String ret = "";
		while (Math.abs(l) > 0) {
			int m = (int) (l % 5);
			if (Math.abs(m) > 2) {
				if (m < 0) {
					m += 5;
				} else {
					m -= 5;
				}
			}
			ret = c[m + 2] + ret;
			l = (l - m) / 5;
		}
		return ret;
	}
	
	public static int c(char c) {
		if (c == '=') {
			return -2;
		}
		if (c == '-') {
			return -1;
		}
		return Integer.parseInt(Character.toString(c));
	}
	
	public static long fromSnafu(String s) {
		long ret = 0;
		long mult = 1;
		
		for (int i = s.length() - 1; i >= 0; i--) {
			ret += (c(s.charAt(i))) * mult;
			mult *= 5;
		}
		return ret;
	}
	
	public static String ans() {
		File file = new File("inputs/d-25.txt");

		long tot = 0;
		
		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				tot += fromSnafu(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toSnafu(tot);
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
