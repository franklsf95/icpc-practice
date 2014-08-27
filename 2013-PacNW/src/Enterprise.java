import java.util.*;

public class Enterprise {
	
	static class Point {
		int x;
		int y;
		
		Point() {
			x = y = 0;
		}
		
		Point(int _x, int _y) {
			x = _x;
			y = _y;
		}
		
		public boolean equals(Object o) {
			Point q = (Point) o;
			return x == q.x && y == q.y;
		}
		
		public String toString() {
			return "(" + x + ", " + y + ")";
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
	
	static int enqueue(ArrayList<Point> q, int now, int x, int y) {
		Point p = new Point(x, y);
		System.out.println("Trying to enqueue " + p + "; now is " + now);
		for (int i = 0; i < now; i++) {
			if (q.get(i).equals(p)) {
				System.out.println("We already processed " + p + " at " + i);
				return now;
			}
		}
		q.add(p);
		return now + 1;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int TT = in.nextInt();
		for (int i = 0; i < TT; i++) {
			int k = in.nextInt();
			int w = in.nextInt();
			int h = in.nextInt();
			
			HashMap<String, Integer> dic = new HashMap<String, Integer>();
			for (int j = 0; j < k; j++) {
				String cls = in.next();
				int duration = in.nextInt();
				dic.put(cls, duration);
			}
			in.nextLine();
			dic.put("E", 0);
			
			int[][] T = new int[h][w];
			int[][] f = new int[h][w];
			Point ent = null;
			
			for (int u = 0; u < h; u++) {
				String buf = in.nextLine();
				for (int v = 0; v < w; v++) {
					T[u][v] = dic.get(buf.substring(v, v + 1));
					if (T[u][v] == 0) {
						ent = new Point(u, v);
					}
					f[u][v] = 2147483647;
				}
			}
			
			printMap(T, w, h);
			
			int now = 0;
			ArrayList<Point> queue = new ArrayList<Point>();
			f[ent.x][ent.y] = 0;
			queue.add(ent);
			while (now != queue.size()) {
				Point cur = queue.get(now);
				now++;
				System.out.println("Current point is " + cur);
				
				if (cur.y > 0 && f[cur.x][cur.y] < f[cur.x][cur.y - 1] + T[cur.x][cur.y]) {
					f[cur.x][cur.y] = f[cur.x][cur.y - 1] + T[cur.x][cur.y];
					now = enqueue(queue, now, cur.x, cur.y - 1);
				}
				if (cur.y < h && f[cur.x][cur.y] < f[cur.x][cur.y + 1] + T[cur.x][cur.y]) {
					f[cur.x][cur.y] = f[cur.x][cur.y + 1] + T[cur.x][cur.y];
					now = enqueue(queue, now, cur.x, cur.y + 1);
				}
				if (cur.x > 0 && f[cur.x][cur.y] < f[cur.x - 1][cur.y] + T[cur.x][cur.y]) {
					f[cur.x][cur.y] = f[cur.x - 1][cur.y] + T[cur.x][cur.y];
					now = enqueue(queue, now, cur.x - 1, cur.y);
				}
				if (cur.x < w && f[cur.x][cur.y] < f[cur.x + 1][cur.y] + T[cur.x][cur.y]) {
					f[cur.x][cur.y] = f[cur.x + 1][cur.y] + T[cur.x][cur.y];
					now = enqueue(queue, now, cur.x + 1, cur.y);
				}
			}
			
			printMap(f, w, h);
		}

	}

}
