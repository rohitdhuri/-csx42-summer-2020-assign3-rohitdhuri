package studentskills.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import studentskills.mytree.StudentRecord;
import studentskills.mytree.SubjectI;
import studentskills.mytree.TreeHelper;

public class Parser {
    private FileProcessor iFp, mFp;
    private TreeHelper replicaTree_0, replicaTree_1, replicaTree_2;
    private Results results_0, results_1, results_2;

    public Parser(FileProcessor iFp, FileProcessor mFp, Results results_0, Results results_1, Results results_2) {
        this.iFp = iFp;
        this.mFp = mFp;
        replicaTree_0 = new TreeHelper();
        replicaTree_1 = new TreeHelper();
        replicaTree_2 = new TreeHelper();
        this.results_0 = results_0;
        this.results_1 = results_1;
        this.results_2 = results_2;
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
            Set<String> skills = new HashSet<String>();
            Integer i = 0;
            while (i < (values.length - 4)) {
                if (values[i + 4] != null) {
                    skills.add(values[i + 4]);
                    i++;
                } else {
                    break;
                }
            }

            StudentRecord record = replicaTree_0.findRecord(bNumber);

            if (record == null) {
                StudentRecord replicaNode0 = new StudentRecord(bNumber, firstName, lastName, gpa, major, skills);
                StudentRecord replicaNode1 = replicaNode0.clone();
                StudentRecord replicaNode2 = replicaNode0.clone();

                replicaNode0.register(replicaNode1);
                replicaNode0.register(replicaNode2);
                replicaTree_0.add(replicaNode0);

                replicaNode1.register(replicaNode0);
                replicaNode1.register(replicaNode2);
                replicaTree_1.add(replicaNode1);

                replicaNode2.register(replicaNode0);
                replicaNode2.register(replicaNode1);
                replicaTree_2.add(replicaNode2);
            } else {
                record.recordChanged(bNumber, firstName, lastName, gpa, major, skills);
                record.notifyObservers(Operation.INSERT);
            }

            str = iFp.poll();
        }
    }

    public void processModify() throws IOException {

        String str = mFp.poll();

        while (str != null) {
            String treeNumber = str.split(",")[0];
            String bNumber = str.split(",")[1];
            String value = (str.split(",")[2]).split(":")[0];
            String replacement = (str.split(",")[2]).split(":")[1];

            if (treeNumber.equals(Replica.replicaTree_0.getConstantValue())) {
                SubjectI record = replicaTree_0.findRecord(Integer.parseInt(bNumber));
                if (record != null) {
                    record.recordChanged(value, replacement);
                    record.notifyObservers(Operation.MODIFY);
                }
            } else if (treeNumber.equals(Replica.replicaTree_1.getConstantValue())) {
                SubjectI record = replicaTree_1.findRecord(Integer.parseInt(bNumber));
                if (record != null) {
                    record.recordChanged(value, replacement);
                    record.notifyObservers(Operation.MODIFY);
                }
            } else if (treeNumber.equals(Replica.replicaTree_2.getConstantValue())) {
                SubjectI record = replicaTree_2.findRecord(Integer.parseInt(bNumber));
                if (record != null) {
                    record.recordChanged(value, replacement);
                    record.notifyObservers(Operation.MODIFY);
                }
            }

            str = mFp.poll();
        }

        String tree0 = replicaTree_0.printInorder();
        results_0.storeOutput(tree0);
        String tree1 = replicaTree_1.printInorder();
        results_1.storeOutput(tree1);
        String tree2 = replicaTree_2.printInorder();
        results_2.storeOutput(tree2);

    }

}