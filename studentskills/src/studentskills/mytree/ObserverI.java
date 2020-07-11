package studentskills.mytree;

import studentskills.util.Operation;

public interface ObserverI {
    /**
     * Updates the observers with new values from subject
     * @param s - subject
     * @param op - Specifies Insert or Modify type update
     */
    void update(StudentRecord s, Operation op);
}