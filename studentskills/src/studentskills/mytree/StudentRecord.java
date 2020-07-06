package studentskills.mytree;

import java.util.ArrayList;

public class StudentRecord implements SubjectI, ObserverI {
    StudentRecord left;
    StudentRecord right;
    final Integer bNumber;
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

        /*
         * for (String s : skills) { if (!this.skills.contains(s)) this.skills.add(s); }
         */
    }

}