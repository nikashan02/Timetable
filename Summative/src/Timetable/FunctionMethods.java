package Timetable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FunctionMethods {

    public static ArrayList<Student> importStudents(String name) {
        String fileName = name;
        File file = new File(fileName);
        ArrayList<Student> listOfStudents = new ArrayList<Student>();
        try {
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()) {
                String data = inputStream.next();
                String[] values = data.split(",");
                ArrayList<Course> courses = new ArrayList<Course>();
                for(int x = 3; x<11; x++) {
                    Course currentCourse = new Course(values[x]);
                    courses.add(currentCourse);
                }
                listOfStudents.add(new Student(values[0], values[1], values[2], courses));
            }
            inputStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return listOfStudents;
    }

}
