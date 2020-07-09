package studentskills.mytree;

public interface SubjectI {
    
    void recordChanged(String value, String replacement);

    void notifyObservers();
}