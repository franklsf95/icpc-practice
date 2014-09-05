/**
 * @author luans
 * @created Aug 29, 2014
 */
import java.util.*;

public class C {
	
	static class BitString {
		int len;
		boolean bits[];
		
		BitString(int length) {
			len = length;
			bits = new boolean[length];
		}
		
		BitString(String s) {
			len = s.length();
			bits = new boolean[len];
			for (int i = 0; i < len; i++) {
				char c = s.charAt(i);
				if (c == '1') {
					bits[i] = true;
				}
			}
		}
		
		public int hashCode() {
//			long h = 1;
//			for (int i = 0; i < len; i++) {
//				
//			}
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder(len);
			for (int i = 0; i < len; i++) {
				sb.append(bits[i] ? '1' : '0');
			}
			return sb.toString();
		}
		
		boolean allZero() {
			boolean allZero = true;
			for (int i = 0; i < len; i++) {
				allZero &= !bits[i];
			}
			return allZero;
		}
		
		BitString evolve() {
			BitString c = new BitString(len);
			// if length <= 1, then result will be 0
			if (len >= 2) {
				c.bits[0] = bits[1] ^ false;
				c.bits[len - 1] = bits[len - 2] ^ false;
			}
			for (int i = 1; i <= len - 2; i++) {
				c.bits[i] = bits[i - 1] ^ bits[i + 1];
			}
			return c;
		}
	}

	static boolean solve(BitString b) {
		HashSet<BitString> visited = new HashSet<BitString>(10000);
		visited.add(b);
		
		while (!b.allZero()) {
			b = b.evolve();
			if (visited.contains(b)) { // enter a loop
				return false; // thus will never stop
			}
			visited.add(b);
		}
		return true; // all zero, stop
	}
	
	public static void main(String[] args) {
//		System.out.println("Debug");
		
		Scanner in = new Scanner(System.in);
		int nCases = in.nextInt();
		nCases = 50000;
		in.nextLine(); // the newline character
		for (int i = 0; i < nCases; i++) {
			String s = in.nextLine();
			BitString b = new BitString(s);
			boolean stop = solve(b);
			System.out.println(stop ? "DIES" : "LIVES");
		}
	}

}
