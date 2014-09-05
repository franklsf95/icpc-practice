/**
 * @author luans
 * @created Sep 4, 2014
 */

import java.util.*;

public class cashcow {
	
	static final int H = 12;
	static final int W = 10;
	static final int DOWN = 'a' - 'A';
	
	static int parse(String row) {
		return row.charAt(0) - 'a';
	}
	
	static int parse(int col) {
		return col - 1;
	}
	
	static class Board {
		char[][] board;
		
		Board(Scanner in) {
			board = new char[H][W];
			for (int row = H - 1; row >= 0; row--) {
				String line = in.nextLine();
				for (int col = 0; col < W; col++) {
					board[row][col] = line.charAt(col);
				}
			}
		}
		
		void execute(String col, int row) {
			int r = parse(row);
			int c = parse(col);
			char color = board[r][c];
			if (color == ' ') {
				return;
			}
			int cnt = preDye(r, c, color);
//			System.out.println("Dye col=" + c + " row=" + r + " color=" + color + " predye=" + cnt);
			rollbackPreDye();
			if (cnt < 3) {
				return;
			}
			dye(r, c, color);
//			print();
//			System.out.println("Fall");
			fall();
//			print();
		}

		void fall() {
			for (int col = 0; col < W; col++) {
				int fall = 0;
				for (int row = 0; row < H; row++) {
					if (board[row][col] == ' ') {
						fall++;
					} else {
						if (fall > 0) {
							board[row - fall][col] = board[row][col];
							board[row][col] = ' ';
						}
					}
				}
			}
			int fallLeftDist = 0;
			for (int col = 0; col < W; col++) {
				boolean colEmpty = true;
				for (int row = 0; row < H; row++) {
					if (board[row][col] != ' ') {
						colEmpty = false;
						break;
					}
				}
				if (colEmpty) {
					fallLeftDist++;
//					System.out.println(col + " is empty");
					continue;
				}
				if (fallLeftDist > 0 && col >= fallLeftDist) {
					for (int row = 0; row < H; row++) {
						board[row][col - fallLeftDist] = board[row][col];
					}
				}
			}
			for (int col = W - fallLeftDist; col < W; col++) {
				for (int row = 0; row < H; row++) {
					board[row][col] = ' ';
				}
			}
		}
		
		void dye(int row, int col, char color) {
			if (board[row][col] != color) {
				return;
			}
			board[row][col] = ' ';
			if (row > 0) {
				dye(row - 1, col, color);
			}
			if (row < H - 1) {
				dye(row + 1, col, color);
			}
			if (col > 0) {
				dye(row, col - 1, color);
			}
			if (col < W - 1) {
				dye(row, col + 1, color);
			}
		}
		
		int preDye(int row, int col, char color) {
			if (board[row][col] != color) {
				return 0;
			}
			board[row][col] += DOWN;
			int result = 1;
			if (row > 0) {
				result += preDye(row - 1, col, color);
				if (result >= 3) {
					return result;
				}
			}
			if (row < H - 1) {
				result += preDye(row + 1, col, color);
				if (result >= 3) {
					return result;
				}
			}
			if (col > 0) {
				result += preDye(row, col - 1, color);
				if (result >= 3) {
					return result;
				}
			}
			if (col < W - 1) {
				result += preDye(row, col + 1, color);
			}
			return result;
		}
		
		void rollbackPreDye() {
			for (int row = H - 1; row >= 0; row--) {
				for (int col = 0; col < W; col++) {
					char c = board[row][col];
					if (c >= 'a' && c <= 'z') {
						board[row][col] -= DOWN;
					}
				}
			}
		}
		
		int remaining() {
			int result = 0;
			for (int row = H - 1; row >= 0; row--) {
				for (int col = 0; col < W; col++) {
					if (board[row][col] != ' ') {
						result++;
					}
				}
			}
			return result;
		}
		
		// ---- utilities
		void print() {
			System.out.println("----------");
			for (int row = H - 1; row >= 0; row--) {
				for (int col = 0; col < W; col++) {
					System.out.print(board[row][col]);
				}
				System.out.println();
			}
			System.out.println("----------");
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		System.out.println("Hello");
		
		while (true) {
			int T = in.nextInt();
			if (T == 0) {
				break;
			}
			in.nextLine(); // newline
			Board b = new Board(in);
			for (int i = 0; i < T; i++) {
				String col = in.next();
				int row = in.nextInt();
				b.execute(col, row);
			}
			System.out.println(b.remaining());
		}
	}
}
