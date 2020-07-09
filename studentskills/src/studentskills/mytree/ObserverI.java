package studentskills.mytree;

import studentskills.util.Operation;

public interface ObserverI {
    void update(StudentRecord s, Operation op);
}