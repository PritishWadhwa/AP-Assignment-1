/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Name:		Pritish Wadhwa													 *
 * RollNumber: 	2019440															 *
 * Section:		A																 *
 * Stream:		CSE																 *
 * Topic:		Assignment 1													 *
 * SubTopic:	Helper Classes for Assignment 1 								 *
 * 				(Patient, HealthCareInstitute,HealthCoordinator ) 				 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package AP_Assignment1;

import java.util.ArrayList;
import java.util.Scanner;

enum AdmittedOrNot {
	Admitted, NotAdmitted
}

enum Status {
	OPEN, CLOSED
}

class Patient {

	// class members
	private final String name;
	private final float temperature;
	private final int age;
	private final int oxygenLevel;
	private final int patientId;
	private static int ctr = 0;
	private int daysOfRecovery;
	private String hospitalName;
	private AdmittedOrNot admissionStatus;

	// constructor
	public Patient(String name, int age, float temp, int oxygen) {
		this.age = age;
		this.name = name;
		this.oxygenLevel = oxygen;
		this.temperature = temp;
		this.patientId = ++ctr;
		this.admissionStatus = AdmittedOrNot.NotAdmitted;
		this.daysOfRecovery = 0;
		this.hospitalName = null;
	}

	// getters
	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	public int getOxygen() {
		return this.oxygenLevel;
	}

	public float getTemp() {
		return this.temperature;
	}

	public int getID() {
		return this.patientId;
	}

	public String getHospitalName() {
		return this.hospitalName;
	}

	public AdmittedOrNot getAdmissionStatus() {
		return this.admissionStatus;
	}

	public int getRecoveryTime() {
		return this.daysOfRecovery;
	}

	// setters
	public void setHospitalName(String name) {
		this.hospitalName = name;
	}

	public void setAdmissionStatus(AdmittedOrNot status) {
		this.admissionStatus = status;
	}

	public void setDaysOfRecovery(int days) {
		this.daysOfRecovery = days;
	}

	// function to display details of the Patient
	public void displayDetails() {
		System.out.println(this.getName());
		System.out.println("Temperatur is " + this.getTemp());
		System.out.println("Oxygen Levels is " + this.getOxygen());
		System.out.println("Admission Status - " + this.getAdmissionStatus());
		System.out.println("Admitting Institute - " + this.getHospitalName());
	}

	// function to display the id and name of the patient
	public void displayIdName() {
		System.out.println(this.getID() + " " + this.getName());
	}

	// function to display the name and the recovery status of the patient
	public void displayNameRecovery() {
		System.out.println(this.getName() + ", Recovery time is " + this.getRecoveryTime() + " days");
	}
}

class HealthCareInstitute {

	// class members
	private final String name;
	private final int oxygenLevel;
	private final float temperature;
	private final int numberOfAvailaibleBeds;
	private int bedsOccupied;
	private Status hospitalStatus;
	// array list to store the patients in a hospital
	private ArrayList<Patient> admittedPatients = new ArrayList<Patient>();

	// constructor
	public HealthCareInstitute(String name, int oxygen, float temp, int beds) {
		this.name = name;
		this.oxygenLevel = oxygen;
		this.temperature = temp;
		this.numberOfAvailaibleBeds = beds;
		this.bedsOccupied = 0;
		this.hospitalStatus = Status.OPEN;
	}

	// getters
	public String getName() {
		return this.name;
	}

	public float getTemp() {
		return this.temperature;
	}

	public int getOxygen() {
		return this.oxygenLevel;
	}

	public int getBeds() {
		return this.numberOfAvailaibleBeds;
	}

	public int getFreeBeds() {
		return (this.numberOfAvailaibleBeds - this.bedsOccupied);
	}

	public Status getStatus() {
		return this.hospitalStatus;
	}

	// setters
	public void setStatus(Status s) {
		this.hospitalStatus = s;
	}

	public void updateFreeBeds(int n) {
		this.bedsOccupied += n;
	}

	// function to admit any patient
	public void admitPatient(Patient p) {
		this.admittedPatients.add(p);
	}

	// function to display data of a health care institute
	public void displayData() {
		System.out.println(this.getName());
		System.out.println("Temperature should be <= " + this.getTemp());
		System.out.println("Oxygen Level should be >= " + this.getOxygen());
		System.out.println("Number of availaible beds - " + this.getFreeBeds());
		System.out.println("Admission Status - " + this.getStatus());
	}

	// function to check if a patient can be admitted on the basis of oxygen level
	// in this hospital
	public boolean canPatientBeAdmittedOnOxygen(Patient p) {
		if (p.getAdmissionStatus() == AdmittedOrNot.NotAdmitted) {
			if (p.getOxygen() >= this.getOxygen()) {
				return true;
			}
		}
		return false;
	}

	// function to check if a patient can be admitted on the basis of temperature
	// in this hospital
	public boolean canPatientBeAdmittedOnTemperature(Patient p) {
		if (p.getAdmissionStatus() == AdmittedOrNot.NotAdmitted) {
			if (p.getTemp() <= this.getTemp()) {
				return true;
			}
		}
		return false;
	}

	// function to display the name and recovery date of the patients admitted in
	// this hospital
	public void displayNameRecovery() {
		for (Patient patient : admittedPatients) {
			patient.displayNameRecovery();
		}
	}

}

class HealthCoordinator {

	Scanner input = new Scanner(System.in);

	// array list to store the details of all the patients
	private ArrayList<Patient> patientDetails = new ArrayList<Patient>();

	// array list to store the details of all the hospitals
	private ArrayList<HealthCareInstitute> instituteList = new ArrayList<HealthCareInstitute>();

	// function to add patient in the list
	public void addPatient(Patient p) {
		patientDetails.add(p);
	}

	// function to add hospital in the list
	public void addHealthCareInstitute(HealthCareInstitute h) {
		instituteList.add(h);
	}

	// function to admit patient in the particular hospital
	// and return the number of patients admitted
	public int admitPatients(HealthCareInstitute hospital) {

		int ctr = 0;

		if (hospital.getFreeBeds() == 0) {
			hospital.setStatus(Status.CLOSED);
			return ctr;
		}

		// adding patients on the basis of oxygen levels
		for (Patient patient : patientDetails) {
			if (hospital.canPatientBeAdmittedOnOxygen(patient) && hospital.getFreeBeds() > 0) {

				System.out.print("Recovery days for admitted patient ID " + patient.getID() + " – ");
				int numberOfRecoveryDays = input.nextInt();

				patient.setDaysOfRecovery(numberOfRecoveryDays);
				patient.setHospitalName(hospital.getName());
				patient.setAdmissionStatus(AdmittedOrNot.Admitted);

				hospital.admitPatient(patient);

				ctr++;

				hospital.updateFreeBeds(1);
			}

			if (hospital.getFreeBeds() == 0) {
				hospital.setStatus(Status.CLOSED);
				return ctr;
			}

		}

		// adding patients on the basis of temperature
		for (Patient patient : patientDetails) {
			if (hospital.canPatientBeAdmittedOnTemperature(patient) && hospital.getFreeBeds() > 0) {

				System.out.print("Recovery days for admitted patient ID " + patient.getID() + " – ");
				int numberOfRecoveryDays = input.nextInt();

				patient.setDaysOfRecovery(numberOfRecoveryDays);
				patient.setHospitalName(hospital.getName());
				patient.setAdmissionStatus(AdmittedOrNot.Admitted);

				hospital.admitPatient(patient);

				ctr++;

				hospital.updateFreeBeds(1);
			}

			if (hospital.getFreeBeds() == 0) {
				hospital.setStatus(Status.CLOSED);
				return ctr;
			}
		}

		return ctr;
	}

	// function to remove all the admitted patients
	public void removeAdmittedPatients() {

		ArrayList<Patient> patientDetailsNew = new ArrayList<Patient>();

		for (Patient patient : patientDetails) {
			if (patient.getAdmissionStatus() == AdmittedOrNot.NotAdmitted) {
				patientDetailsNew.add(patient);
			} else {
				System.out.println("The account details of patient with ID " + patient.getID() + " has been removed!");
			}
		}

		patientDetails = patientDetailsNew;
	}

	// function to remove all the closed hospitals
	public void removeClosedHospitals() {

		ArrayList<HealthCareInstitute> instituteListNew = new ArrayList<HealthCareInstitute>();

		for (HealthCareInstitute institute : instituteList) {
			if (institute.getStatus() == Status.OPEN) {
				instituteListNew.add(institute);
			} else {
				System.out.println("The account of " + institute.getName() + " has been removed!");
			}
		}

		instituteList = instituteListNew;
	}

	// function to print number of patients still not admitted in any hospital
	public void numberOfNotAdmittedPatients() {

		int ctr = 0;

		for (Patient patient : patientDetails) {
			if (patient.getAdmissionStatus() == AdmittedOrNot.NotAdmitted) {
				ctr++;
			}
		}

		System.out.println(ctr + " Patients");
	}

	// function to print number of hospitals still open
	public void numberOfOpenHospitals() {

		int ctr = 0;

		for (HealthCareInstitute healthCareInstitute : instituteList) {
			if (healthCareInstitute.getStatus() == Status.OPEN) {
				ctr++;
			}
		}

		System.out.println(ctr + " institutes are admitting patients currently.");
	}

	// function to display details of hospital, given the hospital name
	public void displayHospitalDetails(String name) {

		for (HealthCareInstitute healthCareInstitute : instituteList) {

			if (healthCareInstitute.getName().equalsIgnoreCase(name)) {
				healthCareInstitute.displayData();
				break;
			}
		}
	}

	// function to display details of patient, given the unique patient id
	public void displayPatientDetails(int id) {

		for (Patient patient : patientDetails) {

			if (patient.getID() == id) {
				patient.displayDetails();
			}
		}
	}

	// function to display the names and id's of all the patients
	public void displayAllPatients() {

		for (Patient patient : patientDetails) {
			patient.displayIdName();
		}
	}

	// function the display all the patients of a hospital, given the hospital name
	public void displayPatientsOfHospital(String name) {

		for (HealthCareInstitute hospital : instituteList) {

			if (hospital.getName() != null && hospital.getName().equalsIgnoreCase(name)) {
				hospital.displayNameRecovery();
			}
		}
	}
}