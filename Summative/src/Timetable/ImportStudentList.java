package Timetable;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;


public class ImportStudentList {
    @FXML
    private Button browse;

    @FXML
    private Button create;

    @FXML
    private Label fileLabel;

    @FXML
    Button mainMenuBtn = new Button();

    public String fileName = "";

    private static ArrayList<Student> listStudents = null;



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
        listStudents = FunctionMethods.importStudents(fileName);

        ArrayList<ArrayList<Classroom>> outerList = new ArrayList<ArrayList<Classroom>>(2);
        Table masterTable = new Table(outerList);
        try {
            masterTable.createMasterTimetable(listStudents);
        } catch (Exception e) {
            System.out.println("Uh oh");
        }
        /*
        int classroomCount = 0;
        //int lessThan15 = 0;
        for (ArrayList<Classroom> currentClassList : masterTable.getTable()) {
            for (Classroom currentClassroom : currentClassList) {
                System.out.println(currentClassroom.getNumberOfStudents());

                classroomCount++;
            }
        }
        System.out.println(classroomCount);
        */
    }

    public static ArrayList<Student> getListStudents(){
        return listStudents;
    }

    public void backToMainMenu(ActionEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene pageScene = new Scene(page);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(pageScene);
        appStage.show();

    }

}
