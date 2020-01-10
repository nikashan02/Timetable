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
    @FXML
    Button mainMenuBtn = new Button();

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

                    for (Course course: ImportStudentList.getListCourses()){
                        coursesListView.getItems().add(course.getCourseCode());
                    }

                }
            }
        }
    }

    public void removeButtonPressed(ActionEvent event) {
        for (ArrayList<Classroom> semester: ImportStudentList.getMasterTimeTable().getTable()) {
            for (Classroom currentClass: semester) {
                Iterator<Student> itr = currentClass.getStudents().iterator();
                while (itr.hasNext()) {
                    Student currentStudent = itr.next();
                    if (currentStudent.getStudentNumber().equals(studentNumberTextField.getText())) {
                        itr.remove();

                        Iterator<Student> studentItr = ImportStudentList.getListStudents().iterator();

                        while (studentItr.hasNext()) {
                            Student studentToDelete = studentItr.next();
                            if (studentToDelete.getStudentNumber().equals(studentNumberTextField.getText())) {
                                studentItr.remove();
                            }
                        }

                    }
                }
            }
        }
    }

    public void changeCourse(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String id = btn.getId();
        String selectedItem = (String) coursesListView .getSelectionModel().getSelectedItem();
        boolean possible;
        if (id.equals("course1Button")){
            possible  = switchCourses(course1Label.getText(), selectedItem);
            if (possible){
                course1Label.setText(selectedItem);
            }else{
                course1Label.setText("Class is Full!");
            }
        }
        if (id.equals("course2Button")){
            possible  = switchCourses(course2Label.getText(), selectedItem);
            if (possible){
                course2Label.setText(selectedItem);
            }else{
                course2Label.setText("Class is Full!");
            }
        }
        if (id.equals("course3Button")){
            possible  = switchCourses(course3Label.getText(), selectedItem);
            if (possible){
                course3Label.setText(selectedItem);
            }else{
                course3Label.setText("Class is Full!");
            }
        }
        if (id.equals("course4Button")){
            possible  = switchCourses(course4Label.getText(), selectedItem);
            if (possible){
                course4Label.setText(selectedItem);
            }else{
                course4Label.setText("Class is Full!");
            }
        }
        if (id.equals("course5Button")){
            possible  = switchCourses(course5Label.getText(), selectedItem);
            if (possible){
                course5Label.setText(selectedItem);
            }else{
                course5Label.setText("Class is Full!");
            }
        }
        if (id.equals("course6Button")){
            possible  = switchCourses(course6Label.getText(), selectedItem);
            if (possible){
                course6Label.setText(selectedItem);
            }else{
                course6Label.setText("Class is Full!");
            }
        }
        if (id.equals("course7Button")){
            possible  = switchCourses(course7Label.getText(), selectedItem);
            if (possible){
                course7Label.setText(selectedItem);
            }else{
                course7Label.setText("Class is Full!");
            }
        }
        if (id.equals("course8Button")){
            possible  = switchCourses(course8Label.getText(), selectedItem);
            if (possible){
                course8Label.setText(selectedItem);
            }else{
                course8Label.setText("Class is Full!");
            }
        }

    }

    public boolean switchCourses(String previousClassroomLabel, String addedClassroomLabel){
        boolean possible = false;
        int currentSemester = 0;
        boolean found = false;
        int previousClassroomIndex = -1;
        int potentialClassroomIndex = -1;
        int previousCourseIndex = 0;
        int newCourseIndex = 0;

        for (ArrayList<Classroom> checkSemester: ImportStudentList.getMasterTimeTable().getTable()){
            for(Classroom checkClassroom: checkSemester){
                previousClassroomIndex++;
                if (checkClassroom.getCourse().getCourseCode().equals(previousClassroomLabel)){
                    found= true;
                    break;
                }
            }
            if (found){
                break;
            }
            //Makes sure the semester never gets to 2 otherwise code breaks because semester can either only equal 0 or 1
            if (currentSemester == 1){
                break;
            }
            currentSemester++;
        }

        for (Classroom potentialClassroom: ImportStudentList.getMasterTimeTable().getTable().get(currentSemester)){
            potentialClassroomIndex = 0;
            if (potentialClassroom.getCourse().getCourseCode().equals(addedClassroomLabel) && potentialClassroom.getAvailableSeats()>0){
                possible = true;
                break;
            }
            potentialClassroomIndex++;

        }

        if (possible){
            for(Student currentStudent: ImportStudentList.getMasterTimeTable().getTable().get(currentSemester).get(previousClassroomIndex).getStudents()){
                if (currentStudent.getStudentNumber().equals(studentNumberTextField.getText())){
                    ImportStudentList.getMasterTimeTable().getTable().get(currentSemester).get(previousClassroomIndex).getStudents().remove(currentStudent);
                    break;
                }
            }
            for(Student currentStudent2: ImportStudentList.getMasterTimeTable().getTable().get(currentSemester).get(previousClassroomIndex).getStudents()){
                if (currentStudent2.getStudentNumber().equals(studentNumberTextField.getText())){
                    ImportStudentList.getMasterTimeTable().getTable().get(currentSemester).get(potentialClassroomIndex).getStudents().add(currentStudent2);
                    break;
                }
            }
            for (Student thisStudent: ImportStudentList.getListStudents()){
                int prevCounter = -1;
                int newCounter = -1;
                if (thisStudent.getStudentNumber().equals(studentNumberTextField.getText())){
                    for (Course tempCourse: thisStudent.getCoursesChosen()){
                        prevCounter++;
                        if (tempCourse.getCourseCode().equals(previousClassroomLabel)){
                            previousCourseIndex = prevCounter;
                            break;
                        }
                    }
                    for (Course tempCourse: ImportStudentList.getListCourses()){
                        newCounter++;
                        if (tempCourse.getCourseCode().equals(addedClassroomLabel)){
                            newCourseIndex = newCounter;
                            break;
                        }
                    }
                    thisStudent.getCoursesChosen().remove(previousCourseIndex);
                    thisStudent.getCoursesChosen().add(previousCourseIndex, ImportStudentList.getListCourses().get(newCourseIndex));
                    break;
                }
            }
        }
        return possible;
    }


    public void backToMainMenu(ActionEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene pageScene = new Scene(page);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(pageScene);
        appStage.show();
    }


}


