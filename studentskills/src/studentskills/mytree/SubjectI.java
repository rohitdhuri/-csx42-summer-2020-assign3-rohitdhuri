package studentskills.mytree;

import java.util.Set;

import studentskills.util.Operation;
import studentskills.util.exception.InvalidInputFormat;

public interface SubjectI {

    void recordChanged(String value, String replacement) throws InvalidInputFormat;

    void recordChanged(Integer bNumber, String inFirstName, String inLastName, Double inGpa, String inMajor,
            Set<String> inSkills);

    void notifyObservers(Operation op);
}