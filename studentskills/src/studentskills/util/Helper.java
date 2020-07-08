package studentskills.util;

import java.io.IOException;
import java.util.ArrayList;

import studentskills.mytree.StudentRecord;
import studentskills.mytree.TreeHelper;

public class Helper {
    private FileProcessor fp;
    private TreeHelper replicaTree0, replicaTree1, replicaTree2;

    public Helper(FileProcessor fp) {
        this.fp = fp;
        replicaTree0 = new TreeHelper();
        replicaTree1 = new TreeHelper();
        replicaTree2 = new TreeHelper();
    }

    public void process() throws IOException {
        Integer bNumber;
        String str = fp.poll();

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
              //  System.out.println("helper " + replicaNode0.getBNumber());
            // if(replicaNode2 != null)
            // System.out.println("helper "+ replicaNode2);

            replicaNode0.registerObservers(replicaNode1, replicaNode2);
            replicaTree0.add(replicaNode0);

            replicaNode1.registerObservers(replicaNode0, replicaNode2);
            replicaTree1.add(replicaNode1);

            replicaNode2.registerObservers(replicaNode0, replicaNode1);
            replicaTree2.add(replicaNode2);

            str = fp.poll();
        }
        String tree0 = replicaTree0.printInorder();
        String tree1 = replicaTree0.printInorder();
        String tree2 = replicaTree0.printInorder();

        System.out.println(" " + tree0 + "\n" + " " + tree1 + "\n" + " " + tree2 + "\n");
    }
}