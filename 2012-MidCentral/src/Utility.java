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
	
	/**
	 * Prints an 1-D array
	 */
	static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("------------");
	}
	
//	static void printCol(Collection<T> c) {
//		for ()
//	}
	
	// safe get from array

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
