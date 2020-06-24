package application;

/**
 * Create instance of a out-of-state student and calculate the tuition for this
 * student.
 * 
 * @author Haoulun Cheng
 */
public class Outstate extends Student {
	private boolean tristate;
	private final int TUITION_PER_CREDIT = 756;
	private final int TUITION_TRI_STATE_DISCOUNT = 200;

	/**
	 * Constructor, to create out-of-state student object.
	 * 
	 * @param fname    the first name of the student attending
	 * @param lname    the last name of the student attending
	 * @param credits  is the total credits the student is taking
	 * @param tristate checks if the student is eligible for discount of $200 per
	 *                 credit
	 */
	public Outstate(String fname, String lname, int credits, boolean tristate) {
		super(fname, lname, credits);
		this.tristate = tristate;
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
	 * Calculate student tuition. This will be based on credits, full time,
	 * tri-state, university fee.
	 * 
	 * @return the tuition amount due
	 */
	@Override
	public int tuitionDue() {
		int tuitionDue = Tuition.MIN_PAYMENT;
		boolean isFullTime = isFullTime();
		if (super.credit >= Tuition.MAX_CREDITS && tristate) {
			tuitionDue = (((TUITION_PER_CREDIT - TUITION_TRI_STATE_DISCOUNT) * Tuition.MAX_CREDITS)
					+ Tuition.FEE_FULL_TIME);
		} else if (super.credit >= Tuition.MAX_CREDITS && !tristate) {
			tuitionDue = ((TUITION_PER_CREDIT * Tuition.MAX_CREDITS) + Tuition.FEE_FULL_TIME);
		} else if (isFullTime && super.credit < Tuition.MAX_CREDITS && tristate) {
			tuitionDue = (((TUITION_PER_CREDIT - TUITION_TRI_STATE_DISCOUNT) * super.credit) + Tuition.FEE_FULL_TIME);

		} else if (isFullTime && super.credit < Tuition.MAX_CREDITS && !tristate) {
			tuitionDue = ((TUITION_PER_CREDIT * super.credit) + Tuition.FEE_FULL_TIME);
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
		str = str + " credit hours," + " Outstate,";
		if (this.tristate == true) {
			str = str + " Tristate," + " Tuition Due: $" + tuitionDue();
		} else {
			str = str + " Not Tristate," + " Tuition Due: $" + tuitionDue();
		}
		return str; // Format is: first name, last name, credits, status, Tri-state status, Tuition
					// Due in $
	}

	/**
	 * Test bed main
	 * 
	 * @param args argument
	 */
	public static void main(String[] args) {
		System.out.println("This is the test bed main for outstate.");

		Outstate studentA = new Outstate("Peter", "Pan", 12, true);
		Outstate studentB = new Outstate("John", "Smith", 8, false);

		System.out.println("Is student A full-time? : " + studentA.isFullTime());
		System.out.println("Is student B full-time? : " + studentB.isFullTime());
		System.out.println("Tuition due for student A : $" + studentA.tuitionDue());
		System.out.println("Tuition due for student B : $" + studentB.tuitionDue());
		System.out.println(studentA.toString());
		System.out.println(studentB.toString());
	}
}
