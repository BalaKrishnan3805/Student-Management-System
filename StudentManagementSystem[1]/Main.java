// Main.java
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final StudentDAO dao = new StudentDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Student Management System ===");
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose option: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": addStudent(); break;
                case "2": viewAllStudents(); break;
                case "3": updateStudent(); break;
                case "4": deleteStudent(); break;
                case "5": viewStudentById(); break;
                case "0": running = false; break;
                default: System.out.println("Invalid option. Try again.");
            }
        }
        System.out.println("Exiting... Goodbye!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. View Student By ID");
        System.out.println("0. Exit");
    }

    private static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter age: ");
        int age = readInt();
        System.out.print("Enter department: ");
        String dept = scanner.nextLine().trim();

        Student s = new Student(name, age, dept);
        boolean ok = dao.addStudent(s);
        if (ok) System.out.println("Student added with ID: " + s.getId());
        else System.out.println("Failed to add student.");
    }

    private static void viewAllStudents() {
        List<Student> students = dao.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- Students ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = readInt();
        Student existing = dao.getStudentById(id);
        if (existing == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }
        System.out.println("Existing: " + existing);

        System.out.print("Enter new name (leave blank to keep): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) existing.setName(name);

        System.out.print("Enter new age (leave blank to keep): ");
        String ageStr = scanner.nextLine().trim();
        if (!ageStr.isEmpty()) {
            try {
                existing.setAge(Integer.parseInt(ageStr));
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input. Keeping old value.");
            }
        }

        System.out.print("Enter new department (leave blank to keep): ");
        String dept = scanner.nextLine().trim();
        if (!dept.isEmpty()) existing.setDepartment(dept);

        boolean ok = dao.updateStudent(existing);
        if (ok) System.out.println("Student updated.");
        else System.out.println("Update failed.");
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = readInt();
        System.out.print("Are you sure? (y/N): ");
        String conf = scanner.nextLine().trim().toLowerCase();
        if (!conf.equals("y")) {
            System.out.println("Delete cancelled.");
            return;
        }
        boolean ok = dao.deleteStudent(id);
        if (ok) System.out.println("Student deleted.");
        else System.out.println("Delete failed or student not found.");
    }

    private static void viewStudentById() {
        System.out.print("Enter student ID: ");
        int id = readInt();
        Student s = dao.getStudentById(id);
        if (s == null) System.out.println("Student not found.");
        else System.out.println(s);
    }

    private static int readInt() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Enter again: ");
            }
        }
    }
}
