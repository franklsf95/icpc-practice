/**
 * @author luans
 * @created Aug 27, 2014
 */
import java.util.Scanner;

public class F {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n_cases = in.nextInt();
		final int[] goodStrength = {1, 2, 3, 3, 4, 10};
		final int[] evilStrength = {1, 2, 2, 2, 3, 5, 10};
		final int goodSize = 6;
		final int evilSize = 7;
		int count;
		for (int i = 1; i <= n_cases; i++) {
			int goodTotal = 0;
			int evilTotal = 0;
			System.out.print("Battle " + i + ": ");
			for (int j = 0; j < goodSize; j++) {
				count = in.nextInt();
				goodTotal += count * goodStrength[j];
			}
			for (int j = 0; j < evilSize; j++) {
				count = in.nextInt();
				evilTotal += count * evilStrength[j];
			}
			if (goodTotal > evilTotal) {
				System.out.println("Good triumphs over Evil");
			} else if (goodTotal == evilTotal) {
				System.out.println("No victor on this battle field");
			} else {
				System.out.println("Evil eradicates all trace of Good");
			}
		}

	}

}
