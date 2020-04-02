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

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class ViewTimetable {
    @FXML
    Label sem1Period1Label = new Label();
    @FXML
    Label sem1Period2Label = new Label();
    @FXML
    Label sem1Period3Label = new Label();
    @FXML
    Label sem1Period4Label = new Label();
    @FXML
    Label sem2Period1Label = new Label();
    @FXML
    Label sem2Period2Label = new Label();
    @FXML
    Label sem2Period3Label = new Label();
    @FXML
    Label sem2Period4Label = new Label();
    @FXML
    TextField studentNumberTextField = new TextField();
    @FXML
    Button searchBtn = new Button();
    @FXML
    Button mainMenuBtn = new Button();


    private String studentNumber = "";

    public void studentNumberTextFieldAction(ActionEvent event){ studentNumber = studentNumberTextField.getText(); }

    public void searchButtonPressed (ActionEvent event) {
        sem1Period1Label.setText("");
        sem1Period2Label.setText("");
        sem1Period3Label.setText("");
        sem1Period4Label.setText("");
        sem2Period1Label.setText("");
        sem2Period2Label.setText("");
        sem2Period3Label.setText("");
        sem2Period4Label.setText("");
        for (Classroom currentClass: ImportStudentList.getMasterTimeTable().getTable().get(0)){
            for (Student currentStudent: currentClass.getStudents()){
                if (currentStudent.getStudentNumber().equals(studentNumber)) {
                    switch (currentClass.getPeriod()){
                        case 1:
                            sem1Period1Label.setText(currentClass.getClassCode());
                            break;
                        case 2:
                            sem1Period2Label.setText(currentClass.getClassCode());
                            break;
                        case 3:
                            sem1Period3Label.setText(currentClass.getClassCode());
                            break;
                        case 4:
                            sem1Period4Label.setText(currentClass.getClassCode());
                            break;
                    }
                }
            }
        }
        for (Classroom currentClass: ImportStudentList.getMasterTimeTable().getTable().get(1)){
            for (Student currentStudent: currentClass.getStudents()){
                if (currentStudent.getStudentNumber().equals(studentNumber)) {
                    switch (currentClass.getPeriod()){
                        case 1:
                            sem2Period1Label.setText(currentClass.getClassCode());
                            break;
                        case 2:
                            sem2Period2Label.setText(currentClass.getClassCode());
                            break;
                        case 3:
                            sem2Period3Label.setText(currentClass.getClassCode());
                            break;
                        case 4:
                            sem2Period4Label.setText(currentClass.getClassCode());
                            break;
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
