//Matthew Yamout 

public abstract class Cell {

	public Cell() // constructor that isn't needed :D
	{
		
	}
	public double getValue()
	{
		return 0;
	}
	public String toString() // used for printing cell values into the table
	{
		return "";
	}

	public DateCell getDate(String date0) // get date method. date is set to a value, but is always overwritten
	{
		DateCell date = new DateCell("1/01/2016");
		return date;
	}
	public String toDisplayCellString() // used for cell reference printing
	{
		return "";
	}
}
