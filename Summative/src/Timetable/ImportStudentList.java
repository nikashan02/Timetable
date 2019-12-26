package Timetable;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.channels.FileChannel;
import java.util.ArrayList;


public class ImportStudentList {
    @FXML
    private Button browse;

    @FXML
    private Button create;

    @FXML
    private Label fileLabel;

    public String fileName = "";



    public void ButtonAction(ActionEvent event){
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null){
            fileLabel.setText(selectedFile.getName());
            fileName = selectedFile.getName();

        }
        else{
            System.out.println("File is Invalid");
        }


    }

    public void setCreate(ActionEvent event) {
        ArrayList<Student> listStudents = FunctionMethods.importStudents(fileName);

        ArrayList<ArrayList<Classroom>> outerList = new ArrayList<ArrayList<Classroom>>(2);
        Table masterTable = new Table(outerList);
        try {
            masterTable.createMasterTimetable(listStudents);
        } catch (Exception e) {
            System.out.println("Uh oh");
        }
        int classroomCount = 0;
        //int lessThan15 = 0;
        for (ArrayList<Classroom> currentClassList : masterTable.getTable()) {
            for (Classroom currentClassroom : currentClassList) {
                System.out.println(currentClassroom.getNumberOfStudents());

                classroomCount++;
            }
        }
        System.out.println(classroomCount);
    }
}
