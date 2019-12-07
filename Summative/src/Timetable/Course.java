package Timetable;

import java.io.Serializable;

public class Course implements Serializable {
    private String courseCode;

    //CONSTRUCTOR
    public Course (String courseCode) {
        this.courseCode = courseCode;
    }

    //GETTERS
    public String getCourseCode() {
        return courseCode;
    }

    //SETTERS
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}