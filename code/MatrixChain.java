public class MatrixChain {
	public static void main(String[] args) {
		int[] matrix = new int [] {300, 21, 42, 67, 198, 10}; // 3812760
		System.out.println(multiplication((matrix.length - 2), matrix)); }
	public static int multiplication(int size, int matrix[]) {
		int memo[][] = new int[matrix.length - 1][matrix.length - 1];
		int tempMin;
		for(int i = 0;i <= size;i++) memo[i][i] = 0; // Init all diagonals to 0
		for(int i = (size - 1);i >= 0;i--)
			for (int j = (i + 1);j <= size;j++) {
				tempMin = Integer.MAX_VALUE;
				for(int k = i;k < j; k++) { // Add our last memo'd values to the multiplcation permutation of our matrix
					int temp = ((memo[k + 1][j] + memo[i][k]) + matrix[i] * matrix[k] * matrix[j]);
					if(temp < tempMin) tempMin = temp; // Check to see if that is smaller than the previous minimum
				} memo[i][j] = tempMin; }
		return memo[0][size]; } }