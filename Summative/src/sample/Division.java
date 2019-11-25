package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Division implements Serializable {
    private Course course;
    private String classCode;
    private int roomNumber;
    private int period;
    private Teacher teacher;
    private ArrayList<Student> students;
    private int availableSeats;
    private int numberOfStudents;

    //CONSTRUCTOR
    public Division(Course course, String classCode, int roomNumber, int period,
                    Teacher teacher, ArrayList<Student> students) {

        this.course = course;
        this.classCode = classCode;
        this.roomNumber = roomNumber;
        this.period = period;
        this.teacher = teacher;
        this.students = students;
        numberOfStudents = students.size();
        availableSeats = 30-numberOfStudents;
    }

    //GETTERS
    public Course getCourse() {
        return course;
    }
    public String getClassCode() {
        return classCode;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public int getPeriod() {
        return period;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    //SETTERS
    public void setCourse(Course course) {
        this.course = course;
    }
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setPeriod(int period) {
        this.period = period;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        numberOfStudents = students.size();
        availableSeats = 30-numberOfStudents;
    }

    //METHODS
    public void addStudent(Student student) {
        students.add(student);
        numberOfStudents++;
        availableSeats--;
    }
    public void removeStudent(Student student) {
        students.remove(student);
        numberOfStudents--;
        availableSeats++;
    }
}