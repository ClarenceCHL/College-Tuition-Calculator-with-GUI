package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.Event;

/**
 * This class defines Add, Remove, and Print operations for the GUI interface,
 * allowing users to modify the student enrolling status.
 * 
 * @author Haolun Cheng
 */
public class Controller {
	StudentList sl = new StudentList();

	@FXML
	private RadioButton instateRadio;

	@FXML
	private RadioButton outstateRadio;

	@FXML
	private RadioButton internationalRadio;

	@FXML
	private TextField fname;

	@FXML
	private TextField lname;

	@FXML
	private TextField credits;

	@FXML
	private TextArea outputText;

	@FXML
	private CheckBox exchange;

	@FXML
	private CheckBox tristate;

	@FXML
	private CheckBox funding;

	@FXML
	private TextField fundAmount;

	@FXML
	private Button add;

	@FXML
	private Button remove;

	@FXML
	private Button print;

	/**
	 * This method modifies the status of other buttons/check-boxes when the radio
	 * button Out-State is selected.
	 * 
	 * @param evt happens when the radio button Out-State is selected
	 */
	@FXML
	public void outstateOption(Event evt) {
		exchange.setDisable(true);
		funding.setDisable(true);
		tristate.setDisable(false);
		fundAmount.setEditable(false);
		fundAmount.setVisible(false);
		funding.setSelected(false);
		exchange.setSelected(false);
	}

	/**
	 * This method modifies the status of other buttons/check-boxes when the radio
	 * button In-State is selected.
	 * 
	 * @param evt happens when the radio button In-State is selected
	 */
	@FXML
	public void instateOption(Event evt) {
		exchange.setDisable(true);
		tristate.setDisable(true);
		funding.setDisable(false);
		fundAmount.setEditable(true);
		tristate.setSelected(false);
		exchange.setSelected(false);
	}

	/**
	 * This method modifies the status of other buttons/check-boxes when the radio
	 * button International is selected.
	 * 
	 * @param evt happens when the radio button International is selected
	 */
	@FXML
	public void internationalOption(Event evt) {
		tristate.setDisable(true);
		funding.setDisable(true);
		exchange.setDisable(false);
		fundAmount.setEditable(false);
		fundAmount.setVisible(false);
		funding.setSelected(false);
		tristate.setSelected(false);
	}

	/**
	 * This method makes sure that the funding amount TextField is visible only when
	 * the check-box Fund is selected. It also clears the input text in the
	 * TextField when we cancel the Fund selection.
	 * 
	 * @param evt whether or not the Fund check-box is selected
	 */
	@FXML
	public void fundOption(Event evt) {
		if (funding.isSelected()) {
			fundAmount.setVisible(true);
		} else {
			fundAmount.clear();
			fundAmount.setVisible(false);
		}
	}

	/**
	 * This method defines the operations of the GUI interface when the button Add
	 * is clicked.
	 * 
	 * @param evt happens when the Add button is clicked
	 */
	@FXML
	public void addOperation(Event evt) {
		String FName = fname.getText();
		String LName = lname.getText();
		Integer creditsNum;
		Integer fundsNum;

		if (FName.isEmpty() || LName.isEmpty()) {
			String errorFname = "Please enter first name and last name \n";
			outputText.appendText(errorFname);
		} else {
			try {
				creditsNum = Integer.parseInt(credits.getText());
				if (instateRadio.isSelected()) {
					if (funding.isSelected()) {
						if (creditsNum >= Tuition.FULL_TIME_MINIMUM_CREDITS) {
							try {
								fundsNum = Integer.parseInt(fundAmount.getText());
								if (fundsNum < 0) {
									outputText.appendText("Funding cannot be less than 0! \n");
								} else {
									if (addCheck(FName, LName) == false) {
										sl.add(new Instate(FName, LName, creditsNum, fundsNum));
										outputText.appendText("Instate student " + FName + " " + LName + " with funds $"
												+ fundsNum + " is added \n");
									} else {
										outputText.appendText(
												"Student " + FName + " " + LName + " is already in the list \n");
									}
								}
							} catch (NumberFormatException exception) {
								fundsNum = null;
							}
							if (fundsNum == null) {
								outputText.appendText("If you choose fund, you much enter a valid fund amount! \n");
							}
						} else {
							outputText.appendText("Part time In-State students are not eligible for the funding \n");
						}
					} else {
						if (addCheck(FName, LName) == false) {
							if (creditsNum > 0) {
								sl.add(new Instate(FName, LName, creditsNum, 0));
								outputText.appendText(
										"Instate student " + FName + " " + LName + " without funds is added \n");
							}
						} else {
							outputText.appendText("Student " + FName + " " + LName + " is already in the list \n");
						}
					}
				}

				else if (outstateRadio.isSelected()) {
					if (tristate.isSelected()) {
						if (addCheck(FName, LName) == false) {
							sl.add(new Outstate(FName, LName, creditsNum, true));
							outputText.appendText(
									"Outstate student " + FName + " " + LName + " with tri-state status is added \n");
						} else {
							outputText.appendText("Student " + FName + " " + LName + " is already in the list \n");
						}
					} else {
						if (addCheck(FName, LName) == false) {
							sl.add(new Outstate(FName, LName, creditsNum, false));
							outputText.appendText("Outstate student " + FName + " " + LName
									+ " without tri-state status is added \n");
						} else {
							outputText.appendText("Student " + FName + " " + LName + " is already in the list \n");
						}
					}
				} else if (internationalRadio.isSelected()) {
					if (exchange.isSelected()) {
						if (creditsNum < 9) {
							outputText.appendText("International students cannot have less than 9 credits \n");
						} else {
							if (addCheck(FName, LName) == false) {
								sl.add(new International(FName, LName, creditsNum, true));
								outputText.appendText(
										"International exchange student " + FName + " " + LName + " is added \n");
							} else {
								outputText.appendText("Student " + FName + " " + LName + " is already in the list \n");
							}
						}
					} else {
						if (creditsNum < 9) {
							outputText.appendText("International students cannot have less than 9 credits \n");
						} else {
							if (addCheck(FName, LName) == false) {
								sl.add(new International(FName, LName, creditsNum, false));
								outputText.appendText(
										"International non-exchange student " + FName + " " + LName + " is added \n");
							} else {
								outputText.appendText("Student " + FName + " " + LName + " is already in the list \n");
							}
						}
					}

				} else {
					outputText.appendText("Please choose your status! \n");
				}

			} catch (NumberFormatException exception) {
				creditsNum = null;
			}

			if (creditsNum == null) {
				outputText.appendText("Invalid Credits Input: Credits must be a integer number! \n");
			} else if (creditsNum <= 0) {
				outputText.appendText("Invalid Credits Input: Credits must be more than 0! \n");
			}
		}
	}

	/**
	 * This method defines the operations of the GUI interface when the button
	 * Remove is clicked.
	 * 
	 * @param evt happens when the Remove button is clicked
	 */
	@FXML
	public void removeOperation(Event evt) {
		String FName = fname.getText();
		String LName = lname.getText();

		if (!(FName.isEmpty() || LName.isEmpty())) {
			Student s = new Instate(FName, LName, 0, 0);
			if (sl.contains(s) == true) {
				sl.remove(s);
				outputText.appendText("Student " + FName + " " + LName + " is removed! \n");
			} else {
				outputText.appendText("Student is not in the student list! \n");
			}
		} else {
			if (FName.isEmpty() || LName.isEmpty()) {
				outputText.appendText("Please enter first name and last name. \n");
			}

		}

	}

	/**
	 * This method defines the operations of the GUI interface when the button Print
	 * is clicked.
	 * 
	 * @param evt happens when the Print button is clicked
	 */
	@FXML
	public void printOperation(Event evt) {
		if (!sl.isEmpty()) {
			outputText.appendText(sl.toString());
		} else {
			outputText.appendText("The student list is empty! \n");
		}
	}

	/**
	 * This method checks if a student is already in the student list.
	 * 
	 * @param FName first name of the student
	 * @param LName last name of the student
	 * @return true if student s in already in the student list, false otherwise
	 */
	public boolean addCheck(String FName, String LName) {
		Student s = new Instate(FName, LName, 0, 0);
		if (sl.contains(s) == true) {
			return true;
		}
		return false;
	}

}
