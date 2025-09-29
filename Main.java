package org.example;

import java.util.*;

public class Main {
    private static final Map<String, Student> students = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("** Student Management System **");
        while (!exit) {
            menu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    removeStudent();
                    break;
                case "3":
                    assignGrade();
                    break;
                case "4":
                    calculateGPA();
                    break;
                case "5":
                    listStudents();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        System.out.println("Goodbye!");
    }

    private static void menu() {
        System.out.println("\n1) Add student");
        System.out.println("2) Remove student");
        System.out.println("3) Assign grade");
        System.out.println("4) Calculate GPA");
        System.out.println("5) List students");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
    }

    private static void addStudent() {
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        try {
            Student s = new Student(id, name);
            students.put(id, s);
            System.out.println("Added.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeStudent() {
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        if (students.remove(id) != null) System.out.println("Removed.");
        else System.out.println("Student not found.");
    }

    private static void assignGrade() {
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        Student s = students.get(id);
        if (s == null) { System.out.println("Not found."); return; }
        System.out.print("Grade (0-100): ");
        try {
            double g = Double.parseDouble(sc.nextLine().trim());
            s.addGrade(g);
            System.out.println("Grade added.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void calculateGPA() {
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        Student s = students.get(id);
        if (s == null) System.out.println("Not found.");
        else System.out.println("GPA: " + s.calculateGPA());
    }

    private static void listStudents() {
        if (students.isEmpty()) { System.out.println("No students."); return; }
        students.values().forEach(System.out::println);
    }

    // Inner Student class
    public static class Student {
        private final String id;
        private final String name;
        private final List<Double> grades = new ArrayList<>();

        public Student(String id, String name) {
            if (id == null || id.isBlank()) throw new IllegalArgumentException("ID cannot be empty");
            if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
            this.id = id;
            this.name = name;
        }

        public void addGrade(double grade) {
            if (grade < 0 || grade > 100) throw new IllegalArgumentException("Grade must be 0-100");
            grades.add(grade);
        }

        public double calculateGPA() {
            if (grades.isEmpty()) return 0.0;
            double sum = 0.0;
            for (double g : grades) sum += g;
            double avg = sum / grades.size();
            return Math.round((avg / 25.0) * 100.0) / 100.0;
        }

        @Override
        public String toString() {
            return id + " | " + name + " | GPA: " + calculateGPA();
        }
    }
}
