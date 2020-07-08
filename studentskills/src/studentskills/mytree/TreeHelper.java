package studentskills.mytree;

import java.util.ArrayList;

public class TreeHelper {
    private StudentRecord root;
    private String outputBuffer;

    public TreeHelper() {
        root = null;
    }

    public void add(StudentRecord newRecord) {
        root = this.addRecursive(root, newRecord);

    }

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

    public String printInorder() {
        outputBuffer = "";
        inorder(root);
        return outputBuffer;
    }

    public void inorder(StudentRecord root) {
        if (root == null)
            return;

        /* first recur on left child */
        inorder(root.getLeft());

        /* then print the data of node */
        outputBuffer += root.getBNumber() +" "+ root.getFirstName() +" "+ root.getLastName() +" "+ root.getGpa() +" "+ root.getMajor()
                +" "+ root.getSkills() + "\n";

        /* now recur on right child */
        inorder(root.getRight());
    }

    public StudentRecord findNode(Integer bNumber) {

        StudentRecord curr = root;

        while (curr != null) {
            if (curr.getBNumber() == bNumber) {
                return curr;
            } else if (curr.getBNumber() > bNumber) {
                curr = curr.getLeft();
            } else if (curr.getBNumber() < bNumber) {
                curr = curr.getRight();
            }
        }
        return null;
    }
}