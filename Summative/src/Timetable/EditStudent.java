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

public class EditStudent {
    @FXML
    TextField studentNumberTextField = new TextField();
    @FXML
    TextField nameTextField = new TextField();
    @FXML
    TextField gradeTextField = new TextField();
    @FXML
    Button course1Button = new Button();
    @FXML
    Button course2Button = new Button();
    @FXML
    Button course3Button = new Button();
    @FXML
    Button course4Button = new Button();
    @FXML
    Button course5Button = new Button();
    @FXML
    Button course6Button = new Button();
    @FXML
    Button course7Button = new Button();
    @FXML
    Button course8Button = new Button();
    @FXML
    Label course1Label = new Label();
    @FXML
    Label course2Label = new Label();
    @FXML
    Label course3Label = new Label();
    @FXML
    Label course4Label = new Label();
    @FXML
    Label course5Label = new Label();
    @FXML
    Label course6Label = new Label();
    @FXML
    Label course7Label = new Label();
    @FXML
    Label course8Label = new Label();
    @FXML
    Button saveButton = new Button();
    @FXML
    Button removeStudentButton = new Button();
    @FXML
    ListView coursesListView = new ListView();
    @FXML
    Button searchBtn = new Button();

    private String studentNumber = "";

    public void studentNumberTextFieldAction(ActionEvent event){
        studentNumber = studentNumberTextField.getText();
    }

    public void searchButtonPressed(ActionEvent event){
        nameTextField.clear();
        gradeTextField.clear();
        coursesListView.getItems().clear();
        if (ImportStudentList.getListStudents() != null){
            for (Student student: ImportStudentList.getListStudents()){
                if(studentNumber.equals(student.getStudentNumber())){
                    nameTextField.setText(student.getName());
                    gradeTextField.setText(student.getGrade());
                    int counter = 0;
                    for (Course currentCourse: student.getCoursesChosen()) {
                        counter += 1;
                        switch(counter){
                            case 1:
                                course1Label.setText(currentCourse.getCourseCode());
                            case 2:
                                course2Label.setText(currentCourse.getCourseCode());
                            case 3:
                                course3Label.setText(currentCourse.getCourseCode());
                            case 4:
                                course4Label.setText(currentCourse.getCourseCode());
                            case 5:
                                course5Label.setText(currentCourse.getCourseCode());
                            case 6:
                                course6Label.setText(currentCourse.getCourseCode());
                            case 7:
                                course7Label.setText(currentCourse.getCourseCode());
                            case 8:
                                course8Label.setText(currentCourse.getCourseCode());
                        }
                    }
                    if (student.getGrade().equals("9")){

                    }
                }
            }
        }
    }



}


