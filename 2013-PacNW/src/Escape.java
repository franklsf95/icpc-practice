/**
 * @author luans
 * @created Aug 28, 2014
 */

// Breadth-first Search using Priority Queue
// A hash set or a boolean array must be used for re-visit checking

import java.util.*;

public class Escape {
	
	static class Point {
		int x;
		int y;
		int time; // time after passing this point
		
		Point(int _y, int _x, int[][] map, int base) {
			x = _x;
			y = _y;
			time = map[y][x] + base;
		}
		
		boolean win(int w, int h) {
			if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
//				System.out.println(this + " wins! " + w + " " + h);
				return true;
			}
			return false;
		}
		
		public boolean equals(Object o) {
			Point q = (Point) o;
			return x == q.x && y == q.y;
		}
		
		public int hashCode() {
			return x * 1009 + y;
		}
		
		public String toString() {
			return "(" + x + "," + y + ")[" + time + "]";
		}
	}
	
	static class MyQueue extends PriorityQueue<Point> {
		HashSet<Point> visited;
		
		MyQueue(int initialCapacity, Comparator<Point> comparator) {
			super(initialCapacity, comparator);
			visited = new HashSet<Point>();
		}
		
		public boolean add(Point p) {
			if (visited.contains(p)) {
//				System.out.println("Duplicate " + p);
				return false;
			}
//			System.out.println("Pushed " + p);
			visited.add(p);
			return super.add(p);
		}
	}
	
	static void printMap(int[][] map, int w, int h) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static int solve(Point start, int[][] map, int w, int h) {
		MyQueue queue = new MyQueue(100, new Comparator<Point>() {
	        public int compare(Point p, Point q) {
	        	int ret = p.time - q.time;
//	        	System.out.println(p + " - " + q + " = " + ret);
	        	return ret;
	        }
	    });
		
		queue.add(start);
		
		while (!queue.isEmpty()) {
			Point p = queue.remove();
			if (p.win(w, h)) {
				return p.time;
			}
//			System.out.println("Processing " + p);
			// generate new Points
			Point q;
			if (p.x > 0) { // left
				q = new Point(p.y, p.x - 1, map, p.time);
				queue.add(q);
			}
			if (p.x < w - 1) { // right
				q = new Point(p.y, p.x + 1, map, p.time);
				queue.add(q);
			}
			if (p.y > 0) { // up
				q = new Point(p.y - 1, p.x, map, p.time);
				queue.add(q);
			}
			if (p.y < h - 1) { //down
				q = new Point(p.y + 1, p.x, map, p.time);
				queue.add(q);
			}
		}
		return -1; // should never happen
	}
	
	public static void main(String[] args) {
//		System.out.println("Debug");
		
		Scanner in = new Scanner(System.in);
		int nCases = in.nextInt();
		for (int i = 1; i <= nCases; i++) {
			int K = in.nextInt();
			int w = in.nextInt();
			int h = in.nextInt();
			HashMap<String, Integer> dic = new HashMap<String, Integer>();
			for (int j = 0; j < K; j++) {
				String cls = in.next();
				int duration = in.nextInt();
				dic.put(cls, duration);
			}
			in.nextLine();
			dic.put("E", 0);
			
			int[][] T = new int[h][w];
			Point start = null;
			
			for (int u = 0; u < h; u++) {
				String buf = in.nextLine();
				for (int v = 0; v < w; v++) {
					T[u][v] = dic.get(buf.substring(v, v + 1));
					if (T[u][v] == 0) {
						start = new Point(u, v, T, 0);
					}
				}
			}
			int result = solve(start, T, w, h);
			System.out.println(result);
		}
		
	}
}
