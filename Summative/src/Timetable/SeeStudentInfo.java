package Timetable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class SeeStudentInfo {
    @FXML
    TextField studentNumberTextField = new TextField();
    @FXML
    TextField studentNameTextField = new TextField();
    @FXML
    Button searchBtn = new Button();
    @FXML
    ListView studentsListView = new ListView();
    @FXML
    Button mainMenuBtn = new Button();
    @FXML
    ListView chosenStudentInfoListView = new ListView();

    private String studentNumber = "";
    private String studentName = "";


    public void studentNumberTextFieldAction(ActionEvent event){
        if (!studentNumberTextField.getText().equals("")){
            studentNumber = studentNumberTextField.getText();
        }
    }

    public void studentNameTextFieldAction(ActionEvent event){
        if (!studentNameTextField.getText().equals("")){
            studentName = studentNameTextField.getText();
        }
    }

    public void searchBtn(ActionEvent event){
        studentsListView.getItems().clear();
        if (studentNumberTextField.getText().equals("")){
            if (ImportStudentList.getListStudents() != null){
                for (Student student: ImportStudentList.getListStudents()){
                    if (student.getName().contains(studentName)){
                        studentsListView.getItems().add(student.getStudentNumber());
                        studentsListView.getItems().add(student.getName());
                        studentsListView.getItems().add("");
                    }
                }
            }
        }
        else if (studentNameTextField.getText().equals("")){
            if (ImportStudentList.getListStudents() != null){
                for (Student student: ImportStudentList.getListStudents()){
                    if (student.getStudentNumber().contains(studentNumber)){
                        studentsListView.getItems().add(student.getStudentNumber());
                        studentsListView.getItems().add(student.getName());
                        studentsListView.getItems().add("");
                    }
                }
            }
        }
    }

    /*
    @FXML
    private void displayStudentInfo(ActionEvent event){
        System.out.println("Yuh");
        String chosenStudentName = (String) studentsListView.getSelectionModel().getSelectedItem();
        System.out.println(chosenStudentName);
    }
     */

    @FXML public void handleMouseClick() {
        int lineNumber = studentsListView.getSelectionModel().getSelectedIndex() + 1;
        if (lineNumber%3 == 1) {
            chosenStudentInfoListView.getItems().clear();
            for (Student currentStudent: ImportStudentList.getListStudents()) {
                if (currentStudent.getStudentNumber().equals(studentsListView.getSelectionModel().getSelectedItem())) {
                    chosenStudentInfoListView.getItems().add(currentStudent.getStudentNumber());
                    chosenStudentInfoListView.getItems().add(currentStudent.getName());
                    chosenStudentInfoListView.getItems().add(currentStudent.getGrade());
                    chosenStudentInfoListView.getItems().add("");
                    for (Course currentCourse: currentStudent.getCoursesChosen()) {
                        chosenStudentInfoListView.getItems().add(currentCourse.getCourseCode());
                        System.out.println(currentCourse.getCourseCode());
                    }
                }
            }
        }
    }


    public void backToMainMenu(ActionEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene pageScene = new Scene(page);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(pageScene);
        appStage.show();
    }

}
