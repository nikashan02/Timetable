package Timetable;

import java.io.*;
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
    public void createMasterTimetable(ArrayList<Student> listOfStudents) throws IOException, ClassNotFoundException {

        //CLONING
        ByteArrayOutputStream studentBos = new ByteArrayOutputStream();
        ObjectOutputStream studentOos = new ObjectOutputStream(studentBos);

        studentOos.writeObject(listOfStudents);		// serialize
        studentOos.flush();

        // toByteArray creates & returns a copy of stream’s byte array
        byte[] studentBytes = studentBos.toByteArray();

        ByteArrayInputStream studentBis = new ByteArrayInputStream(studentBytes);

        ObjectInputStream studentOis = new ObjectInputStream(studentBis);

        ArrayList<Student> originalStudentList = (ArrayList<Student>) studentOis.readObject();

        Random randomGen = new Random();
        boolean fit = false;
        table.add(new ArrayList<Classroom>());
        table.add(new ArrayList<Classroom>());

        while (!fit) {

            for (int a = 0; a < 2; a++) {
                ArrayList<Course> listOfCourses = new ArrayList<Course>();


                for (Student currentStudent : listOfStudents) {

                    //CLONING
                    ByteArrayOutputStream courseBos = new ByteArrayOutputStream();
                    ObjectOutputStream courseOos = new ObjectOutputStream(courseBos);

                    courseOos.writeObject(listOfCourses);		// serialize
                    courseOos.flush();

                    // toByteArray creates & returns a copy of stream’s byte array
                    byte[] courseBytes = courseBos.toByteArray();

                    ByteArrayInputStream courseBis = new ByteArrayInputStream(courseBytes);

                    ObjectInputStream courseOis = new ObjectInputStream(courseBis);

                    ArrayList<Course> tempListOfCourses = (ArrayList<Course>) courseOis.readObject();

                    //CLONING
                    ByteArrayOutputStream newBos = new ByteArrayOutputStream();
                    ObjectOutputStream newOos = new ObjectOutputStream(newBos);

                    newOos.writeObject(table);		// serialize
                    newOos.flush();

                    // toByteArray creates & returns a copy of stream’s byte array
                    byte[] newBytes = newBos.toByteArray();

                    ByteArrayInputStream newBis = new ByteArrayInputStream(newBytes);

                    ObjectInputStream newOis = new ObjectInputStream(newBis);
                    ArrayList<ArrayList<Classroom>> tempTable = (ArrayList<ArrayList<Classroom>>) newOis.readObject();        // deserialize & typecast

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
                                    randomPeriod = randomGen.nextInt(5);
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

                                    //CLONING
                                    ByteArrayOutputStream temp1Bos = new ByteArrayOutputStream();
                                    ObjectOutputStream temp1Oos = new ObjectOutputStream(temp1Bos);

                                    temp1Oos.writeObject(currentStudent);		// serialize
                                    temp1Oos.flush();

                                    // toByteArray creates & returns a copy of stream’s byte array
                                    byte[] temp1Bytes = temp1Bos.toByteArray();

                                    ByteArrayInputStream temp1Bis = new ByteArrayInputStream(temp1Bytes);

                                    ObjectInputStream temp1Ois = new ObjectInputStream(temp1Bis);
                                    Student temp1Clone = (Student) temp1Ois.readObject();

                                    newStudents.add(temp1Clone);
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

                                                //CLONING
                                                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                                ObjectOutputStream oos = new ObjectOutputStream(bos);

                                                oos.writeObject(currentStudent);	//serialize
                                                oos.flush();

                                                //toByteArray creates & returns a copy of stream’s byte array
                                                byte[] bytes = bos.toByteArray();

                                                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

                                                ObjectInputStream ois = new ObjectInputStream(bis);
                                                Student clone = (Student) ois.readObject();  // deserialize & typecast


                                                table.get(a).get(x).addStudent(clone); //Fixed
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
                                        randomPeriod = randomGen.nextInt(5);
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

                                        //CLONING
                                        ByteArrayOutputStream tempBos = new ByteArrayOutputStream();
                                        ObjectOutputStream tempOos = new ObjectOutputStream(tempBos);

                                        tempOos.writeObject(currentStudent);		// serialize
                                        tempOos.flush();

                                        // toByteArray creates & returns a copy of stream’s byte array
                                        byte[] tempBytes = tempBos.toByteArray();

                                        ByteArrayInputStream tempBis = new ByteArrayInputStream(tempBytes);

                                        ObjectInputStream tempOis = new ObjectInputStream(tempBis);
                                        Student temp2Clone = (Student) tempOis.readObject();

                                        newStudents.add(temp2Clone);
                                        coursesToRemove.add(currentCourse);
                                        table.get(a).add(new Classroom(currentCourse, currentCourse.getCourseCode() + Integer.toString(Classroom.getTeacherCount() + 1), Classroom.getTeacherCount() + 1, randomPeriod, newStudents));
                                    }
                                }

                            }
                        }
                        checkFour = false;
                        if (coursesToRemove.size() >= 4) {
                            checkFour = true;
                            currentStudent.getCoursesChosen().removeAll(coursesToRemove);
                        }
                        else {
                            if (coursesToRemove.size() == 0) {
                                System.out.println("uhhhhhhhh");
                            }
                            listOfCourses = tempListOfCourses;

                            //CLONING
                            ByteArrayOutputStream tempTableBos = new ByteArrayOutputStream();
                            ObjectOutputStream tempTableOos = new ObjectOutputStream(tempTableBos);

                            tempTableOos.writeObject(tempTable);		// serialize
                            tempTableOos.flush();

                            // toByteArray creates & returns a copy of stream’s byte array
                            byte[] tempTableBytes = tempTableBos.toByteArray();

                            ByteArrayInputStream tempTableBis = new ByteArrayInputStream(tempTableBytes);

                            ObjectInputStream tempTableOis = new ObjectInputStream(tempTableBis);
                            ArrayList<ArrayList<Classroom>> temp2Table = (ArrayList<ArrayList<Classroom>>) tempTableOis.readObject();

                            table = temp2Table;
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

            if (!fit) {

                //CLONING
                ByteArrayOutputStream studentOGBos = new ByteArrayOutputStream();
                ObjectOutputStream studentOGOos = new ObjectOutputStream(studentBos);

                studentOos.writeObject(originalStudentList);		// serialize
                studentOos.flush();

                // toByteArray creates & returns a copy of stream’s byte array
                byte[] studentOGBytes = studentOGBos.toByteArray();

                ByteArrayInputStream studentOGBis = new ByteArrayInputStream(studentOGBytes);

                ObjectInputStream studentOGOis = new ObjectInputStream(studentOGBis);

                ArrayList<Student> originalOGStudentList = (ArrayList<Student>) studentOis.readObject();

                listOfStudents = originalOGStudentList;
            }
        }
    }
}
