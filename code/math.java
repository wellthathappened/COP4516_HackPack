public class math {
	public static void main(String[] args) {
		System.out.println(gcd(960, 360)); // 120
		System.out.println(lcm(134, 285)); // 38190
	}
	public static int lcm(int a, int b) {
		return a * b / gcd(a, b); // Beware of overflow
	}
	public static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
	public static int gcd2(int a, int b) {
		int temp;
		while (b != 0) {
			temp = a;
			a = b;
			b = temp % b;
		}
		return a;
	}
}