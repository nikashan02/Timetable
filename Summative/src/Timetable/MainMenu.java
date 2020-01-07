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

public class MainMenu {
    @FXML
    Button createTimetable = new Button();
    @FXML
    Button studentInfoBtn = new Button();
    @FXML
    Button EditStudentBtn = new Button();



    public void ButtonAction(ActionEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("ImportStudentList.fxml"));
        Scene pageScene = new Scene(page);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(pageScene);
        appStage.show();

    }

    public void seeStudentInfoBtnAction(ActionEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("SeeStudentInfo.fxml"));
        Scene pageScene = new Scene(page);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(pageScene);
        appStage.show();

    }

    public void editStudentBtnAction(ActionEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("EditStudent.fxml"));
        Scene pageScene = new Scene(page);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(pageScene);
        appStage.show();
    }



}
