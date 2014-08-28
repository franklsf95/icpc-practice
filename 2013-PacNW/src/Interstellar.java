import java.util.*;

public class Interstellar {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int T = in.nextInt();
		for (int i = 0; i < T; i++) {
			int N = in.nextInt();
			int[] planets = new int[N];
			for (int j = 0; j < N; j++) {
				planets[j] = in.nextInt();
			}
			Arrays.sort(planets);
			int min = planets[0];
			int max = planets[planets.length - 1];
//			System.out.println(min + " " + max);
			double middle = (double)(min + max) / 2;
			double nearest = 999999999, dist;
			int nearest_j = 0;
			for (int j = 0; j < N; j++) {
				dist = Math.abs(middle - planets[j]);
				if (dist < nearest) {
					nearest = dist;
					nearest_j = j;
				}
			}
			int ans;
			int coord = planets[nearest_j];
			if (coord < middle) {
				ans = coord - min;
			} else {
				ans = max - coord;
			}
			System.out.println(ans);
		}
	}

}
