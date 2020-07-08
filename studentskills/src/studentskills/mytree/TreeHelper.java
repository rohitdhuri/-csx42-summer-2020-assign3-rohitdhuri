package studentskills.mytree;

import java.util.ArrayList;

public class TreeHelper {
    private StudentRecord root;


    public TreeHelper(){
        root = null;
    }

    public void add(StudentRecord newRecord){
        root = this.addRecursive(root, newRecord);
        
    }

    public StudentRecord addRecursive( StudentRecord root, StudentRecord newRecord){
        Integer bNumber = newRecord.getBNumber();

        if(root == null){
            root = newRecord;
            return root;
        }
        
        else if(bNumber < root.getBNumber())
            root.setLeft(this.addRecursive(root.getLeft(), newRecord));
        
        else if(bNumber > root.getBNumber())
            root.setRight(this.addRecursive(root.getRight(), newRecord));
        

        return root;
    }


    public void addToTree(StudentRecord replica_0, StudentRecord replica_1, StudentRecord replica_2, StudentRecord Node0, StudentRecord Node1, StudentRecord Node2){
    }


    public StudentRecord findNode(Integer bNumber){
    
        StudentRecord curr = root;

        while(curr != null){
            if(curr.getBNumber() == bNumber){
                return curr;
            }
            else if(curr.getBNumber() > bNumber){
                curr = curr.getLeft();
            }
            else if(curr.getBNumber() < bNumber){
                curr = curr.getRight();
            }
        }
        return null;
    }
}