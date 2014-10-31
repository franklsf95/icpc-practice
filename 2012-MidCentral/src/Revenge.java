import java.util.*;
import java.io.*;

public class Revenge {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
//		Scanner in = new Scanner(new File("Template.in"));
		
		while (true) {
			// ....
			long a=in.nextLong();
			if (a==0) {
				break;
			}
			int count=0;
			long test=a*a;
			for(int i=1;i<a;i++){
				
				if((test)%i==0&&(test/i-i)%2==0){
					if((test/i-i)/2>a){
//					System.out.println(test/i+" "+i);
					count++;
					}
				}
			}
			System.out.println(count);
			
		}
	}

}
