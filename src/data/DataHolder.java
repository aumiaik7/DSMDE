package data;

public class DataHolder {
	
	private int coordOrArray;
	private int rows,columns;
	private int nonZero;
	
	//set the flag for array or coordinate 
	public void setCoordOrArray(int ca)
	{
		coordOrArray = ca;
	}
	//return the flag for array or coordinate  
	public int getCoordOrArray()
	{
		return coordOrArray;
	}
	//set number of rows in matrix
	public void setRows(int r)
	{
		rows = r;
	}
	//set number of columns in matrix
	public void setCols(int c)
	{
		columns = c;
	}
	//set number of nonZero entries in matrix
	public void setNzN(int n)
	{
		nonZero = n;
	}
}
