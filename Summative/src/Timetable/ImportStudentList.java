package Timetable;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
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

    @FXML
    public ProgressBar progressBar = new ProgressBar(0);

    public String fileName = "";

    private static ArrayList<Student> listStudents = null;
    private static ArrayList<Student> cloneOfListStudents = null;
    private static ArrayList<Course> listCourses = null;
    private static ArrayList<ArrayList<Classroom>> outerList = new ArrayList<ArrayList<Classroom>>(2);
    private static Table masterTable = new Table(outerList);



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

    public void setCreate(ActionEvent event) throws IOException, ClassNotFoundException{
        listStudents = FunctionMethods.importStudents(fileName);
        listCourses = FunctionMethods.importCourses(fileName);

        ByteArrayOutputStream workingBos = new ByteArrayOutputStream();
        ObjectOutputStream workingOos = new ObjectOutputStream(workingBos);

        workingOos.writeObject(listStudents);		// serialize
        workingOos.flush();

        // toByteArray creates & returns a copy of stream’s byte array
        byte[] workingBytes = workingBos.toByteArray();

        ByteArrayInputStream workingBis = new ByteArrayInputStream(workingBytes);

        ObjectInputStream workingOis = new ObjectInputStream(workingBis);

        cloneOfListStudents = (ArrayList<Student>) workingOis.readObject();

        try {
            masterTable.createMasterTimetable(listStudents);
        } catch (Exception e) {
            System.out.println("Oh");
        }
    }

    public static ArrayList<Student> getListStudents(){
        return cloneOfListStudents;
    }

    public static ArrayList<Course> getListCourses() { return listCourses; }

    public static Table getMasterTimeTable() { return masterTable; }

    public void backToMainMenu(ActionEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene pageScene = new Scene(page);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(pageScene);
        appStage.show();
    }
}
