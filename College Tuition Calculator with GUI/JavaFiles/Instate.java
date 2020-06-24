package application;

/**
 * Creates instance of a in-state student and calculate the tuition for this
 * student.
 * 
 * @author Haolun Cheng
 */
public class Instate extends Student {
	private int funds;
	private final int TUITION_PER_CREDIT = 433;
	private final int NO_FUNDS = 0;

	/**
	 * Constructor, to create out-of-state student object.
	 * 
	 * @param fname  the first name of the student attending.
	 * @param lname  the last name of the student attending.
	 * @param credit the total credits of the student at the University.
	 * @param funds  the amount of fund that a student receives.
	 */
	public Instate(String fname, String lname, int credit, int funds) {
		super(fname, lname, credit);

		if (funds >= NO_FUNDS) {
			this.funds = funds;
		} else {
			this.funds = NO_FUNDS;
		}

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
		int tuitionDue = Tuition.MIN_PAYMENT;
		boolean isFullTime = isFullTime();

		if (super.credit >= Tuition.MAX_CREDITS) {
			tuitionDue = ((TUITION_PER_CREDIT * Tuition.MAX_CREDITS) + Tuition.FEE_FULL_TIME - this.funds);
		} else if (isFullTime && super.credit < Tuition.MAX_CREDITS) {
			tuitionDue = ((TUITION_PER_CREDIT * super.credit) + Tuition.FEE_FULL_TIME - this.funds);

		} else {
			tuitionDue = ((TUITION_PER_CREDIT * super.credit) + Tuition.FEE_PART_TIME);
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
		str = str + " credit hours," + " Instate" + " Funds: $" + this.funds + ", Tuition Due: $" + tuitionDue();
		return str; // Format is: first name, last name, credits, status, Funds, Tuition Due in $

	}

	/**
	 * Test bed main
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		System.out.println("This is the test bed main for instate.");

		Instate studentA = new Instate("Jady", "Tian", 12, 1000);
		Instate studentB = new Instate("Clarence", "Cheng", 8, 1000);

		System.out.println("Is student A full-time? : " + studentA.isFullTime());
		System.out.println("Is student B full-time? : " + studentB.isFullTime());
		System.out.println("Tuition due for student A : $" + studentA.tuitionDue());
		System.out.println("Tuition due for student B : $" + studentB.tuitionDue());
		System.out.println(studentA.toString());
		System.out.println(studentB.toString());
	}

}
