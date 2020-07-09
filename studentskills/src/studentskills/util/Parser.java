package studentskills.util;

import java.io.IOException;
import java.util.ArrayList;

import studentskills.mytree.StudentRecord;
import studentskills.mytree.TreeHelper;

public class Parser {
    private FileProcessor iFp, mFp;
    private TreeHelper replicaTree_0, replicaTree_1, replicaTree_2;

    public Parser(FileProcessor iFp, FileProcessor mFp) {
        this.iFp = iFp;
        this.mFp = mFp;
        replicaTree_0 = new TreeHelper();
        replicaTree_1 = new TreeHelper();
        replicaTree_2 = new TreeHelper();
    }

    public void processInput() throws IOException {
        Integer bNumber;
        String str = iFp.poll();

        while (str != null) {
            String tokens[] = str.split(":");
            bNumber = Integer.parseInt(tokens[0]);

            str = tokens[1];
            String[] values = str.split(",");
            String firstName = values[0];
            String lastName = values[1];
            Double gpa = Double.parseDouble(values[2]);
            String major = values[3];
            ArrayList<String> skills = new ArrayList<String>();
            Integer i = 0;
            while (i < (values.length - 4)) {
                if (values[i + 4] != null) {
                    skills.add(values[i + 4]);
                    i++;
                } else {
                    break;
                }
            }

            // System.out.println(bNumber + firstName + lastName + gpa + major+" "+skills);

            StudentRecord replicaNode0 = new StudentRecord(bNumber, firstName, lastName, gpa, major, skills);
            StudentRecord replicaNode1 = replicaNode0.clone();
            StudentRecord replicaNode2 = replicaNode0.clone();
            // if (replicaNode0 != null)
            // System.out.println("helper " + replicaNode0.getBNumber());
            // if(replicaNode2 != null)
            // System.out.println("helper "+ replicaNode2);

            replicaNode0.register(replicaNode1, replicaNode2);
            replicaTree_0.add(replicaNode0);

            replicaNode1.register(replicaNode0, replicaNode2);
            replicaTree_1.add(replicaNode1);

            replicaNode2.register(replicaNode0, replicaNode1);
            replicaTree_2.add(replicaNode2);

            str = iFp.poll();
        }
        String tree0 = replicaTree_0.printInorder();
        String tree1 = replicaTree_0.printInorder();
        String tree2 = replicaTree_0.printInorder();

        System.out.println(" " + tree0 + "\n" + " " + tree1 + "\n" + " " + tree2 + "\n");
    }

    public void processModify() throws IOException {

        String str = mFp.poll();

        while (str != null) {
            String treeNumber = str.split(",")[0];
            String bNumber = str.split(",")[1];
            String value = (str.split(",")[2]).split(":")[0];
            String replacement = (str.split(",")[2]).split(":")[1];

        //    System.out.println("treenumber " + treeNumber + " bNumber " + bNumber + " value" + value + " replacement "
          //          + replacement);

            if (treeNumber.equals(Replica.replicaTree_0.getConstantValue())) {
                StudentRecord node = replicaTree_0.findNode(Integer.parseInt(bNumber));
                if (node != null) {
                    node.recordChanged(value, replacement);
                    node.notifyObservers();
                  //  node.update();
                }
            }
            else if (treeNumber.equals(Replica.replicaTree_1.getConstantValue())) {
                StudentRecord node = replicaTree_1.findNode(Integer.parseInt(bNumber));
                if (node != null) {
                    node.recordChanged(value, replacement);
                    node.notifyObservers();
                  //  node.update();
                }
            }
            else if (treeNumber.equals(Replica.replicaTree_2.getConstantValue())) {
                StudentRecord node = replicaTree_2.findNode(Integer.parseInt(bNumber));
                if (node != null) {
                    node.recordChanged(value, replacement);
                    System.out.println(" flag after change "+node.lastNameFlag);
                    node.notifyObservers();
                    //node.update();
                }
            }

            str = mFp.poll();
        }

        String tree0 = replicaTree_0.printInorder();
        String tree1 = replicaTree_1.printInorder();
        String tree2 = replicaTree_2.printInorder();

        System.out.println(" " + tree0 + "\n" + " " + tree1 + "\n" + " " + tree2 + "\n");

    }

}