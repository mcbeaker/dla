//Modified from Pradip Hari - Rutgers CS 101
import java.io.*;

public class IO
{
	private static BufferedReader kb =
		new BufferedReader(new InputStreamReader(System.in));

	public static String readString()
	{
		while (true) {
			try {
				return kb.readLine();
			} catch (IOException e) {
				// shouldn't happen
			}
		}
	}

	public static int readInt()
	{
		while (true) {
			try {
				String s = kb.readLine();
				return Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.print("That is not an integer.  Enter again: ");
			} catch (IOException e) {
				// shouldn't happen
			}
		}
	}

	public static double readDouble()
	{
		while (true) {
			try {
				String s = kb.readLine();
				return Double.parseDouble(s);
			} catch (NumberFormatException e) {
				System.out.print("That is not a number.  Enter again: ");
			} catch (IOException e) {
				// shouldn't happen
			}
		}
	}

	public static char readChar()
	{
		String s = null;

		try {
			s = kb.readLine();
		} catch (IOException e) {
			// shouldn't happen
		}

		while (s.length() != 1) {
			System.out.print("That is not a single character.  Enter again: ");
			try {
				s = kb.readLine();
			} catch (IOException e) {
				// shouldn't happen
			}
		}

		return s.charAt(0);
	}

        public static boolean readBoolean()
        {
                String s = null;
 
                while (true) {
                        try {
                                s = kb.readLine();
                        } catch (IOException e) {
                                // shouldn't happen
                        }
 
                        if (s.equalsIgnoreCase("yes") ||
			    s.equalsIgnoreCase("y") ||
			    s.equalsIgnoreCase("true") ||
			    s.equalsIgnoreCase("t")) {
                                return true;
                        } else if (s.equalsIgnoreCase("no") ||
			           s.equalsIgnoreCase("n") ||
			           s.equalsIgnoreCase("false") ||
			           s.equalsIgnoreCase("f")) {
                                return false;
                        } else {
                                System.out.print("Enter \"yes\" or \"no\": ");
                        }
                }
        }

	
	public static void reportBadInput()
	{
		System.out.println("User entered bad input.");
	}
}
