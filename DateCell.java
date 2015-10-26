import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateCell extends Cell{

	private Date date;
	public int year; // used to compare dates in sorting
	public int day; // used to compare dates in sorting
	public int month; // used to compare dates in sorting
	
	public DateCell(String value) // constructs the date
	{
		SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy"); // creates date format
        date = format.parse(value,new ParsePosition(0)); // creates date 
        String dateCompare = format.format(date); // puts date into a string
        //below is used for comparing dates
        String[] dateCompareOps = dateCompare.split("/"); // splits values at the /
        month = Integer.parseInt(dateCompareOps[0]); // gets value for month
        day = Integer.parseInt(dateCompareOps[1]); // gets value for day
        year = Integer.parseInt(dateCompareOps[2]); // gets value for year
	}
	
	public String toString() // prints the value out to the cell.
	{
		SimpleDateFormat formatToString = new SimpleDateFormat("mm/dd/yyyy");
		return formatToString.format(date);
	}
	
	public double getValue()
	{
		return 0;
	}
	
	public String toDisplayCellString() // displays the cell in the console if asked for the cell value.
	{
		//return date;
		SimpleDateFormat formatToString = new SimpleDateFormat("mm/dd/yyyy");
		return formatToString.format(date);
	}
	
	public DateCell getDate(String date0) // gets the date for the sorting method
	{
		DateCell dateCell = new DateCell(date0);
		return dateCell;
	}

	public int compareTo(DateCell date) // comparing method for sorting dates
	{
		if (year != date.year)
		{
			return year - date.year;
		}
		else if (month != date.month)
		{
			return month - date.month;
		}
		else
		{
			return day - date.day;
		}
	}
}
