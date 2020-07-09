package studentskills.mytree;

import java.util.Set;

import studentskills.util.Operation;

public interface SubjectI {

    void recordChanged(String value, String replacement);

    void recordChanged(Integer bNumber, String inFirstName, String inLastName, Double inGpa, String inMajor,
            Set<String> inSkills);

    void notifyObservers(Operation op);
}