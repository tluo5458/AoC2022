import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class D04P2 {
	public static int ans() {
		int ans = 0;
		
		File file = new File("inputs/d-04.txt");

		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				// we do a bit of parsing
				String[] parse = st.split(",");
				String[] parse1 = parse[0].split("-");
				String[] parse2 = parse[1].split("-");
				int[] ass1 = {Integer.parseInt(parse1[0]), Integer.parseInt(parse1[1])};
				int[] ass2 = {Integer.parseInt(parse2[0]), Integer.parseInt(parse2[1])};
				if (Math.min(ass1[1], ass2[1]) >= Math.max(ass1[0], ass2[0])) {
					ans++;
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
