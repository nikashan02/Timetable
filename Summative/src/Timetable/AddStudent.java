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
    Button searchBtn = new Button();
    @FXML
    Button mainMenuBtn = new Button();
    @FXML
    ListView coursesListView = new ListView();

    
}
