import java.util.*;
import java.io.*;

public class Slice {
	
	static class Segment {
		double ax, ay, bx, by;
		Segment(double aax, double aay, double bbx, double bby) {
			ax = aax;
			ay = aay;
			bx = bbx;
			by = bby;
		}
		Segment(int deg, int dist) {
			ax = x;
			ay = y;
			bx = x + Math.cos(deg / 180.0 * Math.PI) * dist;
			by = y + Math.sin(deg / 180.0 * Math.PI) * dist;
		}
	}
	
	static double x, y;
	static int curDeg;
	static ArrayList<Segment> segments;
	
	static boolean cut(Segment t) {
		for (int i = 0; i < segments.size() - 1; i++) {
			Segment s = segments.get(i);
			if (judge(s,t)==true){
				return true;
			}
		}
		return false;
	}
	
	final static int COUNTER = -1;
	final static int ON = 0;
	final static int CLOCKWISE = 1;
	final static double EPS = 0.0000001;
	
	static boolean negative(double x) {
		return x < -EPS;
	}
	
	static boolean judge(Segment s, Segment t) {
		double tmp1=(s.ax-s.bx)*(t.ay-s.by)-(s.ay-s.by)*(t.ax-s.bx);
		double tmp2=(s.ax-s.bx)*(t.by-s.by)-(s.ay-s.by)*(t.bx-s.bx);
		double tmp3=(t.ax-t.bx)*(s.ay-t.by)-(t.ay-t.by)*(s.ax-t.bx);
		double tmp4=(t.ax-t.bx)*(s.by-t.by)-(t.ay-t.by)*(s.bx-t.bx);
//		System.out.printf("%.2f %.2f %.2f %.2f\n", s.ax, s.ay, s.bx, s.by);
//		System.out.printf("%.2f %.2f %.2f %.2f\n", t.ax, t.ay, t.bx, t.by);
//		System.out.printf("%.8f %.8f \n", tmp1 * tmp2, tmp3*tmp4);
//		boolean ta_online=false;
//		boolean tb_online=false;
//		if(Math.abs(tmp1)<0.001) {
//			ta_online=true;
//		}
//		if(Math.abs(tmp2)<0.001){
//			
//			tb_online=true;
//		}
//		
//		if(ta_online&&tb_online){
//			return false;
//		}
//		
		

		
		if((tmp1*tmp2)<0 && (tmp3*tmp4)<0){
			return true;
		}
		
		
		if((tmp1*tmp2)<0 && (tmp3*tmp4)<0){
			return true;
		}
		
		else return false;
		
		
		
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);

		int n;
		while (true) {
			n = in.nextInt();
			
			if (n == 0) {
				break;
			}
			x = y = 0;
			curDeg = 90;
			segments = new ArrayList<Segment>(n);
			
			int deg, dist;
			int died = -1;
			for (int i = 1; i <= n; i++) {
				deg = in.nextInt();
				dist = in.nextInt();
				if (died == -1) {
					curDeg -= deg;
					Segment s = new Segment(curDeg, dist);
					x = s.bx;
					y = s.by;
					if (cut(s)) {
						died = i;
					}
					segments.add(s);
				}
//				System.out.printf("Curr %.2f, %.2f\n", x, y);
			}
			if (died == -1) {
				System.out.println("SAFE");
			} else {
				System.out.println(died);
			}
		}
	}

}
