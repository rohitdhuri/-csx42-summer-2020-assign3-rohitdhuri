package studentskills.mytree;

import java.util.ArrayList;

public class StudentRecord implements SubjectI, ObserverI, Cloneable {
    private StudentRecord left;
    private StudentRecord right;
    private StudentRecord observer1;
    private StudentRecord observer2;

    private final Integer bNumber;
    private String firstName, lastName, major;
    private Double gpa;
    private ArrayList<String> skills;
    public Boolean firstNameFlag, lastNameFlag, majorFlag, skillsFlag;

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
        resetFlags();
        /*
         * for (String s : skills) { if (!this.skills.contains(s)) this.skills.add(s); }
         */

    }

    public StudentRecord getRight() {
        return right;
    }

    public StudentRecord getLeft() {
        return left;
    }

    public Integer getBNumber() {
        return bNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMajor() {
        return major;
    }

    public Double getGpa() {
        return gpa;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setLeft(StudentRecord node) {
        this.left = node;
    }

    public void setRight(StudentRecord node) {
        this.right = node;
    }

    public void setFirstName(String newValue) {
        firstName = newValue;
    }

    public void setLastName(String newValue) {
        lastName = newValue;
    }

    public void setMajor(String newValue) {
        major = newValue;
    }

    public void setSkills(ArrayList<String> newValue) {
        skills = newValue;
    }


    public void recordChanged(String value, String replacement) {

        if (value.equals(firstName)) {
            firstName = replacement;
            firstNameFlag = true;
        } else if (value.equals(lastName)) {
            lastName = replacement;
            lastNameFlag = true;
        } else if (value.equals(major)) {
            major = replacement;
            majorFlag = true;
        } else if (skills.contains(value)) {
            skills.remove(value);
            skills.add(replacement);
            skillsFlag = true;
        }

    }

    public StudentRecord clone() {
        return new StudentRecord(bNumber, firstName, lastName, gpa, major, skills);
    }

    public void register(StudentRecord replicaNode_1, StudentRecord replicaNode_2) {
        observer1 = replicaNode_1;
        observer2 = replicaNode_2;
    }

    public ArrayList<StudentRecord> getObservers(){
        ArrayList<StudentRecord> observers = new ArrayList<StudentRecord>();
        observers.add(observer1);
        observers.add(observer2);
        return observers;
    }

    public void resetFlags(){
        firstNameFlag= lastNameFlag= majorFlag = skillsFlag = false; 
    }

    public void update(StudentRecord subject){
        if(subject.firstNameFlag){
            firstName= subject.getFirstName();
        } 
        else if(subject.lastNameFlag){
            lastName= subject.getLastName();
        }
        else if(subject.majorFlag){
            major= subject.getMajor();
        }
        else if(subject.skillsFlag){
            skills = subject.getSkills();
        }
    }

    @Override
    public void notifyObservers(){

        observer1.update(this);
        observer2.update(this);
        resetFlags();

    }

}