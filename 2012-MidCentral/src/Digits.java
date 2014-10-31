import java.util.*;
import java.io.*;

public class Digits {
	
	static void solve(int n) {
		while (n >= 10) {
			System.out.print(n + " ");
			int m = 1;
			while (n != 0) {
				m *= (n % 10);
				n /= 10;
			}
			n = m;
		}
		System.out.println(n);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		Scanner in = new Scanner(new File("Template.in"));
		
		int n;
		while (true) {
			n = in.nextInt();
			
			if (n == 0) {
				break;
			}
			
			solve(n);
		}
	}

}
