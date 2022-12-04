import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class D01P2 {
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
		Collections.sort(elves);
		for (int i = elves.size() - 1; i >= elves.size() - 3; i--) {
			ans += elves.get(i);
		}
		return ans;
	}
	
	public static void main(String[] args) {
		System.out.println(ans());
	}
}
