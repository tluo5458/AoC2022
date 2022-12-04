import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class D01P1 {
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-01.txt");

		BufferedReader br;
		String st;
		ArrayList<Integer> elves = new ArrayList<Integer>();
		try {
			br = new BufferedReader(new FileReader(file));
			int curr = 0;
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				if (st.equals("")) {
					elves.add(curr);
					curr = 0;
				} else {
					curr += Integer.parseInt(st);
				}
			}
			elves.add(curr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i : elves) {
			if (i > ans) {
				ans = i;
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		System.out.println(ans());
	}
}
