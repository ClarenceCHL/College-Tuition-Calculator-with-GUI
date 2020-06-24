package application;

/**
 * This class defines and implements operations of the students array.
 * 
 * @author Haolun Cheng
 */
public class StudentList {
	private final int GROW_SIZE = 4;
	private final int NOT_FOUND = -1;
	private Student[] studentobjs;
	private int studentsNum;

	/**
	 * Default constructor; initialize the number of students counter to 0 and the
	 * size of the array to 4.
	 */
	public StudentList() {
		this.studentsNum = 0;
		studentobjs = new Student[GROW_SIZE];
	}

	/**
	 * This method grows the length of students array if studentsNum reaches the
	 * maximum capacity of the students array.
	 */
	private void grow() {
		if (studentsNum >= studentobjs.length) {
			Student[] studentsGrow = new Student[studentsNum + GROW_SIZE];
			System.arraycopy(studentobjs, 0, studentsGrow, 0, studentsNum);
			studentobjs = studentsGrow;
			studentsGrow = null;
		}
	}

	/**
	 * This method finds a student s in the students array.
	 * 
	 * @param s a student that needs to be found
	 * @return the index of the student s in the array; otherwise return NOT_FOUND
	 */
	private int find(Student s) {
		for (int i = 0; i < studentsNum; ++i) {
			if (s.compareTo(studentobjs[i]) == 0) {
				return i;
			}
		}
		return NOT_FOUND;
	}

	/**
	 * This method adds a student s to the students array.
	 * 
	 * @param s a student that needs to be added
	 */
	public void add(Student s) {
		if (s.credit > 0) {
			if (find(s) == NOT_FOUND) {
				if (studentobjs.length == studentsNum) {
					grow();
				}
				studentobjs[studentsNum] = s;
				studentsNum++;
			}

		} else {
			System.out.println("Invalid Credit Hours!");
		}
	}

	/**
	 * This method removes a student from the students array.
	 * 
	 * @param s a student that needs to be removed
	 * @return true if the remove operation is succeed, false otherwise
	 */
	public boolean remove(Student s) {
		int posOfStudent = find(s);
		if (posOfStudent != NOT_FOUND) {
			if (posOfStudent == studentsNum) {
				studentobjs[posOfStudent] = null;
			} else {
				System.arraycopy(studentobjs, posOfStudent + 1, studentobjs, posOfStudent, studentsNum - posOfStudent);
			}
			studentsNum--;
			return true;
		}
		return false;
	}

	/**
	 * This method prints the information of each student in the students array.
	 */
	public void print() {
		for (int i = 0; i < studentsNum; ++i) {
			System.out.println(studentobjs[i].toString());
		}
	}

	/**
	 * This method checks if the students array is empty.
	 * 
	 * @return true if the students array is empty, false otherwise
	 */
	public boolean isEmpty() {
		if (studentsNum == 0) {
			return true;
		}
		return false;
	}

	/**
	 * This method checks if a student is in the student list and return a boolean
	 * value.
	 * 
	 * @param s a student that needs to be found
	 * @return true if student s is in the student list, otherwise return false
	 */
	public boolean contains(Student s) {
		if (find(s) != NOT_FOUND) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns the information of each student in the student list.
	 * 
	 * @return the string format of the information of each student in the student
	 *         list
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < studentsNum; ++i) {
			str = str + studentobjs[i].toString() + "\n";
		}
		return str;
	}
}