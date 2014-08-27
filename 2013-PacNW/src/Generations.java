import java.util.*;

public class Generations {

	public static void main(String[] args) {
		long g[] = new long[70];
		
		g[0] = g[1] = 1;
		g[2] = 2;
		g[3] = 4;
		for (int i = 4; i <= 67; i++) {
			g[i] = g[i - 1] + g[i - 2] + g[i - 3] + g[i - 4];
		}
		
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int i = 0; i < T; i++) {
			int idx = in.nextInt();
			System.out.println(g[idx]);
		}

	}

}
