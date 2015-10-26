
public class TextCell extends Cell{

	private String Value; // global used to take the string.
	private String Value0;
	
	public TextCell(String value) // constructs the string and removes the spaces.
	{
		Value0 = value;
		
		if (value.length() > 10)
		{
			Value = value.substring(0, 10);
		}
		else 
		{
			Value = value;
		}
	}
	
	public String toString() // prints the value out to the cell.
	{
		String returnString = "" + Value;
		int length = returnString.length();
		for( int i = length; i<10; i++ )
		{
			returnString += " ";
		}
		return returnString;
	}
	
	public double getValue()
	{
		return 0;
	}
	public String toDisplayCellString() // displays the cell in the console if asked for the cell value.
	{
		return "\""+Value0+"\"";
	}
}
