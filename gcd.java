public class gcd
{
    public static void main(String[] args)
    {
        System.out.println("GCD of 0 and 0 is " + gcd(0, 0));
    }
    
    private static int gcd(int a, int b)
    {
        if(b == 0)
            return a;
        
        return gcd(b, (a % b));
    }
}