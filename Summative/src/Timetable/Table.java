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

        ArrayList<Student> originalStudentList = new ArrayList<Student>(listOfStudents);
        Random randomGen = new Random();
        boolean fit = false;
        table.add(new ArrayList<Classroom>());
        table.add(new ArrayList<Classroom>());

        while (!fit) {

            for (int a = 0; a < 2; a++) {
                ArrayList<Course> listOfCourses = new ArrayList<Course>();


                for (Student currentStudent : listOfStudents) {
                    ArrayList<Course> tempListOfCourses = new ArrayList<Course>(listOfCourses);
                    ArrayList<Classroom> tempArrClassroomA;
                    ArrayList<Classroom> tempArrClassroomB;
                    if (a == 0) {
                        tempArrClassroomA = new ArrayList<Classroom>(table.get(a));
                        tempArrClassroomB = new ArrayList<Classroom>(table.get(a+1));
                    }
                    else {
                        tempArrClassroomA = new ArrayList<Classroom>(table.get(a-1));
                        tempArrClassroomB = new ArrayList<Classroom>(table.get(a));
                    }
                    ArrayList<ArrayList<Classroom>> tempTable = new ArrayList<ArrayList<Classroom>>();
                    tempTable.add(tempArrClassroomA);
                    tempTable.add(tempArrClassroomB);
                    boolean checkFour = false;

                    while (!checkFour) {
                        ArrayList<Course> coursesToRemove = new ArrayList<Course>();
                        for (Course currentCourse : currentStudent.getCoursesChosen()) {

                            boolean currentCourseInList = false;
                            for (Course tempCheckCourse : listOfCourses) {
                                if (tempCheckCourse.getCourseCode().equals(currentCourse.getCourseCode())) {
                                    currentCourseInList = true;
                                    break;
                                }
                            }

                            if (!currentCourseInList) {
                                listOfCourses.add(currentCourse);
                                int randomPeriod = 0;
                                while (randomPeriod == 0) {
                                    randomPeriod = randomGen.nextInt(4);
                                }
                                ArrayList<Student> newStudents = new ArrayList<Student>();
                                boolean inClass = false;
                                if (table.get(a).size() > 0) {
                                    for (Classroom currentClass : table.get(a)) {
                                        if (randomPeriod == currentClass.getPeriod()) {

                                            for (Student tempCheckStudent : currentClass.getStudents()) {
                                                if (tempCheckStudent.getStudentNumber().equals(currentStudent.getStudentNumber())) {
                                                    inClass = true;
                                                    break;
                                                }
                                            }

                                            if (inClass) {
                                                break;
                                            }

                                        }
                                    }
                                }
                                if (!inClass) {
                                    newStudents.add(currentStudent);
                                    coursesToRemove.add(currentCourse);
                                    table.get(a).add(new Classroom(currentCourse, currentCourse.getCourseCode() + "1", Classroom.getTeacherCount() + 1, randomPeriod, newStudents));
                                }

                            } else {
                                boolean added = false;
                                for (int x = table.get(a).size() - 1; x > -1; x--) {

                                    boolean currentCourseInList2 = false;
                                    if (table.get(a).get(x).getCourse().getCourseCode().equals(currentCourse.getCourseCode())) {
                                        currentCourseInList2 = true;
                                    }

                                    if (currentCourseInList2) {
                                        if (table.get(a).get(x).getAvailableSeats() > 0) {
                                            boolean inClass = false;
                                            //Check if they have a class that period
                                            for (Classroom currentClass : table.get(a)) {
                                                if (table.get(a).get(x).getPeriod() == currentClass.getPeriod()) {

                                                    for (Student tempCheckStudent : currentClass.getStudents()) {
                                                        if (tempCheckStudent.getStudentNumber().equals(currentStudent.getStudentNumber())) {
                                                            inClass = true;
                                                            break;
                                                        }
                                                    }

                                                    if (inClass) {
                                                        break;
                                                    }

                                                }
                                            }

                                            if (!inClass) {
                                                table.get(a).get(x).addStudent(currentStudent); //This for some reason adds to tempTable as well??? - .addStudent is the problem
                                                coursesToRemove.add(currentCourse);
                                                added = true;
                                                break;
                                            }
                                        }
                                    }
                                }

                                if (!added) {
                                    int randomPeriod = 0;
                                    while (randomPeriod == 0) {
                                        randomPeriod = randomGen.nextInt(4);
                                    }
                                    ArrayList<Student> newStudents = new ArrayList<Student>();
                                    boolean inClass = false;
                                    for (Classroom currentClass : table.get(a)) {
                                        if (randomPeriod == currentClass.getPeriod()) {

                                            for (Student tempCheckStudent : currentClass.getStudents()) {
                                                if (tempCheckStudent.getStudentNumber().equals(currentStudent.getStudentNumber())) {
                                                    inClass = true;
                                                    break;
                                                }
                                            }

                                            if (inClass) {
                                                break;
                                            }

                                        }
                                    }
                                    if (!inClass) {
                                        newStudents.add(currentStudent);
                                        coursesToRemove.add(currentCourse);
                                        table.get(a).add(new Classroom(currentCourse, currentCourse.getCourseCode() + Integer.toString(Classroom.getTeacherCount() + 1), Classroom.getTeacherCount() + 1, randomPeriod, newStudents));
                                    }
                                }

                            }
                        }
                        checkFour = false;
                        if (coursesToRemove.size() >= 3) {
                            checkFour = true;
                            System.out.println(coursesToRemove.size());
                            currentStudent.getCoursesChosen().removeAll(coursesToRemove);
                            /*
                            for (ArrayList<Classroom> currentClassList : table) {
                                for (Classroom current : currentClassList) {
                                    System.out.println(current.getNumberOfStudents());
                                }
                            }

                             */
                        }
                        else {
                            System.out.println(coursesToRemove.size());
                            listOfCourses = tempListOfCourses;
                            table = tempTable;
                        }
                    }
                }
            }
            fit = true;

            for (int b = 0; b<2; b++) {
                for (Classroom currentClass : table.get(b)) {
                    if (currentClass.getNumberOfStudents() < 1) {
                        fit = false;
                        if (b==1) {
                            table = new ArrayList<ArrayList<Classroom>>();
                            break;
                        }
                    }
                }
            }
            listOfStudents = originalStudentList;
        }
    }
}
