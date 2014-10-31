// DID NOT SOLVE

import java.util.*;
import java.io.*;

public class Bounce {
	
	static void print2D(Node[][] a) {
		for (int i = 0; i < h; i++) {
			int lim = w;
			if (i % 2 == 0) {
				System.out.print(" ");
			} else {
				lim++;
			}
			for (int j = 0; j < lim; j++) {
				System.out.print(a[i][j].c + " ");
			}
			System.out.println();
		}
		System.out.println("------------");
	}
	
	static class Point {
		int x, y;
		Point(int xx, int yy) {
			x = xx;
			y = yy;
		}
	}
	
	static class Node {
		char c;
		HashSet<Point> visited;
		String sofar;
		Point p;
		Node(char cc, int ii, int jj) {
			c = cc;
			visited = new HashSet<Point>();
			sofar = ((Character)c).toString();
			p = new Point(ii, jj);
			visited.add(p);
		}
		public String toString() {
			return String.format("(%d, %d)[%c] ", i, j, c) + sofar;
		}
	}
	
	static int h, w, n;
	static Node map[][];
	
	static String solve() {
		for (int repeats = 1; n * repeats <= h * (w + 1); repeats++) {
			String r = solveOne(repeats);
			System.out.println("-----------------\n solved " + repeats + r);
			if (r != null) {
				return r;
			}
			break; //////////
		}
		return "no solution";
	}
	
	static String solveOne(int repeats) {
		for (int j = 0; j < w; j++) {
			String r = search(map[0][j], repeats);
			if (r != null) {
				return r;
			}
		}
		return null;
	}
	
	static String search(Node start, int repeats) {
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.push(start);
		while (!queue.isEmpty()) {
			Node p = queue.pop();
			String sofar = p.sofar;
			System.out.println("p: " + sofar);
			if (sofar.length() == n * (repeats + 1)) {
				return sofar;
			}
			boolean check = false;
			if (sofar.length() >= n) {
				check = true;
			}
			int i = p.i;
			int j = p.j;
			if (i % 2 == 0) {
				enq(queue, i, j - 1, p, check);
				enq(queue, i, j + 1, p, check);
				enq(queue, i + 1, j, p, check);
				enq(queue, i + 1, j + 1, p, check);
				enq(queue, i - 1, j, p, check);
				enq(queue, i - 1, j + 1, p, check);
			} else {
				enq(queue, i, j - 1, p, check);
				enq(queue, i, j + 1, p, check);
				enq(queue, i + 1, j, p, check);
				enq(queue, i + 1, j - 1, p, check);
				enq(queue, i - 1, j, p, check);
				enq(queue, i - 1, j - 1, p, check);
			}
		}
		return null;
	}
	
	static boolean enq(LinkedList<Node> q, int i, int j, Node p, boolean check) {
		if (i < 0 || i >= h) return false;
		if (i % 2 == 0) {
			if (j < 0 || j >= w) return false;
		} else {
			if (j < 0 || j >= w + 1) return false;
		}
		Node p = map[i][j];
		if (p.visited) {
			return false;
		}
		if (check) {
			if (p.c != sofar.charAt(sofar.length() - n)) {
				return false;
			}
		}
		p.sofar = sofar + p.c;
		q.push(p);
		System.out.println("Enqueue: " + p);
		return true;
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
//		Scanner in = new Scanner(new File("Template.in"));
		
		while (true) {
			h = in.nextInt();
			if (h == 0) {
				break;
			}
			w = in.nextInt();
			n = in.nextInt();
			map = new Node[h][w + 1];
			for (int i = 0; i < h; i++) {
				int lim = i % 2 == 0 ? w : w + 1;
				for (int j = 0; j < lim; j++) {
					char c = in.next().charAt(0);
					map[i][j] = new Node(c, i, j);
				}
			}
			print2D(map);
			
			System.out.println(solve());
		}
	}

}
