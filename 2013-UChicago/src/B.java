import java.util.Scanner;

/**
 * @author luans
 * @created Aug 27, 2014
 */

public class B {
	
	static final int NOT_CONNECTED = 0;
	static final int CONNECTED = 1;
	static final int TRAVERSED = 2;
	static final int NOT_VISITED = 0;
	
	static int[][] g;
	static int N, M;
	
	static void mark(int u, int v, int mark) {
		g[u][v] = g[v][u] = mark;
	}
	
	static int markTree(int u, int treeNo) {
		int treeSize = 0;
		if (g[u][0] != NOT_VISITED) {
			if (g[u][0] == treeNo) {
				return 1; // the circled-back edge
			} else {
				// should never happen
				System.out.println(u + " has treeNo " + g[u][0] + ", not " + treeNo);
				return 0;
			}
		}
		g[u][0] = treeNo;
		for (int v = 1; v <= N; v++) {
			if (g[u][v] == CONNECTED) {
				g[v][u] = TRAVERSED; // don't go back
				treeSize += markTree(v, treeNo) + 1; // plus this edge
			}
		}
		return treeSize;
	}
	
	static int solve() {
		int treeCount = 0;
		int treeNo = 1;
		for (int i = 1; i <= N; i++) {
			if (g[i][0] != NOT_VISITED) {
				continue;
			}
			int treeSize = markTree(i, treeNo);
			int treeNodes = 0;
			for (int j = 1; j <= N; j++) {
				if (g[j][0] == treeNo) {
					treeNodes++;
				}
			}
			if (treeNodes == treeSize + 1) {
				treeCount++;
//				System.out.println("root=" + i + "   size=" + treeSize + "   nodes=" + treeNodes);
			}
			treeNo++;
		}
		return treeCount;
	}
	
	public static void main(String[] args) {
//		System.out.println("Debug");
		
		Scanner in = new Scanner(System.in);
		int i = 1;
		while (true) {
			N = in.nextInt();
			M = in.nextInt();
			if (N == 0 && M == 0) {
				return;
			}
			g = new int[N + 1][N + 1];
			int u, v;
			for (int j = 0; j < M; j++) {
				u = in.nextInt();
				v = in.nextInt();
				mark(u, v, CONNECTED);
			}
			
			System.out.print("Case " + i + ": ");
			int result = solve();
			if (result == 0) {
				System.out.println("No trees.");
			} else if (result == 1) {
				System.out.println("There is one tree.");
			} else {
				System.out.println("A forest of " + result + " trees.");
			}
			i++;
		}
	}
}
