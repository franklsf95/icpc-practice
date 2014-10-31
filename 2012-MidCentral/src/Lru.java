import java.util.*;
import java.io.*;

public class Lru {

	static LinkedList<Character> cache;
	static int cacheSize;
	
	static void dump() {
		int n = cache.size();
		for (int i = 0; i < n; i++) {
			System.out.print(cache.get(n - 1 - i));
		}
		System.out.println();
	}
	
	static void visit(char k) {
		for (int i = 0; i < cache.size(); i++) {
			if (cache.get(i) == k) {
				cache.remove(i);
				cache.push(k);
				return;
			}
		}
		if (cache.size() >= cacheSize) {
			cache.pollLast(); // evict
		}
		cache.push(k);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
//		Scanner in = new Scanner(new File("Template.in"));
		
		int nCase = 0;
		while (true) {
			cacheSize = in.nextInt();
			if (cacheSize == 0) {
				break;
			}
			nCase++;
			
			cache = new LinkedList<Character>();
			String ln = in.nextLine();
			// 0 is space
			System.out.println("Simulation " + nCase);
			for (int i = 1; i < ln.length(); i++) {
				Character c = ln.charAt(i);
				if (c == '!') {
					dump();
				} else {
					visit(c);
				}
			}
		}
	}

}
