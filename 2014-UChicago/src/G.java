import java.util.Scanner;

public class G {
	
	public static void solve(int i, int t) {
		int q = t / 25;
		t %= 25;
		int d = t / 10;
		t %= 10;
		int n = t / 5;
		t %= 5;
		int p = t;
		System.out.println(String.format("%d %d QUARTER(S), %d DIME(S), %d NICKEL(S), %d PENNY(S)", i, q, d, n, p));
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		
		for (int i = 1; i <= N; i++) {
			int total = in.nextInt();
			solve(i, total);
		}

	}

}
