/**
 * @author luans
 * @created Sep 10, 2014
 */

import java.util.*;

public class BoardGame {
	
	final static int W = 3;
	final static int H = 3;
	static int[][] t = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
	static Board target = new Board(t);
	
	static class Board {
		int[][] b;
		
		Board(int[][] b) {
			this.b = b;
		}
		
		void print() {
			System.out.println("+-------+");
			System.out.println("| " + b[0][0] + " " + b[0][1] + " " + b[0][2] + " |");
			System.out.println("| " + b[1][0] + " " + b[1][1] + " " + b[1][2] + " |");
			System.out.println("| " + b[2][0] + " " + b[2][1] + " " + b[2][2] + " |");
			System.out.println("+-------+");
		}
		
		int heuristic(Board target) {
			int h = 0;
			for (int row = 0; row < H; row++) {
				for (int col = 0; col < W; col++) {
					int digit = b[row][col];
					int[] coords = new int[2];
					target.find(digit, coords);
					int dist = Math.abs(coords[0] - row) + Math.abs(coords[1] - col);
					h += dist;
				}
			}
			return h;
		}
		
		// write the coordinate of the given digit in the array return_coordinates
		// caller must guarantee the array return_coordinates is allocated for at least 2 ints
		void find(int digit, int[] return_coordinates) {
			for (int row = 0; row < H; row++) {
				for (int col = 0; col < W; col++) {
					if (b[row][col] == digit) {
						return_coordinates[0] = row;
						return_coordinates[1] = col;
						return;
					}
				}
			}
			return_coordinates[0] = -1;
			return_coordinates[1] = -1;
		}
		
		public boolean equals(Object o) {
			Board other = (Board) o;
			for (int row = 0; row < H; row++) {
				for (int col = 0; col < W; col++) {
					if (b[row][col] != other.b[row][col]) {
						return false;
					}
				}
			}
			return true;
		}
		
		public int hashCode() {
			long h = 0;
			for (int row = 0; row < H; row++) {
				for (int col = 0; col < W; col++) {
					h |= b[row][col];
					h <<= 4;
				}
			}
			return (int) (h >> 4); // to compensate the last << operation
		}
	}
	
	static class State {
		Board brd;
		int g; // past cost (accumulative)
		int h; // heuristic cost (future)
		
		State(Board brd) {
			this.brd = brd;
			this.g = 0;
			this.h = brd.heuristic(target);
		}
		
		void print() {
			brd.print();
			System.out.println("heuristic = " + h);
		}
	}
	
	// returns the minimal steps required to reach target state
	static int search(State s, Board target) {
		PriorityQueue<State> fringe = new PriorityQueue<State>(1000, new Comparator<State>() {
			public int compare(State s, State t) {
				return s.g + s.h - (t.g + t.h);
			}
		});
		// add initial state
		fringe.add(s);
		while (!fringe.isEmpty()) {
			State t = fringe.poll();
			if (t.brd == target) {
				return t.g;
			}
		}
		return -1; // impossible
	}

	public static void main(String[] args) {
		System.out.println("Hello, world");
		
//		int[][] initial = {{8, 7, 6}, {0, 1, 2}, {3, 5, 4}};
		int[][] initial = {{1, 0, 2}, {3, 4, 5}, {6, 7, 8}};
		Board startBrd = new Board(initial);
		State s = new State(startBrd);
		search(s, target);
	}

}
