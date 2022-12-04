import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class D02P1 {
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-02.txt");

		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				String[] act = st.split(" ");
				ans += (act[1].charAt(0) - 'X' + 1);
				if ((act[0].equals("A") && act[1].equals("Y")) || (act[0].equals("B") && act[1].equals("Z")) || (act[0].equals("C") && act[1].equals("X"))) {
					ans += 6;
				} else if (act[0].charAt(0) - 'A' + 'X' == act[1].charAt(0)) {
					ans += 3;
					
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
