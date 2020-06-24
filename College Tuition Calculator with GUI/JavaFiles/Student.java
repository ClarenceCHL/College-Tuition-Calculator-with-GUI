package application;

/**
 * This class creates student object.
 * 
 * @author Haolun Cheng
 */
public abstract class Student implements Comparable<Object> {
	private String fname;
	private String lname;
	protected int credit;

	/**
	 * Default constructor; initialize the fname, lname, and credit of a student
	 * object.
	 * 
	 * @param fname  first name of a student
	 * @param lname  last name of a student
	 * @param credit the number of credits a student has
	 */
	public Student(String fname, String lname, int credit) {
		this.fname = fname;
		this.lname = lname;
		this.credit = credit;
	}

	/**
	 * This method compares two student objects Implement compareTo method because
	 * Student class implements the Comparable Interface
	 * 
	 * @param obj object that needs to be compared with
	 * @return 0 if fname and lname of two student objects are equal, -1 if this
	 *         fname and lname is smaller than obj's, and 1 if this fname and lname
	 *         is bigger than obj's
	 */
	public int compareTo(Object obj) {
		if (obj instanceof Student) {
			Student studentObj = (Student) obj;
			if (this.lname.compareTo(studentObj.lname) > 0) {
				return 1;
			} else {
				if (this.lname.equals(studentObj.lname)) {
					if (this.fname.equals(studentObj.fname)) {
						return 0;
					} else {
						if (this.fname.compareTo(studentObj.fname) > 0) {
							return 1;
						}
					}
				}
			}
		}
		return -1;
	}

	/**
	 * This method returns string formatted by "firstName lastName credits".
	 * 
	 * @return A string with fname, lname and credit hours
	 */
	public String toString() {
		return this.fname + " " + this.lname + " " + this.credit;
	}

	/**
	 * Compute the tuition due; concrete implementation is required in the
	 * subclasses.
	 * 
	 * @return The amount of tuition fee due
	 */
	public abstract int tuitionDue();
}
