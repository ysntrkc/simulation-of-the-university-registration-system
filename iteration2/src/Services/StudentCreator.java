package iteration2.src.Services;

import iteration2.src.Models.*;
import iteration2.src.Services.TranscriptCreator;

import java.util.ArrayList;
import java.util.Calendar;
//This class creates students randomly at the beginning.
public class StudentCreator {
    private Semester semester;
    private Advisor advisor;
    private ArrayList<Course> takenCourses;

    public StudentCreator(Semester semester, Advisor advisor, ArrayList<Course> takenCourses) {
        this.semester = semester;
        this.advisor = advisor;
        this.takenCourses = takenCourses;
    }


    public Student createRandomStudent(int index, String name) {
        StudentID studentID = generateStudentID(index); //We generate a student number according to certain parameters. (Department is fixed)
        Transcript transcript = new TranscriptCreator().generateTranscript(takenCourses); //We generate a transcript for the students.

        return new Student(name, studentID, semester, advisor, transcript); //We return the student.
    }


    private StudentID generateStudentID(int index) {
        String department = "1501"; //Currently, department number is fixed.
        //We calculate the entry year according to semester number.
        String entryYear = ((Calendar.getInstance().get(Calendar.YEAR) - (int) (Math.ceil(semester.getSemesterNo() / 2.0))) % 100) + "";
        String entryOrder;  //We calculate this according to creation order.
        if (index < 10) {
            entryOrder = "00" + index;
        } else if (index < 100) {
            entryOrder = "0" + index;
        } else {
            entryOrder = "" + index;
        }

        return new StudentID(department, entryYear, entryOrder);    //We return the student id.
    }


}