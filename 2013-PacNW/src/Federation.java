import java.util.*;

public class Federation {
	
	static void solve(int n) {
		ArrayList<Integer> factors = new ArrayList<Integer>(n);
		int sum = 1;
		int lim = (int) Math.floor(Math.sqrt((double)n));
		for (int i = 2; i <= lim; i++) {
			if (n % i == 0) {
				sum += i + (n / i);
				factors.add(i);
//				System.out.println("sum is now " + sum + "  factor " + i);
			}
		}
		
		if (sum != n) {
			System.out.println("is NOT perfect.");
			return;
		}
		int start = factors.size() - 1;
		for (int i = start; i >= 0; i--) {
			factors.add(n / factors.get(i));
		}
		System.out.print("= 1 + ");
		for (int i = 0; i < factors.size() - 1; i++) {
			System.out.print(factors.get(i) + " + ");
		}
		System.out.println(factors.get(factors.size() - 1));
	}


	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int val;
		while ((val = in.nextInt()) != -1) {
			System.out.print(val + " ");
			solve(val);
		}
	}

}
