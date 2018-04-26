public class permutations
{
    public static void main(String[] args)
    {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        perms(a);
    }
    
    public static int[][] perms(int [] a)
    {
        int[][] temp = new int[a.length][a.length];
        
	// Match every element in array with every other element including itself
        for(int i = 0;i < a.length;i++)
        {
            for(int j = 0;j < a.length;j++)
            {
                temp[i][j] = (a[i] + a[j]);
                
                //System.out.print(i + ": " + temp[i][j] + ", ");
            }
            
            //System.out.println();
        }
        
        return temp;
    }
}