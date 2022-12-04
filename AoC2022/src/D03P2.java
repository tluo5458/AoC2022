import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

public class D03P2 {
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-03.txt");

		BufferedReader br;
		String st;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				lines.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < lines.size(); i += 3) {
			HashSet<Character> c1 = new HashSet<Character>();
			for (char c : lines.get(i).toCharArray()) {
				c1.add(c);
			}
			HashSet<Character> c2 = new HashSet<Character>();
			for (char c : lines.get(i + 1).toCharArray()) {
				if (c1.contains(c)) {
					c2.add(c);
				}
			}
			for (char c : lines.get(i + 2).toCharArray()) {
				if (c2.contains(c)) {
					if (c >= 'a') {
						ans += (c - 'a' + 1);
					} else {
						ans += (26 + c - 'A' + 1);
					}
					break;
				}
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		System.out.println(ans());
	}
}
