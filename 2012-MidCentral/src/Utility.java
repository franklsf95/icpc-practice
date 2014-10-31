import java.util.*;

public class Utility {
	
	/**
	 * Prints a 2-D array
	 */
	static void print2D(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("------------");
	}
	
	static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("------------");
	}
	
	static void print(ArrayList<Integer> a) {
		for (int x: a) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	static int get(int[][] a, int i, int j) {
		if (i < 0 || i >= a.length) return -1;
		if (j < 0 || j >= a[0].length) return -1;
		return a[i][j];
	}

}
