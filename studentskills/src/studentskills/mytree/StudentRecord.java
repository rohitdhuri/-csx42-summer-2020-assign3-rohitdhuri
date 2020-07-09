package studentskills.mytree;

import studentskills.util.Operation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StudentRecord implements SubjectI, ObserverI, Cloneable {
    private StudentRecord left;
    private StudentRecord right;

    private final Integer bNumber;
    private String firstName, lastName, major;
    private Double gpa;
    private Set<String> skills;
    ArrayList<StudentRecord> observers;

    public StudentRecord(Integer bNumber, String firstName, String lastName, Double gpa, String major,
            Set<String> skills) {
        this.bNumber = bNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gpa = gpa;
        this.major = major;
        this.skills = new HashSet<String>(skills);
        observers = new ArrayList<StudentRecord>();

        left = null;
        right = null;

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

    public Set<String> getSkills() {
        return skills;
    }

    public void setLeft(StudentRecord node) {
        this.left = node;
    }

    public void setRight(StudentRecord node) {
        this.right = node;
    }

    public void recordChanged(String value, String replacement) {

        if (value.equals(firstName)) {
            firstName = replacement;
        } else if (value.equals(lastName)) {
            lastName = replacement;
        } else if (value.equals(major)) {
            major = replacement;
        } else if (skills.contains(value)) {
            skills.remove(value);
            skills.add(replacement);
        }
    }

    public void recordChanged(Integer bNumber, String inFirstName, String inLastName, Double inGpa, String inMajor,
            Set<String> inSkills) {
        if (!firstName.equals(inFirstName)) {
            firstName = inFirstName;
        }
        if (!lastName.equals(inLastName)) {
            lastName = inLastName;
        }
        if (!gpa.equals(inGpa)) {
            gpa = inGpa;
        }
        if (!major.equals(inMajor)) {
            major = inMajor;
        }
        if (!skills.equals(inSkills)) {
            skills.addAll(inSkills);
        }
    }

    public StudentRecord clone() {
        return new StudentRecord(bNumber, firstName, lastName, gpa, major, skills);
    }

    public void register(StudentRecord replicaNode) {
        StudentRecord ob = replicaNode;
        observers.add(ob);
    }

    public void update(StudentRecord subject, Operation op) {

        if (op.equals(Operation.MODIFY)) {
            if (!subject.getFirstName().equals(getFirstName())) {
                firstName = subject.getFirstName();
            } else if (!subject.getLastName().equals(getLastName())) {
                lastName = subject.getLastName();
            } else if (!subject.getMajor().equals(getMajor())) {
                major = subject.getMajor();
            } else if (!subject.getSkills().equals(getSkills())) {
                skills = subject.getSkills();
            }
        }
        if (op.equals(Operation.INSERT)) {
            firstName = subject.getFirstName();
            lastName = subject.getLastName();
            major = subject.getMajor();
            gpa = subject.getGpa();
            skills = subject.getSkills();
        }
    }

    @Override
    public void notifyObservers(Operation op) {

        for (ObserverI obs : observers) {
            obs.update(this, op);
        }
    }
}