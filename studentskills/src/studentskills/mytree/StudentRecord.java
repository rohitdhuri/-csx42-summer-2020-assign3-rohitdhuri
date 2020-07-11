package studentskills.mytree;

import studentskills.util.MyLogger;
import studentskills.util.Operation;
import studentskills.util.exception.InvalidInputFormat;

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

    /**
     * Constructor inializes all the lists, sets and other values.
     * 
     * @param bNumber   - Input String bNumber
     * @param firstName - Input string firstname
     * @param lastName  - Input string lastname
     * @param gpa       - Input doible gpa
     * @param major     - Input string major
     * @param skills    - Input Set of skills
     */
    public StudentRecord(Integer bNumber, String firstName, String lastName, Double gpa, String major,
            Set<String> skills) {
        MyLogger.writeMessage("StudentRecord parameterized constructor", MyLogger.DebugLevel.CONSTRUCTOR);

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

    /**
     * Return the right child node
     * 
     * @return - Object of type StudentRecord
     */
    public StudentRecord getRight() {
        return right;
    }

    /**
     * Returns the left child node
     * 
     * @return - Object of typw StudentRecord
     */
    public StudentRecord getLeft() {
        return left;
    }

    /**
     * Returns the bNumber
     * 
     * @return - Integer value
     */
    public Integer getBNumber() {
        return bNumber;
    }

    /**
     * Returns the firstname
     * 
     * @return - String value
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the lastname
     * 
     * @return - String value
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns teh major
     * 
     * @return - String value
     */
    public String getMajor() {
        return major;
    }

    /**
     * Returns the gpa
     * 
     * @return - Double value
     */
    public Double getGpa() {
        return gpa;
    }

    /**
     * Returns the set of skills
     * 
     * @return - Set of strings
     */
    public Set<String> getSkills() {
        return skills;
    }

    /**
     * Sets the left child
     * 
     * @param node - object of type StudentRecord for left child
     */
    public void setLeft(StudentRecord node) {
        this.left = node;
    }

    /**
     * Sets the right child
     * 
     * @param node - object of type StudentRecord for right child
     */
    public void setRight(StudentRecord node) {
        this.right = node;
    }

    /**
     * Replaces the old value with new
     * 
     * @param value       - field to be replaced
     * @param replacement - new value
     */
    @Override
    public void recordChanged(String value, String replacement) throws InvalidInputFormat {
        MyLogger.writeMessage("Replacing old value with new", MyLogger.DebugLevel.STUDENT_RECORD);

        if (value.equals(firstName)) {
            firstName = replacement;
        } else if (value.equals(lastName)) {
            lastName = replacement;
        } else if (value.equals(major)) {
            major = replacement;
        } else if (skills.contains(value)) {
            skills.remove(value);
            skills.add(replacement);
        } else {
            throw new InvalidInputFormat("Invalid modify field");
        }
    }

    /**
     * Replaces the old values with new
     * 
     * @param - All parameters are new values which are to be overwritten
     */
    @Override
    public void recordChanged(Integer bNumber, String inFirstName, String inLastName, Double inGpa, String inMajor,
            Set<String> inSkills) {
        MyLogger.writeMessage("Replacing old value with new", MyLogger.DebugLevel.STUDENT_RECORD);

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

    /**
     * Creates and returns a new node with all inialization values of the this node
     */
    public StudentRecord clone() {
        MyLogger.writeMessage("Cloning", MyLogger.DebugLevel.STUDENT_RECORD);
        return new StudentRecord(bNumber, firstName, lastName, gpa, major, skills);
    }

    /**
     * Adds a Node as observer for this node
     * 
     * @param replicaNode - node to be registered as observer
     */
    public void register(StudentRecord replicaNode) {
        StudentRecord ob = replicaNode;
        MyLogger.writeMessage("Calling add method to add observer to observers list",
                MyLogger.DebugLevel.STUDENT_RECORD);
        observers.add(ob);
    }

    /**
     * Updates the observers with new values from subject
     * 
     * @param s  - subject
     * @param op - Specifies Insert or Modify type update
     */
    @Override
    public void update(StudentRecord subject, Operation op) {

        if (op.equals(Operation.MODIFY)) {
            MyLogger.writeMessage("Update Modify operation", MyLogger.DebugLevel.STUDENT_RECORD);

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
            MyLogger.writeMessage("Update Insert operation", MyLogger.DebugLevel.STUDENT_RECORD);

            firstName = subject.getFirstName();
            lastName = subject.getLastName();
            major = subject.getMajor();
            gpa = subject.getGpa();
            skills = subject.getSkills();
        }
    }

    /**
     * Notifies all observers of change by updating them
     * 
     * @param op - Enum that specifies the operation
     */
    @Override
    public void notifyObservers(Operation op) {
        MyLogger.writeMessage("Updating all observers", MyLogger.DebugLevel.STUDENT_RECORD);
        for (ObserverI obs : observers) {
            obs.update(this, op);
        }
    }

    @Override
    public String toString() {
        return "Class: StudentRecord, Data Members: [ left= "+ left +" right ="+ right +" bNumber= "+ bNumber +" major= "+ major +" skills= "+ skills +" gpa= "+ gpa +" firstName= "+ firstName +" lastName= "+ lastName +" observers= "+ observers+ " ]";
    }
}