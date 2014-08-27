import java.util.Scanner;

public class Assignments {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		
		for (int i = 0; i < cases; i++) {
			int ship_cnt = in.nextInt();
			double distance = in.nextDouble();
			int v, f, c;
			int win_cnt = 0;
			
			for (int j = 0; j < ship_cnt; j++) {
				v = in.nextInt();
				f = in.nextInt();
				c = in.nextInt();
				
				double time = (double)f / c;
				if (time * v >= distance) {
					win_cnt++;
				}
			}
			System.out.println(win_cnt);
		} // end for i
	}
}
