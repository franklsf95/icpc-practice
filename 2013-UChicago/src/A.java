import java.util.ArrayList;
import java.util.Scanner;

public class A {
	static int SZ = 16777216 + 10;
	static int N = 15;
	static int M = 6;
	static int K = 15504;
	static String table[] = new String[SZ];
	static String ht[]  = new String[K + 10];
	
	public static void main(String[] args) {
		// prepare the table
		choose(N, M, new ArrayList<Integer>(M));
		
		// prepare the hash table
		int j = 0;
		for (int i = 0; i < SZ; ++i) {
			if (table[i] != null) {
				ht[j++] = table[i];
			}
		}
		
		// begin
		Scanner in = new Scanner(System.in);
		String str;
		for (int count = 1; ; count++) {
			str = in.nextLine();
			char ch = str.charAt(0);
			if (ch == 'e') {
				break;
			}
			if (ch == 'm') {
				String q = str.substring(2);
				int ans = -1;
				for (int i = 0; i < K; i++) {
					if (ht[i].equals(q)) {
						ans = i;
						break;
					}
				}
				System.out.println("Case " + count + ": " + ans);
			} else if (ch == 'u') {
				int idx = Integer.parseInt(str.substring(2));
				System.out.println("Case " + count + ": " + ht[idx]);
			}
		}
	}
	
	/* put n elements into m buckets */
	static void choose(int n, int m, ArrayList<Integer> a) {
		if (m == 0) {
			// no more buckets, n should be 0
			if (n != 0) {
				return;
			}
			// good solution; write to table
			int hash = 0;
			for (int i = 0; i < M; i++) {
				hash += a.get(i) * Math.pow(N + 1, M - 1 - i);
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < M - 1; i++) {
				sb.append(a.get(i));
				sb.append(' ');
			}
			sb.append(a.get(M - 1));
			table[hash] = sb.toString();
//			System.out.println(sb.toString());
			return;
		}
		for (int i = 0; i <= n; i++) {
			// put i elements in the first bucket
			a.add(i);
			choose(n - i, m - 1, a);
			a.remove(a.size() - 1);
		}
	}
}
