public class combinations
{
    public static void main(String[] args)
    {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        combos(a);
    }
    
    public static int[][] combos(int [] a)
    {
	// Ensure we're not storing duplicates
        HashSet<Integer> checked = new HashSet<>();
        
        int[][] temp = new int[a.length][a.length];
        
	// Pair every element with every other element in the array once
        for(int i = 0;i < a.length;i++)
        {
            for(int j = 0;j < a.length;j++)
                if(checked.add(a[i] + a[j]))
                {
                    temp[i][j] = (a[i] + a[j]);
                   
                    //System.out.print(i + ": " + temp[i][j] + ", ");
                }
            
            //System.out.println();
        }
        
        return temp;
    }
}