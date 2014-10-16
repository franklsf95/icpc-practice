/**
 * @author luans
 * @created Sep 21, 2014
 */

import java.util.*;

public class G {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s;
		
		System.out.println("hello");
		while (true) {
			s = in.nextLine();
			if (s.equals("0")) {
				break;
			}
			int n = s.length();
			ArrayList<Integer> d = new ArrayList<Integer>(n);
			for (int i = 0; i < n; i++) {
				d.add(s.charAt(i) - '0');
			}
			System.out.println(solve(d));
		}
	}
	
	static int solve(ArrayList<Integer> d) {
		HashSet<Integer> allNums = new HashSet<Integer>();
		allPossible(d, allNums);
		ArrayList<Integer> all = new ArrayList<Integer>(allNums);
		Collections.sort(all);
		int prev = all.get(0) - 1;
		int len = all.size();
		int x;
		for (int i = 0; i < len; i++) {
			x = all.get(i);
			if (!(x == prev || x == prev + 1)) {
				break;
			}
			prev = x;
		}
//		for (int y : all) {
//			System.out.print(y + " ");
//		}
		return prev + 1;
	}
	
	static void allPossible(ArrayList<Integer> a, HashSet<Integer> ret) {
		int len = a.size();
		if (len == 2) {
			ArrayList<Integer> results = new ArrayList<Integer>();
			resultsFromTwoIntegers(a.get(0), a.get(1), results);
			ret.addAll(results);
			return;
		}
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (i == j) {
					continue;
				}
				int x = a.remove(i);
				int y = a.remove(j - 1); // because a[i] is removed
				ArrayList<Integer> results = new ArrayList<Integer>();
				resultsFromTwoIntegers(x, y, results);
				for (int r : results) {
					a.add(r);
					allPossible(a, ret);
					a.remove(a.size() - 1);
				}
				a.add(i, x);
				a.add(j, y);
			}
		}
	}
	
	static void resultsFromTwoIntegers(int a, int b, ArrayList<Integer> ret) {
		ret.add(a + b);
		if (a >= b) {
			ret.add(a - b);
		} else {
			ret.add(b - a);
		}
		ret.add(a * b);
		if (b != 0) {
			ret.add(a / b);
		}
		if (a != 0) {
			ret.add(b / a);
		}
	}

}
