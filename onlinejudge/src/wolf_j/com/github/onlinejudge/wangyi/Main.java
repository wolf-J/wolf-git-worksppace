package wolf_j.com.github.onlinejudge.wangyi;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		
		int a;
		
		int [] c = new int[n];
		for (int i = 0; i < n; i++) {
			c[i] = 0;
		}
		while (in.hasNextInt()) {
			a = in.nextInt() - 1;
			c[a] +=1;
		}
		int l = c[0];
		for (int i = 0; i < c.length; i++) {
			l = l < c[i]? l: c[i];
		}

		System.out.println(l);
	}
}
