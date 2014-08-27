import java.util.*;

public class Holodeck {
	
	static int m(int d) {
		return 19 - d;
	}
	
	static int z(int a1) {
		return a1 == 0 ? 1 : a1;
	}
	
	static int solve(ArrayList<Integer> Y, int i, int j, boolean flag) {		
		int an = Y.get(i);
		int a1 = Y.get(j);
		System.out.print("Solving " + i + "--" + j + " " + flag + ": ");
		for (int k = i; k <= j; k++)
			System.out.print(Y.get(k));
		System.out.println("| an = " + an + "; a1 = " + a1);

		if (i == j) {
			return 1 - (Y.get(i) % 2);  // 0 for odd; 1 for even
		}
		if (i > j) return 1;
		if (i == j - 1) {
			int x = an * 10 + a1;
			if (x < 10) return 0;
			if (x < 20) {
				if (x % 2 == 0 || (flag && x == 11)) {
					return 1;
				} else {
					return 0;
				}				
			}
		}	
		
		if (an == 1) {
			int d = Y.get(i + 1) - a1;
			if (d == 0 || d == 1) {
				ArrayList<Integer> Z = new ArrayList<Integer>(Y);
				Z.set(i + 1, Z.get(i + 1) - a1);
				Z.set(j - 1, Z.get(j - 1) - 1);
				int ret;
				if (d == 0) {
					ret = solve(Z, i + 2, j - 1, false) * m(10 + a1);
				} else {
					ret = solve(Z, i + 1, j - 1, false) * m(10 + a1);
				}
				
				if (a1 == 1 && flag) {
					ret += solve(Y, i + 1, j - 1, true);
				}
				return ret;
			} else {
				if (a1 == 1) {
					return solve(Y, i + 1, j - 1, true);
				} else return 0;
			}
		} else {
			if (an - a1 == 1) {
				ArrayList<Integer> Z = new ArrayList<Integer>(Y);
				Z.set(i, 1); 
				return z(a1) * solve(Z, i, j - 1, true);
			} else if (an == a1) {
				return z(a1) * solve(Y, i + 1, j - 1, true);
			} else return 0;
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		in.nextLine();
		
		for (int i = 0; i < T; i++) {
			String st = in.nextLine();
			ArrayList<Integer> Y = new ArrayList<Integer>(18);
			int stlen = st.length();
			String ch;
			for (int j = 0; j < stlen; j++) {
				ch = st.substring(j, j + 1);
				Y.add(Integer.parseInt(ch));
			}
//			for (Integer k : Y) System.out.print(k);
			int ans = solve(Y, 0, Y.size() - 1, true);
			System.out.println(ans);
		}
	}

}
