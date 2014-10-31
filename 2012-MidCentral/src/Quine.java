import java.util.*;
import java.io.*;

public class Quine {

	static String solve(String s) {
		if (s.length() == 0) { return null; }
		if (s.charAt(0) != '"') { return null; }
		int p = s.indexOf('"', 1);
		if (p == -1) {
			return null;
		}
		int slen = p - 1;
		int offset = p + 1;
		if (p == 1) {
			return null;
		}
		if (p < s.length() - 1 && s.charAt(p + 1) != ' ') {
			return null;
		}
		if (slen * 2 + 3 != s.length()) {
			return null;
		}
		for (int i = 1; i < p; i++) {
			int j = i + offset;
			if (j >= s.length()) {
				return null;
			}
			if (s.charAt(i) != s.charAt(j)) {
				return null;
			}
			char c = s.charAt(i);
			if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == ' ')) {
				return null;
			}
		}
		return s.substring(1, p);
	}
	
	static String solve2(String s) {
		int len = s.length();
		if (len % 2 == 0 || len < 3) {
//			System
			return null;
		}
		int p = (len - 3) / 2 + 1;
		if (s.charAt(p + 1) != ' ') {
			return null;
		}
		for (int i = 1; i < p; i++) {
			int j = i + p + 1;
			if (s.charAt(i) != s.charAt(j)) {
				return null;
			}
			char c = s.charAt(i);
			if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == ' ')) {
				return null;
			}
		}
		return s.substring(1, p);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
//		Scanner in = new Scanner(new File("Template.in"));
		
		while (true) {
			// ....
			String s = in.nextLine();
			if (s.equals("END")) {
				break;
			}
			String ret = solve(s);
			if (ret == null) {
				System.out.println("not a quine");
			} else {
				System.out.println("Quine(" + ret + ")");
			}
			
		}
	}

}
