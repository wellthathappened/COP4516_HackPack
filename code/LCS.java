public class LCS {
	public static void main(String[] args) {
		System.out.println(lcs("TEST1ING2","TEST233333ING1")); }
	public static int lcs(String a, String b) {
		int x = a.length();
		int y = b.length();
		int memo[][] = new int[x + 1][y + 1];
		for(int i = 0;i <= x;i++)
			for(int j = 0;j <= y;j++) {
				memo[i][0] = 0;
				memo[0][j] = 0; }
		for (int i = 1;i <= x;i++) {
			for (int j = 1;j <= y;j++) {
				if (a.charAt(i - 1) != b.charAt(j - 1))
					memo[i][j] = Integer.max(memo[i][j - 1], memo[i - 1][j]);
				else memo[i][j] = memo[i - 1][j - 1] + 1; } }
		return memo[x][y]; } }