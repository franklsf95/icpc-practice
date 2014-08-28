/**
 * @author luans
 * @created Aug 27, 2014
 */
import java.util.*;

public class G {
	
	static class Bitstring {
		
	}
	
	static int solve(String start, String goal) {
		return 0;
		
		
	}

	public static void main(String[] args) {
		System.out.println("Debug");
		
		Scanner in = new Scanner(System.in);
		int nCases = in.nextInt();
		for (int i = 1; i <= nCases; i++) {
			String start = in.nextLine();
			String goal = in.nextLine();
			int result = solve(start, goal);
			System.out.println("Case " + i +  ": " + result);
		}
	}

}
