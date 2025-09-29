package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class studentest {

    @Test
    void testAddGradeAndGPA() {
        Main.Student s = new Main.Student("1", "Alice");
        s.addGrade(80);
        s.addGrade(90);
        assertEquals(3.4, s.calculateGPA(), 0.01);
    }

    @Test
    void testGPAWithNoGrades() {
        Main.Student s = new Main.Student("2", "Bob");
        assertEquals(0.0, s.calculateGPA(), 0.01);
    }

    @Test
    void testInvalidGradeThrows() {
        Main.Student s = new Main.Student("3", "Cara");
        assertThrows(IllegalArgumentException.class, () -> s.addGrade(-5));
        assertThrows(IllegalArgumentException.class, () -> s.addGrade(105));
    }

    @Test
    void testInvalidStudentConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Main.Student("", "Name"));
        assertThrows(IllegalArgumentException.class, () -> new Main.Student("ID", ""));
    }
}
