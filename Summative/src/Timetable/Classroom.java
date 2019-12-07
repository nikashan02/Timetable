package Timetable;

import java.io.Serializable;
import java.util.ArrayList;

public class Classroom implements Serializable {
    private Course course;
    private String classCode;
    private int roomNumber;
    private int period;
    private ArrayList<Student> students;
    private int availableSeats;
    private int numberOfStudents;
    private static int teacherCount = 0;

    //CONSTRUCTOR
    public Classroom(Course course, String classCode, int roomNumber, int period, ArrayList<Student> students) {
        this.course = course;
        this.classCode = classCode;
        this.roomNumber = roomNumber;
        this.period = period;
        this.students = students;
        numberOfStudents = students.size();
        availableSeats = 30-numberOfStudents;
        teacherCount++;
    }

    //GETTERS
    public Course getCourse() { return course; }
    public String getClassCode() { return classCode; }
    public int getRoomNumber() { return roomNumber; }
    public int getPeriod() { return period; }
    public ArrayList<Student> getStudents() { return students; }
    public int getAvailableSeats() { return availableSeats; }
    public int getNumberOfStudents() { return numberOfStudents; }
    public static int getTeacherCount() { return teacherCount; }

    //SETTERS
    public void setCourse(Course course) { this.course = course; }
    public void setClassCode(String classCode) { this.classCode = classCode; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public void setPeriod(int period) { this.period = period; }
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        numberOfStudents = students.size();
        availableSeats = 30-numberOfStudents;
    }
    public static void setTeacherCount(int teacherCount) {
        Classroom.teacherCount = teacherCount;
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