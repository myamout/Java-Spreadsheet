
public class IntCell extends Cell{
	
	private double Value; // global used to set the string
	
	public IntCell(String value)
	{
		Value = Double.parseDouble(value);
	}
	
	public String toString() // constructs the cell and prints the inputed into the table.
	{
		String returnString = "" + Value;
		int length = returnString.length();
		for( int i = length; i<10; i++ )
		{
			returnString += " ";
		}
		return returnString;
	}
	
	public double getValue() // used to return the value of the cell
	{
		return Value;
	}
	
	public String toDisplayCellString() // displays the contents of the cell if the user asks.
	{
		return ""+ Value;
	}
}
