
public class BinarySearch {
	public static void main(String[] args)
	{
		double  value = 0D, min = 0D, max = 0D;
		double res1 = binarySearch(value, min, max);
	}
	public static double binarySearch(double value, double min, double max){
		double checkValue = (min + max) / 2;
		if(checkValue > value)
			return binarySearch(value,min,checkValue);
		else if(checkValue < value)
			return binarySearch(value,checkValue,max);
		return value; }
}
