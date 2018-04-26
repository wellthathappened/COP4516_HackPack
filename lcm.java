public class lcm
{
    public static void main(String[] args)
    {
        System.out.println("LCM of 134 and 285 is " + lcm(134, 285));
    }
    
    public static int lcm(int a, int b)
    {
        int temp;
        
        if(a > b)
            temp = a;
        
        else
            temp = b;
        
        while(true)
        {
            if(((temp % a) == 0) && ((temp % b) == 0))
                return temp;
            
            ++temp;
        }	
    }
}