import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//Matthew Yamout

public class SpreadSheet {

	private static int rows = 10; // int for the amount of rows
	private static int columns = 10; // int for the amount of columns
	public static Cell[][] cells = new Cell[rows][columns]; // creates the 10x10 array for the table

	public static void printDashes() // prints the amount of dashes needed for each line of the table.
	{
		System.out.print("  ");
		for (int i = 1; i <= 111; i++)
		{
			System.out.print("-");
		}
	}

	public void printCells() // prints the individual cells for the table.
	{	
		char c = 'A';
		for (int i = 0; i < rows; i++)
		{
			System.out.print(c+" ");
			for (int j = 0; j < columns; j++)
			{
				//System.out.println(cells[i].length);
				System.out.print("|"+cells[i][j].toString());

			}
			System.out.print("|");
			System.out.println();
			printDashes();
			System.out.println();
			c++;
		}
	}

	public void resetCells() // resets the cells to empty cells.
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				cells[i][j] = new EmptyCell();
			}
		}
	}

	public void printSpreadSheet() // prints the spreadsheet
	{
		System.out.print("  ");
		System.out.print(" 1          ");
		for (int i = 2; i <= 10; i++)
		{
			System.out.print(i+"          ");
		}
		System.out.println();
		printDashes();
		System.out.println();
		printCells();

	}

	public Boolean isCellReference(String value)
	{
		if (Character.isLetter(value.charAt(0)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Cell getCell(String value)
	{
		int row = value.charAt(0) - 'A';
		int col = Integer.parseInt(value.substring(1)) - 1;
		return cells[row][col];	
	}

	public static Boolean isNumber(String number)
	{
		 try
	        {
	            Double.parseDouble(number);
	        }
	        catch (NumberFormatException e)
	        {
	            return false;
	        }
	        return true;

	}
	
	public static Boolean isDate(String date)
	{
		SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
		if (format.parse(date, new ParsePosition(0)) == null)
		{
			return false;
		}
		return true;
	}


	public static void sortA(String valuesToSort, char letterCheck) // does the sorting operation for the program. Take in a character value and a string
	{
		String[] cell = valuesToSort.split(" - "); // splits the cells at the minus sign
		String cell0 = cell[0].trim();
		String cell1 = cell[1].trim();
		char checkLetter = letterCheck; // assigns the inputed character letter
		int row1 = cell[0].charAt(0) - 'A'; // row of the first cell inputed
		int row2 = cell[1].charAt(0) - 'A'; // row of the final cell inputed
		int column1 = Integer.parseInt(cell0.substring(1, cell0.length())) - 1 ; // column of the first cell inputed
		int column2 = Integer.parseInt(cell1.substring(1, cell1.length())) - 1 ; // column of the final cell inputed
		if (row1 == row2) // if row1 = row2 then the user wants to sort a row. Ex A1 - A4.
		{
			if (isNumber(cells[row1][column1].toString())) // checks the value of the first cell inputed and sees if it's a number. 
			{
				ArrayList<Double> numberList = new ArrayList<Double>(); // create an arraylist that's a double
				for (int i = column1; i <= column2; i++) // runs through the multidimensional array and adds the values to the array list
				// i = column1 sets the loop to the column of the first cell and i <= sets the value to the column2 so the loop runs through the inputed cells
				{
					numberList.add(cells[row1][i].getValue()); // arrayList
				}
				Collections.sort(numberList, new Comparator<Double>() // Sorts the arraty list from least the greatest
						{
					public int compare(Double a, Double b) // compare method for the arrayList
					{
						return a.compareTo(b);
					}
						});
				if (checkLetter == 'a') // this checks the letter, since this checks for a then we know we want to sort least to greatest
				{
					for (int i = 0; i < numberList.size(); i ++) // this for loop adds the arrayList back into the multidimensional array
					{
						String input = "" + numberList.get(i);
						cells[row1][column1] = new IntCell(input);
						column1++;
					}
				}
				else if (checkLetter == 'd') // since this check is for the letter d then we know that the user wants to sort by greatest to least
				{
					for (int i = 0; i < numberList.size(); i ++)
					{
						String input = "" + numberList.get(i);
						cells[row1][column2] = new IntCell(input);
						column2--;
					}
				}

			}
			else if (isDate(cells[row1][column1].toString())) // checks to see if the cells to sort are dates
			{
				ArrayList<DateCell> numberList = new ArrayList<DateCell>(); // creates an arrayList that access the date cell
				for (int i = column1; i <= column2; i++)
				{
					numberList.add(cells[row1][i].getDate(cells[row1][i].toDisplayCellString())); // adds the dates to the arrayList
				}
				Collections.sort(numberList, new Comparator<DateCell>(){ // compares the dates
					public int compare(DateCell a, DateCell b){
						return a.compareTo(b);
					}
				});
				if (checkLetter == 'a')
				{
					for (int i = 0; i < numberList.size(); i++)
					{
						DateCell date1 = numberList.get(i);
						cells[row1][column1] = date1;
						column1++;
					}
				}
				else if (checkLetter == 'd')
				{
					for (int i = 0; i < numberList.size(); i++)
					{
						DateCell date1 = numberList.get(i);
						cells[row1][column2] = date1;
						column2--;
					}
				}
			}
			else // Runs it for a text cell
			{
				ArrayList<String> numberList = new ArrayList<String>(); // string arrayList
				for (int i = column1; i <= column2; i++) 
				{
					numberList.add(cells[row1][i].toString());
				}
				Collections.sort(numberList, new Comparator<String>()
						{
					public int compare(String a, String b)
					{
						return a.compareTo(b);
					}
						});
				for (int i = 0; i < numberList.size(); i ++)
				{
					String input = "" + numberList.get(i);
					cells[row1][column1] = new TextCell(input);
					column1++;
				}
			}
		}
		else if (column1 == column2) // if the two columns are equal we know to sort by column. Ex A1 - C1
		{
			if (isNumber(cells[row1][column1].toString())) // same setup as the sorting for rows. Copied and pasted the code and just switch around the for loops a litte
			{
				ArrayList<Double> numberList = new ArrayList<Double>();
				for (int i = row1; i <= row2; i++)
				{
					numberList.add(cells[i][column1].getValue()); // column is going to be the same so move i to the rows
				}
				Collections.sort(numberList, new Comparator<Double>()
						{
					public int compare(Double a, Double b)
					{
						return a.compareTo(b);
					}
						});
				if (checkLetter == 'a')
				{
					for (int i = 0; i < numberList.size(); i ++)
					{
						String input = "" + numberList.get(i);
						cells[row1][column1] = new IntCell(input);
						row1++;
					}
				}
				else if (checkLetter == 'd')
				{
					for (int i = 0; i < numberList.size(); i ++)
					{
						String input = "" + numberList.get(i);
						cells[row2][column1] = new IntCell(input);
						row2--;
					}
				}

			}
			else if (isDate(cells[row1][column1].toString()))
			{
				ArrayList<DateCell> numberList = new ArrayList<DateCell>();
				for (int i = row1; i <= row2; i++)
				{
					numberList.add(cells[i][column1].getDate(cells[i][column1].toDisplayCellString()));
				}
				Collections.sort(numberList, new Comparator<DateCell>(){
					public int compare(DateCell a, DateCell b){
						return a.compareTo(b);
					}
				});
				if (checkLetter == 'a')
				{
					for (int i = 0; i < numberList.size(); i++)
					{
						DateCell date1 = numberList.get(i);
						cells[row1][column1] = date1;
						row1++;
					}
				}
				else if (checkLetter == 'd')
				{
					for (int i = 0; i < numberList.size(); i++)
					{
						DateCell date1 = numberList.get(i);
						cells[row1][column2] = date1;
						row2--;
					}
				}
			}
			else
			{
				ArrayList<String> numberList = new ArrayList<String>();
				for (int i = row1; i <= row2; i++)
				{
					numberList.add(cells[i][column1].toString());
				}
				Collections.sort(numberList, new Comparator<String>()
						{
					public int compare(String a, String b)
					{
						return a.compareTo(b);
					}
						});
				if (checkLetter == 'a')
				{
					for (int i = 0; i < numberList.size(); i ++)
					{
						String input = "" + numberList.get(i);
						cells[row1][column1] = new TextCell(input);
						row1++;
					}
				}
				else if (checkLetter == 'd')
				{
					for (int i = 0; i < numberList.size(); i ++)
					{
						String input = "" + numberList.get(i);
						cells[row2][column1] = new IntCell(input);
						row2--;
					}
				}
			}
		}
	}


	public void setCellWithReference(int row1, int col1, int row2, int col2) // sets a user inputed cell equal to an already inputed cell.
	{
		cells[row1][col1] = cells[row2][col2];
	}

	public void displayCell(int row, int col) // // prints out the contents of a cell inputed by the user.
	{
		System.out.println(cells[row][col].toDisplayCellString());
	}

	public void clearSingleCell(int row, int col) // clears a single cell inputed by the user.
	{
		cells[row][col] = new EmptyCell();
	}
	public void setTextCell(int row, int col, String value) // sets a cell inputed by the user as a text cell.
	{
		cells[row][col] = new TextCell(value);
	}

	public void setDateCell(int row, int col, String value) // sets a cell inputed by the user as a date cell.
	{
		cells[row][col] = new DateCell(value);
	}

	public void setIntCell(int row, int col, String value) // sets a cell inputed by the user as a number cell.
	{
		cells[row][col] = new IntCell(value);
	}

	public void setFormulaCell(int row, int  col, String value) // sets a cell inputed by the user as a formula cell.
	{
		cells[row][col] = new FormulaCell(value);
	}
}
