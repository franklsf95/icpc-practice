/**
 * @author luans
 * @created Aug 26, 2014
 */
import java.util.*;

public class B_alt {
	
	static class Graph {
		int nodesCount;
		ArrayList<Node> nodes;
		
		Graph(int N) {
			nodesCount = N;
			nodes = new ArrayList<Node>(N);
			for (int i = 1; i <= N; i++) {
				nodes.add(new Node(i));
			}
		}
		
		Node node(int i) {
			return nodes.get(i - 1); // node 1 is at index 0
		}

		void addEdge(int u, int v) {
			Node uu = node(u);
			Node vv = node(v);
			uu.outEdges.add(vv);
			vv.inEdges.add(uu);
		}
	}

	static class Node {
		int val;
		int treeNo;
		ArrayList<Node> inEdges;
		ArrayList<Node> outEdges;
		
		Node(int i) {
			val = i;
			treeNo = -1;
			inEdges = new ArrayList<Node>();
			outEdges = new ArrayList<Node>();
		}
		
		int markAllOuts(Graph g) {
			int treeSize = 0;
			for (Node v: outEdges) {
				if (v.treeNo == treeNo) {
//					System.out.println(val + " meets cycling node " + v.val);
					treeSize++;
					continue; // it's a cycle!
				} else if (v.treeNo != 0) {
					// meets the same tree from different roots
					// set current tree's root to their tree's root
				}
				v.treeNo = treeNo;
				treeSize += v.markAllOuts(g) + 1;
			}
//			System.out.println(val + " has tree size " + treeSize);
			return treeSize;
		}
	}
	
	static int solve(Graph g) {
		int treeCount = 0;
		for (int i = 1; i <= g.nodesCount; i++) {
			Node root = g.node(i);
			if (root.treeNo != 0) {
				continue;
			}
			if (root.inEdges.size() > 0) {
				continue; // We only start from a root node
			}
//			root.treeNo = g.nextTreeNo();
			int treeSize = root.markAllOuts(g);
//			for (int j = 1; j <= g.nodesCount; j++) {
//				Node n = g.node(j);
//				if (n.treeNo == treeNo) {
//					treeNodes++;
//				}
//			}
//			System.out.println("root=" + i + "   size=" + treeSize + "   nodes=" + treeNodes);
//			if (treeSize == treeNodes - 1) {
//				treeCount++;
//			}
		}
		
		return treeCount;
	}

	public static void main(String[] args) {
		System.out.println("Debug");
		
		Scanner in = new Scanner(System.in);
		int N, M;
		int i = 1;
		while (true) {
			N = in.nextInt();
			M = in.nextInt();
			if (N == 0 && M == 0) {
				return;
			}
			Graph g = new Graph(N);
			int u, v;
			for (int j = 0; j < M; j++) {
				u = in.nextInt();
				v = in.nextInt();
				g.addEdge(u, v);
			}
			
			System.out.print("Case " + i + ": ");
			int result = solve(g);
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
