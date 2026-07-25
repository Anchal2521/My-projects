import java.util.*;

// Abstract Class
abstract class Person {
    protected String id;
    protected String name;

    Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void displayDetails();
}

// Interface
interface PlacementStatus {
    void updateStatus(String status);
}

// Student Class
class Student extends Person implements PlacementStatus {

    private double cgpa;
    private String status;

    Student(String id, String name, double cgpa) {
        super(id, name);
        this.cgpa = cgpa;
        this.status = "Not Placed";
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public void updateStatus(String status) {
        this.status = status;
    }

    @Override
    public void displayDetails() {
        System.out.println("Student ID : " + id);
        System.out.println("Name       : " + name);
        System.out.println("CGPA       : " + cgpa);
        System.out.println("Status     : " + status);
    }
}

// Company Class
class Company {

    private String companyName;
    private double minCGPA;

    Company(String companyName, double minCGPA) {
        this.companyName = companyName;
        this.minCGPA = minCGPA;
    }

    public double getMinCGPA() {
        return minCGPA;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void displayDetails() {
        System.out.println("Company Name : " + companyName);
        System.out.println("Minimum CGPA : " + minCGPA);
    }
}

// Placement Drive Class
class PlacementDrive {

    private Company company;

    PlacementDrive(Company company) {
        this.company = company;
    }

    public void conductDrive(Student student) {

        if (student.getCgpa() >= company.getMinCGPA()) {
            student.updateStatus("Placed in " + company.getCompanyName());
            System.out.println(student.name + " is Eligible.");
        } else {
            student.updateStatus("Rejected");
            System.out.println(student.name + " is Not Eligible.");
        }
    }
}

// Placement Cell
class PlacementCell {

    ArrayList<Student> students = new ArrayList<>();

    // Method Overloading
    void registerStudent(Student s) {
        students.add(s);
    }

    void registerStudent(String id, String name, double cgpa) {
        students.add(new Student(id, name, cgpa));
    }

    void displayStudents() {
        for (Student s : students) {
            s.displayDetails();
            System.out.println("----------------------------");
        }
    }
}

// Main Class
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PlacementCell cell = new PlacementCell();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        // Input Student Details
        for (int i = 1; i <= n; i++) {

            System.out.println("\nEnter Details of Student " + i);

            System.out.print("Enter Student ID: ");
            String id = sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter CGPA: ");
            double cgpa = sc.nextDouble();
            sc.nextLine();

            cell.registerStudent(id, name, cgpa);
        }

        // Company Details
        System.out.println("\nEnter Company Details");

        System.out.print("Company Name: ");
        String companyName = sc.nextLine();

        System.out.print("Minimum CGPA Required: ");
        double minCGPA = sc.nextDouble();

        Company company = new Company(companyName, minCGPA);

        PlacementDrive drive = new PlacementDrive(company);

        // Conduct Placement
        System.out.println("\nPlacement Results:");
        for (Student s : cell.students) {
            drive.conductDrive(s);
        }

        // Display Student Details
        System.out.println("\nStudent Details After Placement:");
        cell.displayStudents();

        sc.close();
    }
}