package Timetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Table implements Serializable {
    private ArrayList<ArrayList<Classroom>> table;

    //CONSTRUCTOR
    public Table(ArrayList<ArrayList<Classroom>> table) {
        this.table = table;
    }

    //GETTER
    public ArrayList<ArrayList<Classroom>> getTable() {
        return table;
    }

    //SETTER
    public void setTable(ArrayList<ArrayList<Classroom>> table) {
        this.table = table;
    }

    //METHODS
    public void createMasterTimetable(ArrayList<Student> listOfStudents) {
        ArrayList<Course> listOfCourses = new ArrayList<Course>();
        ArrayList<Student> originalStudentList = listOfStudents;
        ArrayList<Course> originalCourseList = new ArrayList<Course>();
        Random randomGen = new Random();
        boolean fit = false;
        while (!fit) {
            for (int a = 0; a < 2; a++) {
                for (Student currentStudent : listOfStudents) {
                    for (Course currentCourse : currentStudent.getCoursesChosen()) {
                        if (!listOfCourses.contains(currentCourse)) {
                            listOfCourses.add(currentCourse);
                            int randomPeriod = 0;
                            while (randomPeriod == 0) {
                                randomPeriod = randomGen.nextInt(4);
                            }
                            ArrayList<Student> newStudents = new ArrayList<Student>();
                            boolean inClass = false;
                            for (Classroom currentClass : table.get(a)) {
                                if (randomPeriod == currentClass.getPeriod()) {
                                    if (currentClass.getStudents().contains(currentStudent)) {
                                        inClass = true;
                                        break;
                                    }
                                }
                            }
                            if (!inClass) {
                                newStudents.add(currentStudent);
                                currentStudent.getCoursesChosen().remove(currentCourse);
                            }
                            table.get(a).add(new Classroom(currentCourse, currentCourse.getCourseCode() + "1", Classroom.getTeacherCount() + 1, randomPeriod, newStudents));
                        } else {
                            for (int x = table.get(a).size() - 1; x > 0; x--) {
                                if (table.get(a).get(x).getAvailableSeats() > 0) {
                                    table.get(a).get(x).addStudent(currentStudent);
                                    currentStudent.getCoursesChosen().remove(currentCourse);
                                    break;
                                } else {
                                    int randomPeriod = 0;
                                    while (randomPeriod == 0) {
                                        randomPeriod = randomGen.nextInt(4);
                                    }
                                    ArrayList<Student> newStudents = new ArrayList<Student>();
                                    boolean inClass = false;
                                    for (Classroom currentClass : table.get(a)) {
                                        if (randomPeriod == currentClass.getPeriod()) {
                                            if (currentClass.getStudents().contains(currentStudent)) {
                                                inClass = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (!inClass) {
                                        newStudents.add(currentStudent);
                                        currentStudent.getCoursesChosen().remove(currentCourse);
                                    }
                                    table.get(a).add(new Classroom(currentCourse, currentCourse.getCourseCode() + Integer.toString(Classroom.getTeacherCount() + 1), Classroom.getTeacherCount() + 1, randomPeriod, newStudents));
                                }
                            }
                        }
                    }
                }
            }
            fit = true;
            for (int b = 0; b < 2; b++) {
                for (Classroom currentClass : table.get(b)) {
                    if (currentClass.getNumberOfStudents() < 15) {
                        fit = false;
                        break;
                    }
                }
            }
            listOfStudents = originalStudentList;
            listOfCourses = originalCourseList;
        }
    }
}
