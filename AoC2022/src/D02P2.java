import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class D02P2 {
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-02.txt");

		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				String[] act = st.split(" ");
				if (act[1].equals("Y")) {
					ans += 3;
				} else if (act[1].equals("Z")) {
					ans += 6;
				}
				if (act[1].equals("Y")) {
					ans += (act[0].charAt(0) - 'A' + 1);
				} else if (act[1].equals("X")) {
					ans += (((act[0].charAt(0) - 'A' + 2) % 3) + 1);
				} else {
					ans += (((act[0].charAt(0) - 'A' + 1) % 3) + 1);
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
