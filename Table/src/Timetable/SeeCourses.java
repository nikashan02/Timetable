package Timetable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SeeCourses {

    @FXML
    Button mainMenuBtn = new Button();
    @FXML
    TextField searchCourse= new TextField();
    @FXML
    ListView displayCourses = new ListView();
    @FXML
    ListView viewClassrooms = new ListView();
    @FXML
    Button searchBtn = new Button();


    public void backToMainMenu(ActionEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene pageScene = new Scene(page);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(pageScene);
        appStage.show();
    }

    public void searchCourses (ActionEvent event){
        displayCourses.getItems().clear();
        viewClassrooms.getItems().clear();
        String codeEntered = searchCourse.getText();
        for (Course currentCourse: ImportStudentList.getListCourses()){
            if (currentCourse.getCourseCode().contains(codeEntered)) {
                displayCourses.getItems().add(currentCourse.getCourseCode());
            }
        }
    }

    public void viewClassesMethod () {
        viewClassrooms.getItems().clear();
        String selectedItem = (String) displayCourses.getSelectionModel().getSelectedItem();
        for (ArrayList<Classroom> semester: ImportStudentList.getMasterTimeTable().getTable()) {
            for(Classroom currentClass: semester) {
                if (currentClass.getCourse().getCourseCode().equals(selectedItem)){
                        viewClassrooms.getItems().add(currentClass.getClassCode());
                    }
                }
            }
        }
    }


