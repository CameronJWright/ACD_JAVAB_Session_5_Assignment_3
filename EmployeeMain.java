package hello.world;

abstract class Employee {
	int empId;
	String empName;
	int total_leaves;
	double total_salary;

	abstract void calculate_balance_leaves();

	abstract boolean avail_leave(int no_of_leaves, char type_of_leave);

	abstract void calculate_salary();
}

class PermanentEmp extends Employee {
	int paid_leave, sick_leave, casual_leave;
	double basic, hra, pfa;

	PermanentEmp(int empId, String empName, int paid_leave, int sick_leave, int casual_leave, double basic) {
		this.empId = empId;
		this.empName = empName;
		this.paid_leave = paid_leave;
		this.sick_leave = sick_leave;
		this.casual_leave = casual_leave;
		this.basic = basic;
		calculate_balance_leaves();
		calculate_salary();

	}

	@Override
	void calculate_balance_leaves() {
		total_leaves = paid_leave + sick_leave + casual_leave;
	}

	@Override
	boolean avail_leave(int no_of_leaves, char type_of_leave) {
		switch (type_of_leave) {
		case 'p':
			if (paid_leave < no_of_leaves) {
				System.out.println("You can only take " + paid_leave + " days of paid leave!");
				return false;
			} else {
				paid_leave = paid_leave - no_of_leaves;
				return true;
			}
		case 's':
			if (sick_leave < no_of_leaves) {
				System.out.println("You can only take " + sick_leave + " days of sick leave!");
				return false;
			} else {
				sick_leave = sick_leave - no_of_leaves;
				return true;
			}
		case 'c':
			if (casual_leave < no_of_leaves) {
				System.out.println("You can only take " + casual_leave + " days of casual leave!");
				return false;
			} else {
				casual_leave = casual_leave - no_of_leaves;
				return true;
			}
		default:
			System.out.println("Choose 'p' for paid leave, 's' for sick leave, or 'c' for casual leave.");
			return false;

		}
	}

	@Override
	void calculate_salary() {
		pfa = basic * 0.12;
		hra = basic * .5;
		total_salary = basic + hra - pfa;
	}

	void display() {
		System.out.println("Employee ID: " + empId);
		System.out.println("Employee Name: " + empName);
		System.out.println("Employee Total Salary: $" + total_salary);
		System.out.println("Employee days of Paid Leave: " + paid_leave);
		System.out.println("Employee days of Sick Leave: " + sick_leave);
		System.out.println("Employee days of Casual Leave: " + casual_leave);
		System.out.println();

	}

}

class TemporaryEmp extends Employee {

	TemporaryEmp(int empId, String empName, double basic) {
		this.empId = empId;
		this.empName = empName;
		this.total_salary = basic;
	}

	@Override
	void calculate_balance_leaves() {
		System.out.println("Temporary employee does have leaves");
	}

	@Override
	boolean avail_leave(int no_of_leaves, char type_of_leave) {
		System.out.println("Temporary employee does have leaves");
		return false;
	}

	@Override
	void calculate_salary() {
		total_salary = total_salary;
	}

	void display() {
		System.out.println("Employee ID: " + empId);
		System.out.println("Employee Name: " + empName);
		System.out.println("Employee Total Salary: $" + total_salary);
		System.out.println("Temporary Employee does not get leave.");
		System.out.println();
	}

}

public class EmployeeMain {

	public static void main(String[] args) {
		int empId, paid_leave, sick_leave, casual_leave;
		String empName;
		double basic;
		System.out.println("Welcome to the employee creation example system!");

		empId = 1;
		paid_leave = 6;
		sick_leave = 4;
		casual_leave = 10;
		empName = "Alex";
		basic = 90000;
		PermanentEmp pEmp = new PermanentEmp(empId, empName, paid_leave, sick_leave, casual_leave, basic);
		pEmp.display();
		System.out.println("Taking leave...\n");
		pEmp.avail_leave(3, 's');
		pEmp.display();

		empId = 2;
		empName = "Jim";
		basic = 70000;
		TemporaryEmp tEmp = new TemporaryEmp(empId, empName, basic);
		tEmp.display();

	}

}
