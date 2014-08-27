import java.util.Scanner;
import java.util.ArrayList;

public class C {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cnt = in.nextInt();
		in.nextLine(); // consume \n
		for (int i = 0; i < cnt; i++) {
			String st = in.nextLine();
			if (st.length() == 1) {
				// evolve fails when length == 1
				System.out.println("DIES");
				continue;
			}
			boolean live = false;
			int top = 0;
			ArrayList<String>ss = new ArrayList<String>();
			ss.add(st);
			top++;
			int TLE = 500;
			String ns = st;
			boolean dup = false;
			for (int t = 0; t < TLE; t++) {
//				System.out.println(ns);
				ns = evolve(ns);
				// if all-zero, die and break
				boolean all_zero = true;
				for (int j = 0; j < ns.length(); j++) {
					if (ns.charAt(j) != '0') {
						all_zero = false;
						break;
					}
				}
				if (all_zero) {
					live = false;
					break;
				}
				// otherwise, see if entering loop
				for (String s : ss) {
					if (ns.equals(s)) {
						dup = true;
						break;
					}
				}
				// survive if entering loop
				if (dup) {
					live = true;
					break;
				} else {
					ss.add(ns);
					top++;
				}
			}
			System.out.println(live ? "LIVES" : "DIES");
		}
	}
	
	static String evolve(String st) {
		StringBuilder sb = new StringBuilder();
		sb.append(st.charAt(1) == '1' ? '1' : '0');
		char a, b;
		for (int i = 1; i < st.length() - 1; i++) {
			a = st.charAt(i - 1);
			b = st.charAt(i + 1);
			if ((a == '0' && b == '1') || (a == '1' && b == '0')) {
				sb.append('1');
			} else {
				sb.append('0');
			}
		}
		sb.append(st.charAt(st.length() - 2) == '1' ? '1' : '0');
		return sb.toString();
	}

}
