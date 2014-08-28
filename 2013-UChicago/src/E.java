/**
 * @author luans
 * @created Aug 27, 2014
 */

import java.util.*;

public class E {
	
	static long solve(int a) {
		long x = a;
		long highest = x;
		while (x != 1) {
//			System.out.print(x + " ");
			if (highest < x) {
				highest = x;
			}
			int remainder = (int)(x & 1); // 0 if even, 1 if odd
			if (remainder == 0) {
				x >>= 1; // x /= 2
			} else {
				x = (x << 1) + x + 1; // x = 3 * x + 1
			}
		}
		return highest;
	}

	public static void main(String[] args) {
//		System.out.println("Debug");
		
		Scanner in = new Scanner(System.in);
		int n_cases = in.nextInt();
		for (int i = 0; i < n_cases; i++) {
			int caseNo = in.nextInt();
			int initialValue = in.nextInt();
			long result = solve(initialValue);
			System.out.println(caseNo + " " + result);
		}
	}

}
