/**
 * @author luans
 * @created Oct 9, 2014
 */

import java.util.*;

public class B {
	
	static int[][] map;
	
	static int[][] createMap(ArrayList<String> a) {
		int height = a.size();
		int width = a.get(0).length();
		int[][] ret = new int[height][width];
		
		for (int i = 0; i < height; i++) {
			String t = a.get(i);
			for (int j = 0; j < width; j++) {
				ret[i][j] = t.charAt(j);
			}
		}
		
		return ret;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s;
		
		System.out.println("hello");
		while (true) {
			s = in.nextLine();
			if (s.equals("end")) {
				break;
			}
			ArrayList<String> a = new ArrayList<String>();
			a.add(s);
			while (true) {
				s = in.nextLine();
				if (s.length() == 0) {
					break;
				}
				a.add(s);
			}
			map = createMap(a);
			System.out.println(solve());
		}
		in.close();
	}
	
	static class Point {
		int row, col;
		
		Point(int r, int c) {
			row = r;
			col = c;
		}
		
		public String toString() {
			return "(" + row + ", " + col + ")";
		}
		
		public int hashCode() {
			return row * 6 + col;
		}
	}
	
	static class Worm {
		ArrayList<Point> body;
		int length;
		int steps;
		
		Worm(ArrayList<Point> a, int s) {
			length = a.size();
			body = new ArrayList<Point>(length + 2);
			body.addAll(a);
			steps = s;
		}
		
		// gives the worm on the left side
		static Worm initial() {
			int height = map.length;
			ArrayList<Point> a = new ArrayList<Point>(height);
			for (int row = 0; row < height; row++) {
				a.add(new Point(row, 0));
			}
			return new Worm(a, 0);
		}
		
		boolean inPosition() {
			int lastCol = map[0].length - 1;
			int height = map.length;
			boolean inPos[] = new boolean[height];
			for (Point p : body) {
				if (p.col != lastCol) {
					return false;
				} else {
					inPos[p.row] = true;
				}
			}
			for (boolean b : inPos) {
				if (!b) {
					return false;
				}
			}
			return true;
		}
		
		Point head() {
			return body.get(0);
		}
		
		Point tail() {
			return body.get(length - 1);
		}
		
		public int hashCode() {
			int h = 0;
			for (Point p : body) {
				h = h * 300 + p.hashCode();
			}
			return h;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (Point p : body) {
				sb.append(p.toString());
				sb.append(" ");
			}
			return sb.toString();
		}
	}
	
	static int solve() {
		HashSet<Worm> visited = new HashSet<Worm>();
		LinkedList<Worm> queue = new LinkedList<Worm>();
		queue.add(Worm.initial());
		
		Worm success;
		while (!queue.isEmpty()) {
			Worm w = queue.pop();
			if (!visited.contains(w)) {
				continue;
			}
			visited.add(w);
			// generate all possible next states
			
		}
		
		return success.steps;
	}
}
