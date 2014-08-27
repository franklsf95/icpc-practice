import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

public class B {
	static class Vertex {
		public int i;
		public ArrayList<Vertex> outs;
		public int tree;
		
		public Vertex(int j, int n) {
			i = j;
			outs = new ArrayList<Vertex>(n);
			tree = -1;
		}
		
		public void addEdge(Vertex v) {
			outs.add(v);
//			System.out.println(this.i + " -> " + v.i);
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[" + i + "] -> ");
			for (int i = 0; i < outs.size(); i++) {
				sb.append(outs.get(i).i);
				sb.append(' ');
			}
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		for (int count = 1; ; count++) {
			int n = in.nextInt();
			int m = in.nextInt();
			
			if (n == 0 && m == 0) {
				break;
			}
			
			// create n vertices
			ArrayList<Vertex> graph = new ArrayList<Vertex>(n + 1);
			graph.add(null);  // vertex #0
			for (int i = 1; i <= n; i++) {
				graph.add(new Vertex(i, n));
			}
			
			// create m edges
			for (int j = 0; j < m; j++) {
				int u = in.nextInt();
				int v = in.nextInt();
				// create edge (u, v)
				graph.get(u).addEdge(graph.get(v));
			}
			
//			// print graph
//			for (int j = 1; j <= n; j++) {
//				System.out.println(graph.get(j));
//			}
			
			int ans = countTrees(graph);
			System.out.print("Case " + count);
			switch (ans) {
			case 0:
				System.out.println(": No trees.");
				break;
			case 1:
				System.out.println(": There is one tree.");
				break;
			default:
				System.out.println(": A forest of " + ans + " trees.");
			}
		}
	}
	
	static int countTrees(ArrayList<Vertex> graph) {
		int n = graph.size() - 1;
		int tree_no = 0;
		int not_tree_count = 0;
		
		for (int i = 1; i <= n; i++) {
			Vertex u = graph.get(i);
			if (u.tree != -1) {
				// already in some tree
				continue;
			}
			// a new tree
			u.tree = ++tree_no;
			
			ArrayList<Vertex> queue = new ArrayList<Vertex>(n);
			int head = 0;
			boolean not_tree = false;
			queue.add(u);
			while (head < queue.size()) {
				Vertex cur = queue.get(head++);
				// for all children of cur, dye, add to queue
				for (Vertex v : cur.outs) {
					// if added then THIS IS NOT A TREE
					boolean added = false;
//					System.out.println("Enqueing " + cur.i + " -> " + v.i);
					for (int j = 0; j < queue.size(); j++) {
						// need to check the entire queue b/c the duplicate vertex
						// may be behind cur
						if (queue.get(j) == v) {
							added = true;
//							System.out.println("Duplicate: " + v.i);
							break;
						}
					}
					if (added) {
						not_tree = true;
						continue;
					}
					// new vertex
					v.tree = tree_no;
					queue.add(v);
					
//					// print queue
//					for (int k = 0; k < queue.size(); k++) {
//						System.out.print(queue.get(k).i + " ");
//					}
//					System.out.println();
				}
			}
			if (not_tree) {
				not_tree_count++;
			}
			// this dyed all vertices in this "not-tree" connected component
		}
		return tree_no - not_tree_count;
	}

}
