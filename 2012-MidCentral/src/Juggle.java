import java.util.*;
import java.io.*;

public class Juggle {
	
	static String L = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static int next = 0;
	
	static class Ball {
		Character c;
		int h;
		
		Ball(char cc, int hh) {
			c = cc;
			h = hh;
		}
		
		static Ball nextBall(int h) {
			Ball b = new Ball(L.charAt(next), h);
			next++;
			return b;
		}
		
		public String toString() {
			return c + "(" + h + ")";
		}
	}
	
	static String solve(int n, int[] f) {
		StringBuilder sb = new StringBuilder(20);
		ArrayList<Ball> airborne = new ArrayList<Ball>(20);
		for (int t = 0; t < 20; t++) {
			int h = f[t % n];
			Ball theBall = null;
			for (Ball b: airborne) {
				if (b.h == 0) {
					if (theBall != null) {
						// crash
						return null;
					} else {
						theBall = b;
					}
				}
			}
			if (theBall != null) {
				theBall.h = h;
			} else {
				theBall = Ball.nextBall(h);
				airborne.add(theBall);
			}
			sb.append(theBall.c);
			
			// --- decrement all heights
			for (Ball b: airborne) {
//				System.out.print(b + "  ");
				b.h -= 1;
			}
//			System.out.println();
//			System.out.println(sb.toString());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		Scanner in = new Scanner(new File("Template.in"));
		
		int n = 0;
		while (true) {
			n = in.nextInt();
			
			if (n == 0) {
				break;
			}
			
			int[] f = new int[n];
			for (int i = 0; i < n; i++) {
				f[i] = in.nextInt();
			}
			
			String s = solve(n, f);
			if (s == null) {
				System.out.println("CRASH");
			} else {
				System.out.println(s);
			}
			next = 0;
		}
	}

}
