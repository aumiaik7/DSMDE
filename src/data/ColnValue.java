package data;

public class ColnValue {
	private int column;
	private double value;
	//constructor
	public ColnValue(int col, double val) {
		// TODO Auto-generated constructor stub
		column = col;
		value = val;
	}
	//return column no
	public int getColumn()
	{
		return column;
	}
	//return index value
	public double getVaue()
	{
		return value;
	}

}
