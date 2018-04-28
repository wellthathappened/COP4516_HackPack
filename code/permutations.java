public class permutations {
	public static void main(String[] args) {
		int[] values = {1, 2, 3, 4};
		int[] perm = new int[values.length];
		boolean[] used = new boolean[values.length];        // Also consider: Set<T>
		perms(values, perm, used, 0); }
	public static void perms(int[] values, int[] perm, boolean[] used, int depth) {
		if (depth == values.length) {                       // Process a permutation
			System.out.println(java.util.Arrays.toString(perm));
			return; }
		for(int i = 0; i < values.length; i++) {            // Iterate over unused elements
			if (!used[i]) {                                 // Note: for combinations, omit. For derrangements, check i != depth
				used[i] = true;                             // Mark as used
				perm[depth] = values[i];                    // Use the element
				perms(values, perm, used, depth + 1);       // Recurse one level deeper
				used[i] = false; } } } }                    // Unmark as used