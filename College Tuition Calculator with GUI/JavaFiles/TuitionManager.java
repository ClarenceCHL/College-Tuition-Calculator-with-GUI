package application;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class allows users to modify and print the list of students enrolled.
 * 
 * @author Haolun Cheng
 */
public class TuitionManager {

	/**
	 * 
	 * Takes user input to add, remove, print, and quit program. The command is
	 * split into tokens using a String Tokenizer constructor to add to remove a
	 * student. Depending on the command, the add or remove method is called with
	 * the respective tokens needed.
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StudentList sl = new StudentList();
		boolean done = false;
		while (!done) {
			String command = sc.nextLine();
			switch (command.charAt(0)) {
			case 'I': {
				StringTokenizer st = new StringTokenizer(command, " ");
				if (st.countTokens() == 5) {
					String tok1 = st.nextToken();
					String tok2 = st.nextToken();
					String tok3 = st.nextToken();
					String tok4 = st.nextToken();
					String tok5 = st.nextToken();
					if (Integer.parseInt(tok4) >= 0) {
						sl.add(new Instate(tok2, tok3, Integer.parseInt(tok4), Integer.parseInt(tok5)));
					}
				}
				break;
			}

			case 'O': {
				StringTokenizer st = new StringTokenizer(command, " ");
				if (st.countTokens() == 5) {
					String tok1 = st.nextToken();
					String tok2 = st.nextToken();
					String tok3 = st.nextToken();
					String tok4 = st.nextToken();
					String tok5 = st.nextToken();
					if (tok5.equals("T")) {
						sl.add(new Outstate(tok2, tok3, Integer.parseInt(tok4), true));
					} else if (tok5.equals("F")) {
						sl.add(new Outstate(tok2, tok3, Integer.parseInt(tok4), false));
					}
				}
				break;
			}
			case 'N': {
				StringTokenizer st = new StringTokenizer(command, " ");
				if (st.countTokens() == 5) {
					String tok1 = st.nextToken();
					String tok2 = st.nextToken();
					String tok3 = st.nextToken();
					String tok4 = st.nextToken();
					String tok5 = st.nextToken();
					if (Integer.parseInt(tok4) >= 9) {

						if (tok5.equals("T")) {
							sl.add(new International(tok2, tok3, Integer.parseInt(tok4), true));
						} else if (tok5.equals("F")) {
							sl.add(new International(tok2, tok3, Integer.parseInt(tok4), false));
						}

					}
				}

			}

			case 'R': {
				StringTokenizer st = new StringTokenizer(command, " ");
				if (st.countTokens() == 3) {
					String tok1 = st.nextToken();
					String tok2 = st.nextToken();
					String tok3 = st.nextToken();
					Student s = new Instate(tok2, tok3, 0, 0);
					sl.remove(s);

				}
				break;
			}

			case 'P':
				if (!sl.isEmpty()) {
					sl.print();
				}
				break;

			case 'Q':
				done = true;
				System.out.println("Program terminated");
				break;
			default:
				System.out.println(command + " not supported");
				break;
			}
		}
	}
}