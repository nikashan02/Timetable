package Timetable;

import java.io.Serializable;

public class Course implements Serializable {
    private String courseCode;
    private String courseName;

    //CONSTRUCTOR
    public Course (String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    //GETTERS
    public String getCourseCode() {
        return courseCode;
    }
    public String getCourseName() {
        return courseName;
    }

    //SETTERS
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
