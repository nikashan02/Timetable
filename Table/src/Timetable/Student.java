package Timetable;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
    private String name;
    private String studentNumber;
    private String grade;
    private ArrayList<Course> coursesChosen;

    //CONSTRUCTOR
    public Student(String name, String studentNumber, String grade, ArrayList<Course> coursesChosen) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.grade = grade;
        this.coursesChosen = coursesChosen;
    }

    //GETTERS
    public String getName() {
        return name;
    }
    public String getStudentNumber() {
        return studentNumber;
    }
    public String getGrade() {
        return grade;
    }
    public ArrayList<Course> getCoursesChosen() { return coursesChosen; }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public void setCoursesChosen(ArrayList<Course> coursesChosen) { this.coursesChosen = coursesChosen; }

}