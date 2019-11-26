package Timetable;

import java.io.Serializable;
import java.util.ArrayList;

public class Teacher implements Serializable {
    private String firstName;
    private String lastName;
    private int teacherNumber;
    private ArrayList<Course> teachableCourses;

    //CONSTRUCTOR
    public Teacher(String firstName, String lastName, int teacherNumber, ArrayList<Course> teachableCourses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.teacherNumber = teacherNumber;
        this.teachableCourses = teachableCourses;
    }

    //GETTERS
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getTeacherNumber() {
        return teacherNumber;
    }
    public ArrayList<Course> getTeachableCourses() {
        return teachableCourses;
    }

    //SETTERS
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setTeacherNumber(int teacherNumber) {
        this.teacherNumber = teacherNumber;
    }
    public void setTeachableCourses(ArrayList<Course> teachableCourses) {
        this.teachableCourses = teachableCourses;
    }

    //METHODS
    public void addTeachableCourse(Course course) {
        teachableCourses.add(course);
    }
    public void removeTeachableCourse(Course course) {
        teachableCourses.remove(course);
    }
}
