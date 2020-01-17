package Timetable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SeeClassrooms {
    @FXML
    Button mainMenuBtn = new Button();
    @FXML
    TextField searchClassroom = new TextField();
    @FXML
    Button searchBtn = new Button();
    @FXML
    ListView viewClassrooms = new ListView();
    @FXML
    ListView viewStudents = new ListView();
    @FXML
    Label numberSeats = new Label();

    public void backToMainMenu(ActionEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene pageScene = new Scene(page);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(pageScene);
        appStage.show();
    }

    public void searchClassrooms (ActionEvent event){
        viewClassrooms.getItems().clear();
        String codeEntered = searchClassroom.getText();
        for (ArrayList<Classroom> semester: ImportStudentList.getMasterTimeTable().getTable()) {
            for(Classroom currentClass: semester) {
                if (currentClass.getClassCode().contains(codeEntered)){
                    viewClassrooms.getItems().add(currentClass.getClassCode());
                }
            }
        }

    }

    public void viewStudentsMethod () {
        viewStudents.getItems().clear();
        String selectedItem = (String) viewClassrooms .getSelectionModel().getSelectedItem();
        for (ArrayList<Classroom> semester: ImportStudentList.getMasterTimeTable().getTable()) {
            for(Classroom currentClass: semester) {
                if (currentClass.getClassCode().equals(selectedItem)){
                    numberSeats.setText(String.valueOf(currentClass.getAvailableSeats()));
                    for(Student current: currentClass.getStudents()){
                        viewStudents.getItems().add(current.getStudentNumber());
                        viewStudents.getItems().add(current.getName());
                        viewStudents.getItems().add("");
                    }
                }
            }
        }
    }
}
