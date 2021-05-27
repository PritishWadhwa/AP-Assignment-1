/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Name:		Pritish Wadhwa													 *
 * RollNumber: 	2019440															 *
 * Section:		A																 *
 * Stream:		CSE																 *
 * Topic:		Assignment 1													 *
 * SubTopic:	Main Class for Assignment 1										 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package AP_Assignment1;

import java.util.Scanner;

public class Assignment1_Main {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		// inputting the number of patients
		int numberOfPatients = input.nextInt();
		int numberOfPatientsAdmitted = 0;

		HealthCoordinator healthCoordinator = new HealthCoordinator();

		// inputting the patients
		for (int i = 0; i < numberOfPatients; i++) {
			createPatient(healthCoordinator);
		}

		while (numberOfPatientsAdmitted < numberOfPatients) {

			int queryNumber = input.nextInt();

			if (queryNumber == 1) {
				int c = addressQuery1(healthCoordinator);
				numberOfPatientsAdmitted += c;
			} else if (queryNumber == 2) {
				addressQuery2(healthCoordinator);
			} else if (queryNumber == 3) {
				addressQuery3(healthCoordinator);
			} else if (queryNumber == 4) {
				addressQuery4(healthCoordinator);
			} else if (queryNumber == 5) {
				addressQuery5(healthCoordinator);
			} else if (queryNumber == 6) {
				String name = input.next();
				addressQuery6(healthCoordinator, name);
			} else if (queryNumber == 7) {
				int id = input.nextInt();
				addressQuery7(healthCoordinator, id);
			} else if (queryNumber == 8) {
				addressQuery8(healthCoordinator);
			} else if (queryNumber == 9) {
				String name = input.next();
				addressQuery9(healthCoordinator, name);
			}

		}
		input.close();
	}

	public static void createPatient(HealthCoordinator healthCoordinator) {
		// inputting the values for patients
		String name = input.next();
		float temp = input.nextFloat();
		int oxygenLevel = input.nextInt();
		int age = input.nextInt();

		Patient pat = new Patient(name, age, temp, oxygenLevel);

		healthCoordinator.addPatient(pat);
	}

	public static int addressQuery1(HealthCoordinator healthCoordinator) {

		String name = input.next();

		System.out.print("Temperatue criteria - ");
		float temp = input.nextFloat();

		System.out.print("Oxygen Levels - ");
		int oxygenLevel = input.nextInt();

		System.out.print("Number of availaible beds - ");
		int beds = input.nextInt();

		HealthCareInstitute hospital = new HealthCareInstitute(name, oxygenLevel, temp, beds);
		healthCoordinator.addHealthCareInstitute(hospital);

		hospital.displayData();

		int patientsAdmitted = healthCoordinator.admitPatients(hospital);

		return patientsAdmitted;
	}

	public static void addressQuery2(HealthCoordinator healthCoordinator) {
		healthCoordinator.removeAdmittedPatients();
	}

	public static void addressQuery3(HealthCoordinator healthCoordinator) {
		healthCoordinator.removeClosedHospitals();
	}

	public static void addressQuery4(HealthCoordinator healthCoordinator) {
		healthCoordinator.numberOfNotAdmittedPatients();
	}

	public static void addressQuery5(HealthCoordinator healthCoordinator) {
		healthCoordinator.numberOfOpenHospitals();
	}

	public static void addressQuery6(HealthCoordinator healthCoordinator, String name) {
		healthCoordinator.displayHospitalDetails(name);
	}

	public static void addressQuery7(HealthCoordinator healthCoordinator, int id) {
		healthCoordinator.displayPatientDetails(id);
	}

	public static void addressQuery8(HealthCoordinator healthCoordinator) {
		healthCoordinator.displayAllPatients();
	}

	public static void addressQuery9(HealthCoordinator healthCoordinator, String name) {
		healthCoordinator.displayPatientsOfHospital(name);
	}

}