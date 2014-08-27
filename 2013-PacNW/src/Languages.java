//////////////////////////////////////////////////// BUGGY
import java.util.*;

public class Languages {
	
	static void readToDic(String buf, HashMap<String, ArrayList<String>> dic) {
//		System.out.println("Reading buffer: " + buf);
		String all[] = buf.split("[^a-zA-z'-]");
		ArrayList<String> bucket = new ArrayList<String>();
		for (int i = 1; i < all.length; i++) {
			bucket.add(all[i].toLowerCase());
		}
		dic.put(all[0], bucket);
	}

	static void detect(String ln, HashMap<String, ArrayList<String>> dic) {
//		System.out.println("Detecting: " + ln);
		String words[] = ln.split("[^a-zA-z'-]");
		for (String w : words) {
//			System.out.println(w);
			w = w.toLowerCase();
			// iterate over the dictionary
			for (String lang : dic.keySet()) {
				ArrayList<String> b = dic.get(lang);
				if (b.contains(w)) {
					System.out.println(lang);
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		HashMap<String, ArrayList<String>> dic = new HashMap<String, ArrayList<String>>();
		
		int N = in.nextInt();
		in.nextLine();
		for (int i = 0; i < N; i++) {
			String buf = in.nextLine();
			readToDic(buf, dic);
		}
//		for (String k : dic.keySet()) {
//			System.out.println(" --- " + k);
//			ArrayList<String> b = dic.get(k);
//			for (String t : b) {
//				System.out.print(t + " || ");
//			}
//			System.out.println();
//		}
		in.nextLine();
		while (in.hasNextLine()) {
			String ln = in.nextLine();
			
			detect(ln, dic);
		}
	}

}
