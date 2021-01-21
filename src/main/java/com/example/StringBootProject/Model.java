package com.example.StringBootProject;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document
public class Model {

    @Id
    private String studentId;
    private String studentName;
    private String studentAddress;
    private String studentCollege;

    @Override
    public String toString() {
        return "Model{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", studentCollege='" + studentCollege + '\'' +
                ", cources=" + cources +
                '}';
    }

    public Model(String studentId, String studentName, String studentAddress, String studentCollege, ArrayList<String> cources) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentCollege = studentCollege;
        this.cources = cources;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentCollege() {
        return studentCollege;
    }

    public void setStudentCollege(String studentCollege) {
        this.studentCollege = studentCollege;
    }

    public ArrayList<String> getCources() {
        return cources;
    }

    public void setCources(ArrayList<String> cources) {
        this.cources = cources;
    }

    private ArrayList<String> cources = new ArrayList<String>();


}