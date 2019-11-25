package sample;

import java.io.Serializable;

public class Student implements Serializable {
    private String firstName;
    private String lastName;
    private int studentNumber;
    private int grade;
    private int average;
    private boolean ossltCompletion;
    private boolean volunteerCompletion;
    private int credits;

    //CONSTRUCTOR
    public Student(String firstName, String lastName, int studentNumber, int grade,
                   int average, boolean ossltCompletion, boolean volunteerCompletion, int credits) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.grade = grade;
        this.average = average;
        this.ossltCompletion = ossltCompletion;
        this.volunteerCompletion = volunteerCompletion;
        this.credits = credits;
    }

    //GETTERS
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getStudentNumber() {
        return studentNumber;
    }
    public int getGrade() {
        return grade;
    }
    public int getAverage() {
        return average;
    }
    public boolean getOssltCompletion() {
        return ossltCompletion;
    }
    public boolean getVolunteerCompletion() {
        return volunteerCompletion;
    }
    public int getCredits() {
        return credits;
    }

    //SETTERS
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public void setAverage(int average) {
        this.average = average;
    }
    public void setOssltCompletion(boolean ossltCompletion) {
        this.ossltCompletion = ossltCompletion;
    }
    public void setVolunteerCompletion(boolean volunteerCompletion) {
        this.volunteerCompletion = volunteerCompletion;
    }
    public void setCredit(int credits) {
        this.credits = credits;
    }
}
