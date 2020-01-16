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
import java.util.*;
import java.io.*;


import java.io.File;
import java.io.IOException;

public class MainMenu {
    @FXML
    Button createTimetable = new Button();
    @FXML
    Button studentInfoBtn = new Button();
    @FXML
    Button EditStudentBtn = new Button();
    @FXML
    Button SaveBtn = new Button();
    @FXML
    Button UpdateBtn = new Button();
    @FXML
    Button viewTimetableBtn = new Button();



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
    public void addStudentBtnAction(ActionEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
        Scene pageScene = new Scene(page);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(pageScene);
        appStage.show();
    }

    public void viewTimeTableBtnPressed(ActionEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("ViewTimetable.fxml"));
        Scene pageScene = new Scene(page);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(pageScene);
        appStage.show();
    }

    public void saveBtnAction(ActionEvent event) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("MasterTimetable");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ImportStudentList.getMasterTimeTable());
            oos.close();
            fos.close();

    }

        catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileOutputStream fos = new FileOutputStream("Students");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ImportStudentList.getListStudents());
            oos.close();
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileOutputStream fos = new FileOutputStream("Courses");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ImportStudentList.getListCourses());
            oos.close();
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateBtnAction(ActionEvent event) throws IOException {
        try {
            FileInputStream fis = new FileInputStream("MasterTimetable");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ImportStudentList.setMasterTimeTable((Table) ois.readObject());

            ois.close();
            fis.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c) {
            System.out.println("File could not be loaded");
            c.printStackTrace();
            return;
        }


        try {
            FileInputStream fis = new FileInputStream("Students");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ImportStudentList.setListStudents((ArrayList<Student>) ois.readObject());

            ois.close();
            fis.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c) {
            System.out.println("File could not be loaded");
            c.printStackTrace();
            return;
        }


        try {
            FileInputStream fis = new FileInputStream("Courses");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ImportStudentList.setListCourses((ArrayList<Course>) ois.readObject());

            ois.close();
            fis.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c) {
            System.out.println("File could not be loaded");
            c.printStackTrace();
            return;
        }

        UpdateBtn.setDisable(false);
        UpdateBtn.setVisible(false);
    }


}
