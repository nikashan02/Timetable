package Timetable;

import com.sun.jdi.ArrayReference;

import java.util.ArrayList;

public class Main { //extends Application {
/*
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Meadowvale Secondary School");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
*/

    public static void main(String[] args) {
        //launch(args);
        ArrayList<Student> listStudents = FunctionMethods.importStudents("stuff.csv");
        /*
        for (Student student : listStudents) {
            System.out.println(student.getName());
        }
         */
        ArrayList<ArrayList<Classroom>> outerList = new ArrayList<ArrayList<Classroom>>(2);
        Table masterTable = new Table(outerList);
        try {
            masterTable.createMasterTimetable(listStudents);
        } catch (Exception e) {
            System.out.println("Uh oh");
        }
        int classroomCount = 0;
        for (ArrayList<Classroom> currentClassList : masterTable.getTable()) {
            for (Classroom currentClassroom : currentClassList) {
                System.out.println(currentClassroom.getNumberOfStudents());
                classroomCount++;
            }
        }
        System.out.println(classroomCount);
    }
}
