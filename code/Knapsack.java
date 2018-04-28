public class Knapsack {
	public static void main(String[] args) {
		int sackSize = 45, numItems = 10;
		int[] weights = {7, 19, 8, 5, 14, 23, 1, 4, 9, 11};
		int[] values = {10, 50, 12, 7, 25, 63, 2, 5, 15, 20};
		System.out.println("Test: " + knapsack(numItems, weights, values, sackSize)); }
	public static int knapsack(int n, int[] w, int[] v, int size) {
		int[][] memo = new int[n + 1][size + 1];
		for(int i = 0;i <= size;i++)
			memo[0][i] = 0;
		for(int i = 0;i <= n;i++)
			memo[i][0] = 0;
		for(int i = 1;i <= n;i++)
			for(int j = 1;j <= size;j++) {
				if(j >= w[i - 1])
					memo[i][j] = Integer.max(memo[i - 1][j], 
											(memo[i - 1][j - w[i - 1]] + v[i - 1]));
				else
				   memo[i][j] = memo[i - 1][j]; }
		return memo[n][size]; } }