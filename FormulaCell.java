//Matthew Yamout Period 2

public class FormulaCell extends Cell{

	public double Value = 0;
	public String Value0;
	
	@SuppressWarnings("static-access") //allows the static stuff to work properly with the rest of my code, mainly with the sorting
	public FormulaCell(String value)
	{
		
		String input = value.substring(2, value.length() - 2); // optains the string to parse
		Value0 = value; // used for the display cell method
		String[] parts = input.split(" "); // splits at spaces
		String operator = "=";
		double partValue = 0; // running value for adding
		for(int i = 0; i < parts.length; i++)
		{
			//is reference or operator
			if(i % 2 == 0)
			{
				//looking for reference
				if( Visicalcmain.spr.isCellReference(parts[i]))  // checks if character at i is a cell reference
				{
					Cell refCell = Visicalcmain.spr.getCell(parts[i]);
					partValue = refCell.getValue();
				}
				//looking for value
				if( Visicalcmain.spr.isNumber(parts[i])) // checks if character at i is a number
				{
				    partValue = parseValue(parts[i]); // sets the running part to the new value
				}
				if(Visicalcmain.spr.isDate(parts[i])) // if the cell inputed add zero to prevent the program from crashing
				{
					partValue = 0;
				}
				if( operator.equals( "=" )) // does operations
				{
					Value = partValue;
				}
				if( operator.equals( "+" ) )
				{
					Value += partValue;
				}
				if (operator.equals("-"))
				{
					Value -= partValue;
				}
				if (operator.equals("*"))
				{
					Value *= partValue;
				}
				if (operator.equals("/"))
				{
					Value /= partValue;
				}
				
				

			}
			else
			{
				//stores operator
				operator = parts[i];
			}
			//if reference do operation
			//if operator store operator
		}
		
	}
	
	public double getValue()
	{
		return 0;
	}
	
	public double parseValue(String value) // parse the number into a double
	{
		double parse = Double.parseDouble(value);
		return parse;
	}
	
	public String toString() // prints the value into the cell
	{
		String returnString = "" + Value;
		int length = returnString.length();
		for( int i = length; i<10; i++ )
		{
			returnString += " ";
		}
		return returnString;
	}
	
	public String toDisplayCellString() // prints out the formula used to the user if requested
	{
		return ""+ Value0;
	}
	
}
