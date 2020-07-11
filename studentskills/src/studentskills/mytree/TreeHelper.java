package studentskills.mytree;

import studentskills.util.MyLogger;

/**
 * Contains all methods necessary to implement a Binary Search Tree.
 * 
 * @author Rohit Mahendra Dhuri
 *
 */
public class TreeHelper {
    private StudentRecord root;
    private String outputBuffer;

    /**
     * Constructor inilatizes root to null
     */
    public TreeHelper() {
        MyLogger.writeMessage("TreeHelper parameterized constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        root = null;
    }

    /**
     * Adds a node to the tree
     * 
     * @param newRecord - node to be added
     */
    public void add(StudentRecord newRecord) {
        root = this.addRecursive(root, newRecord);

    }

    /**
     * Method the travel to the bottom of a tree and add node
     * 
     * @param root
     * @param newRecord
     * @return - returns the root of updated tree
     */
    public StudentRecord addRecursive(StudentRecord root, StudentRecord newRecord) {
        Integer bNumber = newRecord.getBNumber();

        if (root == null) {
            root = newRecord;
            return root;
        }

        else if (bNumber < root.getBNumber())
            root.setLeft(this.addRecursive(root.getLeft(), newRecord));

        else if (bNumber > root.getBNumber())
            root.setRight(this.addRecursive(root.getRight(), newRecord));

        return root;
    }

    /**
     * Prints the nodes of tree into a buffer
     * 
     * @return - The Buffer of type string
     */
    public String printNodes() {
        outputBuffer = "";
        inorder(root);
        return outputBuffer;
    }

    /**
     * Travels the tree inorder to print nodes in ascending format
     * 
     * @param - root node of type studentRecord
     */
    public void inorder(StudentRecord root) {
        if (root == null)
            return;

        inorder(root.getLeft());
        outputBuffer += root.getBNumber() + " " + root.getSkills() + "\n";
        inorder(root.getRight());
    }

    /**
     * Finds a Node in the tree using the bNumber and then return that node
     * 
     * @param bNumber - bNumber of node to find
     * @return - Searched Node
     */
    public StudentRecord findRecord(Integer bNumber) {

        StudentRecord curr = root;
        while (curr != null) {
            if (curr.getBNumber().compareTo(bNumber) == 0) {
                return curr;
            } else if (curr.getBNumber().compareTo(bNumber) > 0) {
                curr = curr.getLeft();
            } else if (curr.getBNumber().compareTo(bNumber) < 0) {
                curr = curr.getRight();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Class: TreeHelper, Data Members: [root= " + root + " outputBuffer= " + outputBuffer + " ]";
    }
}