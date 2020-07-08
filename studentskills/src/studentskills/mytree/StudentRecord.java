package studentskills.mytree;

import java.util.ArrayList;

public class StudentRecord implements SubjectI, ObserverI, Cloneable {
    private StudentRecord left;
    private StudentRecord right;
    private ArrayList<StudentRecord> observers;

    private final Integer bNumber;
    private String firstName, lastName, major;
    private Double gpa;
    private ArrayList<String> skills;

    public StudentRecord(Integer bNumber, String firstName, String lastName, Double gpa, String major,
            ArrayList<String> skills) {
        this.bNumber = bNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gpa = gpa;
        this.major = major;
        this.skills = new ArrayList<String>(skills);

        left = null;
        right = null;
        /*
         * for (String s : skills) { if (!this.skills.contains(s)) this.skills.add(s); }
         */


    }



    public StudentRecord getRight(){
        return right;
    }

    public StudentRecord getLeft(){
        return left;
    }

    public Integer getBNumber(){
        return bNumber;
    }

    public void setLeft(StudentRecord node){
        this.left = node;
    }

    public void setRight(StudentRecord node){
        this.right = node;
    }

    public void registerObservers(StudentRecord replicaNode1, StudentRecord replicaNode2){
        observers.add(replicaNode1);
        observers.add(replicaNode2);
    }

    public StudentRecord clone()
    {
        return new StudentRecord(bNumber, firstName, lastName, gpa, major, skills);
    }
}