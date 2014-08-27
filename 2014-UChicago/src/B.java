import java.util.*;

public class B {
	
	static class Graph {
		int M;
		ArrayList<Node> nodes;
		
		Graph(int m) {
			M = m;
			nodes = new ArrayList<Node>();
		}
		
		Node nodeByInt(int u) {
			for (Node n: nodes) {
				if (n.val == u) {
					return n;
				}
			}
			Node n = new Node(u);
			nodes.add(n);
			return n;
		}
		
		void addEdge(int u, int v) {
			Node uu = nodeByInt(u);
			Node vv = nodeByInt(v);
			uu.addOutEdge(vv);
			vv.addInEdge(uu);
		}
	}
	
	static class Node {
		int val;
		int order;
		ArrayList<Node> inEdges;
		ArrayList<Node> outEdges;
		
		Node(int v) {
			val = v;
			order = -1;
			inEdges = new ArrayList<Node>();
			outEdges = new ArrayList<Node>();
		}
		
		void addInEdge(Node u) {
			inEdges.add(u);
		}
		
		void addOutEdge(Node v) {
			outEdges.add(v);
		}
		
		int getOrder() {
			if (order != -1) {
				return order;
			}
			ArrayList<Integer>orders = new ArrayList<Integer>();
			int highest = 1;
			int highestCount = 0;
			for (Node inNode: inEdges) {
				int ord = inNode.getOrder();
				if (ord == highest) {
					highestCount++;
				} else if (ord > highest) {
					highest = ord;
					highestCount = 1;
				}
				orders.add(ord);
			}
			if (highestCount >= 2) {
				this.order = highest + 1;
			} else {
				this.order = highest;
			}
			return this.order;
		}
	}
	
	public static int solve(Graph g) {
		int order = 0;
		Node mouth = g.nodeByInt(g.M);
		
		return mouth.getOrder();
	}

	public static void main(String[] args) {
//		System.out.println("Debug");

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		
		for (int i = 1; i <= N; i++) {
			int K = in.nextInt(); // index
			int M = in.nextInt(); // nodes
			int P = in.nextInt(); // edges
			
			Graph g = new Graph(M);
			
			for (int j = 0; j < P; j++) {
				int u = in.nextInt();
				int v = in.nextInt();
				g.addEdge(u, v);
			}
			
			int result = solve(g);
			System.out.println(String.format("%d %d", K, result));
		}

	}

}
