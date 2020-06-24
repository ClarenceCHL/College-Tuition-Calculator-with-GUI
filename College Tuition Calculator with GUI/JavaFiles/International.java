package application;

/**
 * Create instance of a international student and calculate the tuition for this
 * student.
 * 
 * @author Haolun Cheng
 */
public class International extends Student {
	private boolean IF_exchange;
	private final int TUITION_PER_CREDIT = 945;
	private final int INTERNATIONAL_STUDENT_FEE = 350;

	/**
	 * Constructor, to create international student object.
	 * 
	 * @param fname       the first name of the student attending.
	 * @param lname       the last name of the student attending.
	 * @param credit      is the total credits of the student at the University.
	 * @param IF_exchange if student is an exchange student or not.
	 */
	public International(String fname, String lname, int credit, boolean IF_exchange) {
		super(fname, lname, credit);
		this.IF_exchange = IF_exchange;
	}

	/**
	 * Checks the student status for part-time student or full-time.
	 * 
	 * @return true if full-time student, otherwise false
	 */
	private boolean isFullTime() {
		if (super.credit >= Tuition.FULL_TIME_MINIMUM_CREDITS) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Compute tuition for the student. This will be based on credits, full time,
	 * funds, university fee.....
	 * 
	 * @return the amount of tuition due.
	 */
	@Override
	public int tuitionDue() {
		int tuitionDue = Tuition.MIN_PAYMENT + INTERNATIONAL_STUDENT_FEE;
		boolean isFullTime = isFullTime();
		if (this.IF_exchange) {
			tuitionDue = Tuition.FEE_FULL_TIME + INTERNATIONAL_STUDENT_FEE;
		}

		else {

			if (super.credit >= Tuition.MAX_CREDITS) {
				tuitionDue = ((TUITION_PER_CREDIT * Tuition.MAX_CREDITS) + INTERNATIONAL_STUDENT_FEE
						+ Tuition.FEE_FULL_TIME);
			} else if (isFullTime && super.credit < Tuition.MAX_CREDITS) {
				tuitionDue = ((TUITION_PER_CREDIT * super.credit) + INTERNATIONAL_STUDENT_FEE + Tuition.FEE_FULL_TIME);

			} else {
				tuitionDue = ((TUITION_PER_CREDIT * super.credit) + Tuition.FEE_PART_TIME + INTERNATIONAL_STUDENT_FEE);
			}

		}
		return tuitionDue;
	}

	/**
	 * Creating toString with first name, last name, credit hours and tuition due.
	 * 
	 * @return formatted String
	 */
	@Override
	public String toString() {
		String str = "";
		str = super.toString();
		str = str + " credit hours," + " International, ";
		if (this.IF_exchange == true) {
			str = str + "Exchange Student" + ", Tuition Due: $" + tuitionDue();
		} else {
			str = str + "Not Exchange Student" + ", Tuition Due: $" + tuitionDue();
		}
		return str; // Format is: first name, last name, credits, Exchange Status, Tuition Due
					// in "$"
	}

	/**
	 * Test bed main
	 * 
	 * @param args argument
	 */
	public static void main(String[] args) {
		System.out.println("This is the test bed main for international.");

		International studentA = new International("Jay", "Chou", 12, true);
		International studentB = new International("Jakie", "Cheng", 9, false);

		System.out.println("Is student A full-time? : " + studentA.isFullTime());
		System.out.println("Is student B full-time? : " + studentB.isFullTime());
		System.out.println("Tuition due for student A : $" + studentA.tuitionDue());
		System.out.println("Tuition due for student B : $" + studentB.tuitionDue());
		System.out.println(studentA.toString());
		System.out.println(studentB.toString());
	}
}
