package Timetable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;

public class AddStudent {
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
    Button addStudentButton = new Button();
    @FXML
    Button addCourseButton = new Button();
    @FXML
    Button displayCourses = new Button();
    @FXML
    Button mainMenuBtn = new Button();
    @FXML
    ListView coursesListView = new ListView();

    ArrayList<Course>studentCourses = new ArrayList<Course>();
    ArrayList<Classroom>studentClassrooms = new ArrayList<Classroom>();
    int selectedCourseButton;
    int coursesInSem1 = 0;
    int coursesInSem2 = 0;


    public void backToMainMenu(ActionEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene pageScene = new Scene(page);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(pageScene);
        appStage.show();
    }

    public void displayCoursesAction(ActionEvent event){
        coursesListView.getItems().clear();
        for (Course course: ImportStudentList.getListCourses()){
            coursesListView.getItems().add(course.getCourseCode());
        }
    }

    public void course1ButtonAction(ActionEvent event) { selectedCourseButton = 1; }
    public void course2ButtonAction(ActionEvent event) { selectedCourseButton = 2; }
    public void course3ButtonAction(ActionEvent event) { selectedCourseButton = 3; }
    public void course4ButtonAction(ActionEvent event) { selectedCourseButton = 4; }
    public void course5ButtonAction(ActionEvent event) { selectedCourseButton = 5; }
    public void course6ButtonAction(ActionEvent event) { selectedCourseButton = 6; }
    public void course7ButtonAction(ActionEvent event) { selectedCourseButton = 7; }
    public void course8ButtonAction(ActionEvent event) { selectedCourseButton = 8; }

    public void addCourse(ActionEvent event) {
        boolean possible = true;
        boolean foundClassroom = false;
        Classroom classToAddTo = null;
        String courseStringToAdd = (String) coursesListView.getSelectionModel().getSelectedItem();
        for (Course courseToCheckWith: studentCourses) {
            if (courseStringToAdd.equals(courseToCheckWith.getCourseCode())) {
                possible = false;
                break;
            }
        }
        if (possible) {
            possible = false;
            int currentSemester = 0;
            for (ArrayList<Classroom> semester: ImportStudentList.getMasterTimeTable().getTable()) {
                for (Classroom potentialClassroom: semester) {
                    if (potentialClassroom.getAvailableSeats() > 0) {
                        if (potentialClassroom.getCourse().getCourseCode().equals(courseStringToAdd)) {
                            if (currentSemester == 0) {

                                if (coursesInSem1 < 4) {
                                    for (Classroom classroomToCheckWith : studentClassrooms) {

                                        int checkSemesterNumber = 0;
                                        boolean foundSemester = false;

                                        for (ArrayList<Classroom> checkSemester: ImportStudentList.getMasterTimeTable().getTable()) {
                                            for (Classroom checkClassroom: checkSemester) {
                                                if (checkClassroom.getClassCode().equals(classroomToCheckWith.getClassCode())) {
                                                    foundSemester = true;
                                                    break;
                                                }
                                            }
                                            if (foundSemester) {
                                                break;
                                            }
                                            checkSemesterNumber++;
                                        }

                                        if (checkSemesterNumber == currentSemester) {
                                            if (!foundClassroom) {
                                                if (potentialClassroom.getPeriod() != classroomToCheckWith.getPeriod()) {
                                                    possible = true;
                                                    foundClassroom = true;
                                                    classToAddTo = potentialClassroom;
                                                } else {
                                                    possible = false;
                                                    break;
                                                }
                                            }
                                        }

                                    }
                                }

                            }
                            else if (currentSemester == 1) {

                                if (coursesInSem2 < 4) {
                                    for (Classroom classroomToCheckWith : studentClassrooms) {

                                        int checkSemesterNumber = 0;
                                        boolean foundSemester = false;

                                        for (ArrayList<Classroom> checkSemester: ImportStudentList.getMasterTimeTable().getTable()) {
                                            for (Classroom checkClassroom: checkSemester) {
                                                if (checkClassroom.getClassCode().equals(classroomToCheckWith.getClassCode())) {
                                                    foundSemester = true;
                                                    break;
                                                }
                                            }
                                            if (foundSemester) {
                                                break;
                                            }
                                            checkSemesterNumber++;
                                        }

                                        if (checkSemesterNumber == currentSemester) {
                                            if (!foundClassroom) {
                                                if (potentialClassroom.getPeriod() != classroomToCheckWith.getPeriod()) {
                                                    possible = true;
                                                    foundClassroom = true;
                                                    classToAddTo = potentialClassroom;
                                                } else {
                                                    possible = false;
                                                    break;
                                                }
                                            }
                                        }

                                    }
                                }

                            }
                        }
                    }
                    if (possible) {
                        break;
                    }
                }
                currentSemester++;
                if (possible) {
                    break;
                }
            }
        }

        if (possible) {
            studentClassrooms.add(classToAddTo);
            for (Course checkCourse: ImportStudentList.getListCourses()) {
                if (checkCourse.getCourseCode().equals(courseStringToAdd)) {
                    studentCourses.add(checkCourse);
                    break;
                }
            }

            if (selectedCourseButton == 1) { course1Label.setText(courseStringToAdd); }
            else if (selectedCourseButton == 2) { course2Label.setText(courseStringToAdd); }
            else if (selectedCourseButton == 3) { course3Label.setText(courseStringToAdd); }
            else if (selectedCourseButton == 4) { course4Label.setText(courseStringToAdd); }
            else if (selectedCourseButton == 5) { course5Label.setText(courseStringToAdd); }
            else if (selectedCourseButton == 6) { course6Label.setText(courseStringToAdd); }
            else if (selectedCourseButton == 7) { course7Label.setText(courseStringToAdd); }
            else if (selectedCourseButton == 8) { course8Label.setText(courseStringToAdd); }

        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Sorry, that course cannot be added");
            alert.setContentText("Please select another course to add");
            alert.showAndWait();
        }
    }

    public void addStudentButtonAction(ActionEvent event) {
        if (studentCourses.size() == 8) {
            Student thisStudent = new Student(nameTextField.getText(), studentNumberTextField.getText(), gradeTextField.getText(), studentCourses);
            for (Classroom toAddClass : studentClassrooms) {
                for (ArrayList<Classroom> semester : ImportStudentList.getMasterTimeTable().getTable()) {
                    for (Classroom currentClass : semester) {
                        if (currentClass.getClassCode().equals(toAddClass.getClassCode())) {
                            currentClass.addStudent(thisStudent);
                        }
                    }
                }
            }
        }
    }

}
