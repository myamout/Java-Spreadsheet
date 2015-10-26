//Matthew Yamout 

import java.util.*;

public class Visicalcmain {

	public static int row; // global used to set the row of a cell.
	public static int col; // global used to set the column of a cell.
	public static String value; // string value used to parse the contents of a cell.
	public static int value1; // int value used to parse the contents of a cell.
	public static SpreadSheet spr = new SpreadSheet(); // global class
	public static boolean error = true; // boolean for error checking

	public static void resetGlobals() // resets global variables
	{
		row = 0;
		col = 0;
		value = "";
		value1 = 0;
	}

	public static void textCellCheck(String test) // checks if a text cell is inputed correctly
	{
		if (test.charAt(0) == '\"' && !test.endsWith("\""))
		{
			error = false;
		}
		if (!((test.charAt(0)) == '\"') && test.endsWith("\""))
		{
			error = false;
		}
		if (Character.isLetter(test.charAt(0)))
		{
			error = false;
		}

	}

	public static void checkUpperCase(String test) // checks to make sure that the cell reference is entered in upper case
	{
		if (!(Character.isUpperCase(test.charAt(0))))
		{
			error = false;
		}
	}

	public static void formulaCellCheck(String test) // checks to make sure that formula cells are inputed correctly
	{
		int almostEndPoint = test.length() - 2;
		int endPoint = test.length() - 1;
		
		if (test.charAt(0) == '(' && !test.endsWith(")"))
		{
			error = false;
		}
		if (!(test.charAt(0) == '(') && test.endsWith(")"))
		{
			error = false;
		}
		if (!(test.contains("+") || !(test.contains("-")) || !(test.contains("*")) || !(test.contains("/"))))
		{
			error = false;
		}
		if (test.charAt(0) == '(' && !(test.charAt(1) == ' '))
		{
			error = false;
		}
		if (test.charAt(endPoint) == ')' && !(test.charAt(almostEndPoint) == ' '))
		{
			error = false;
		}
	}

	public static void displayCellCheck1(String test) // checks to make sure that the cell reference is accessed correctly
	{
		if (Character.isLetter(test.charAt(0)))
		{
			if (Character.isDigit(test.charAt(1)))
			{
				int letter = test.charAt(0) - 'A';
				int number = Integer.parseInt(test.substring(1));
				if (number > 10)
				{
					error = false;
				}
				if (letter > 9)
				{
					error = false;
				}
			}
			else
			{
				error = false;
			}
		}
		else
		{
			error = false;
		}
	}

	public static void checkSort(String test) // checks to make sure that the sort function is inputed correctly
	{
		String[] check = test.split(" ");
		String sortCheck = check[0].trim();
		if (sortCheck.endsWith("a") || sortCheck.endsWith("d"))
		{
			error = true;
		}
		else
		{
			error = false;
		}
	}

	public static void displayCellCheck2(String test) // checks more cell reference input
	{
		if (!(test.contains(":")))
		{
			error = false;
		}
	}

	public static void assignmentCellCheck(String test) // checks to make sure that the user assigns the cell correctly
	{
		if (Character.isLetter(test.charAt(0)))
		{
			if (Character.isDigit(test.charAt(1)))
			{
				int letter = test.charAt(0) - 'A';
				int number = Integer.parseInt(test.substring(1));
				if (number > 10)
				{
					error = false;
				}
				if (letter > 9)
				{
					error = false;
				}
			}
			else
			{
				error = false;
			}
		}
		else
		{
			error = false;
		}
	}
	
	public static void spaceCheck(String test) // checks for error if user just hits enter
	{
		if (!(test.contains(" ")))
		{
			error = false;
		}
	}


	public static void helpGuide() // prints out the list of available commands.
	{
		System.out.print("\t\t\t\tAVAILABLE COMMANDS\n");
		System.out.print("-------------------------------------------------------------------------------\n");
		System.out.print("Typing in quit will exit the program.\n");
		System.out.print("Typing in help will display the legal commands of the program.");
		System.out.print("<Cell Reference> = <Value>\n Use the above method to assign values to cells.\n");
		System.out.print("<Cell Reference> :\n Use the above method to display the contents of a cell.\n");
		System.out.print("Type in clear to delete the contents of all cells and start from scratch.\n");
		System.out.print("sorta <Cell Range>\n Use the above method to sort cells in accending order.\n");
		System.out.print("sortd <Cell Range>\n Use the above method to sort cells in decending order.\n");
		System.out.print("--------------------------------------------------------------------------------\n");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("USER GUIDE\n"); // user guide for the program.
		System.out.print("--------------\n");
		System.out.print("To quit the program type in \"quit\"\n");
		System.out.print("Type in \"print\" to print out the table\n");
		System.out.print("To get started type in \"print\" to obtain a blank spreadsheet\n");
		System.out.print("To assign a value to a cell type in the desired cell and have it equal the value, example below...\n");
		System.out.print("A1 = 12345\nB2 = \"Hello World!\"\n");
		System.out.print("To clear a cell type in \"clear\" followed with the desired cell, exmaple below...\n");
		System.out.print("clear A1\n");
		System.out.print("To clear an entire spreadsheet simply type in \"clear\"\n");
		System.out.print("KEY NOTES\n");
		System.out.print("----------\n");
		System.out.print("All sentence based type must be enclosed with quotations.\n");
		System.out.print("All numbers entered must be real numbers.\n");
		System.out.print("Dates must be entered in the following order...\n");
		System.out.print("mm/dd/yyyy\n");
		System.out.print("All mathematical formulas must be enclosed with quotations\n");
		System.out.print("To make cells equal to other cells...\n");
		System.out.print("B1 = (A1)\n");
		System.out.print("Cells can be used in mathematical equations if the said cell contains numbers\n");
		System.out.print("That's the user guide, enjoy the program and happy coding!\n");

		Scanner console = new Scanner(System.in); // scanner to get input.
		boolean quit = true; // boolean for the do while loop.
		System.out.println("Type in 'help' to bring up the list of legal commands, otherwise happy spreadsheeting!");
		//SpreadSheet spr = new SpreadSheet(); // intializes the spreadsheet class.
		spr.resetCells(); // resets the cells to empty.
		do // start of do while loop
		{
			System.out.print("Command: ");
			String command = console.nextLine(); // scanner
			if (command.equalsIgnoreCase("quit")) // quits program
			{
				System.out.print("Thank you for using this version of VisiCalc!");
				quit = false;
			}
			else if (command.equalsIgnoreCase("help")) // prints help guide
			{
				helpGuide();
			}
			else if (command.contains("="))
			{
				String[] commandLine = command.split(" = "); // splits the cells at the equals.
				String command0 = commandLine[0]; // cell the user wants to add stuff to. Ex. A1.
				String command1 = commandLine[1]; // contents of the cell. Ex. 12345.

				command0 = command0.trim(); // removes extra spaces.
				command1 = command1.trim();
				if (error == true) // error checking
				{
					assignmentCellCheck(command0);
					textCellCheck(command1);
					formulaCellCheck(command1);
					checkUpperCase(command0);
					if (error == false)
					{
						System.out.println("ERROR");
						error = true;
					}
					else // if error checking passes then parsing is allowed
					{
						row = command0.charAt(0) - 'A'; // obtains the row and stores as an int value.
						col = Integer.parseInt(command0.substring(1)) - 1; // obtains the column and stores as an int value.
						if (command1.charAt(0) == '\"' && command1.endsWith("\"")) // sets up parse if the string starts with a quotation.
						{
							value = command1.substring(1, command1.length() - 1);
							spr.setTextCell(row, col, value); // intializes the text cell.
							resetGlobals();
						}
						else if (command1.length() == 10 && command1.charAt(2) == '/' && command1.charAt(5) == '/') // sets date cell.
						{
							value = command1;
							spr.setDateCell(row, col, value); // initializes a date cell.
							resetGlobals();
						}
						else if (command1.startsWith("(") && command1.endsWith(")") && Character.isLetter(command1.charAt(2))
								&& !(command1.contains("+")) && !(command1.contains("-")) && !(command.contains("*"))
								&& !(command1.contains("/"))) // sets up a cell with contents based on an already inputed cell.
						{
							String cellReference = command1.substring(2, command1.length() - 2);
							int row2 = cellReference.charAt(0) - 'A';
							int col2 = Integer.parseInt(cellReference.substring(1)) - 1;
							spr.setCellWithReference(row, col, row2, col2); // initialzies the reference cell.
							resetGlobals();
						}
						else if (command1.charAt(0) == '-') // allows for negative numbers
						{
							value = command1;
							spr.setIntCell(row, col, command1);
							resetGlobals();
						}
						else if (command1.charAt(0) == '(' && command1.contains("+") || command1.contains("-") || command1.contains("/") || command1.contains("*")) // sets up formula cell
						{
							spr.setFormulaCell(row, col, command1);
							resetGlobals();
						}
						else if (Character.isDigit(command1.charAt(0))) // regular number cell
						{
							value = command1;
							spr.setIntCell(row, col, value);
							resetGlobals();
						}
						
					}
				}
			}

			else if (command.contains(":")) // sets up parse for displaying the contents of a cell.
			{
				String[] commandLine = command.split(" "); // splits at the space
				String command0 = commandLine[0]; // parse the cell string
				String command1 = commandLine[1];
				command0 = command0.trim(); // trims out extra spaces
				command1 = command1.trim();
				// error checking for displaying cell contents
				displayCellCheck1(command0);
				checkUpperCase(command0);
				displayCellCheck2(command1);
				if (error == false)
				{
					System.out.println("ERROR");
					error = true;
				}
				else // if error checking passes cell display parsing goes through
				{
					row = command0.charAt(0) - 'A'; // parses row
					col = Integer.parseInt(command0.substring(1)) - 1; // parses column
					spr.displayCell(row, col); // sets up a the display method.
				}
			}
			else if (command.startsWith("clear") && command.length() > 5) // clears a user inputed cell.
			{
				if (!(command.contains(" ")))
				{
					System.out.println("ERROR");
				}
				else
				{
					String[] commandLine = command.split(" ");
					String command1 = commandLine[1];
					command1 = command1.trim();
					row = command1.charAt(0) - 'A';
					col = Integer.parseInt(command1.substring(1)) - 1;
					spr.clearSingleCell(row, col);
				}
			}
			else if (command.startsWith("sort")) // sorting call
			{
				checkSort(command); // error checking for sorting
				if (error == false)
				{
					System.out.println("ERROR");
					error = true;
				}
				else // if error checking passes then sorting is allowed
				{
					char command0 = command.charAt(4);
					String command1 = command.substring(6, command.length());
					SpreadSheet.sortA(command1, command0);
				}
			}
			else if (command.equalsIgnoreCase("clear")) // resets all cells.
			{
				spr.resetCells();
			}
			else if (command.equalsIgnoreCase("print")) // prints out the spreadsheet.
			{
				spr.printSpreadSheet();
				System.out.println();
			}
			else // allows for easy error checking :D
			{
				System.out.println("ERROR");
			}

		}while (quit);
	}
}


