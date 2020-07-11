package studentskills.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.HashSet;
import java.util.Set;

import studentskills.mytree.StudentRecord;
import studentskills.mytree.SubjectI;
import studentskills.mytree.TreeHelper;
import studentskills.util.exception.EmptyFileException;
import studentskills.util.exception.InvalidInputFormat;

/**
 * Parser class contains methods to process the input files
 */
public class Parser {
    private FileProcessor iFp, mFp;
    private TreeHelper replicaTree_0, replicaTree_1, replicaTree_2;
    private Results results_0, results_1, results_2, results_e;

    /**
     * Constructor inilaizes the results and fileprocessor objects. Also inilializes
     * three instances of treeHelper
     * 
     * @param iFp       - fileprocessor object for input file
     * @param mFp       - fileprocessor object for modify file
     * @param results_0 - results object
     * @param results_1 - results object
     * @param results_2 - results object
     * @param results_e - results object
     */
    public Parser(FileProcessor iFp, FileProcessor mFp, Results results_0, Results results_1, Results results_2,
            Results results_e) {
        MyLogger.writeMessage("Parser parameterized constructor", MyLogger.DebugLevel.CONSTRUCTOR);

        this.iFp = iFp;
        this.mFp = mFp;
        replicaTree_0 = new TreeHelper();
        replicaTree_1 = new TreeHelper();
        replicaTree_2 = new TreeHelper();
        this.results_0 = results_0;
        this.results_1 = results_1;
        this.results_2 = results_2;
        this.results_e = results_e;
    }

    /**
     * Parses throught the entire input file and processes each line at a time
     * 
     * @throws EmptyFileException
     * @throws NumberFormatException
     * @throws InvalidPathException
     * @throws SecurityException
     * @throws FileNotFoundException
     * @throws InvalidInputFormat
     */
    public void processInput() throws EmptyFileException, NumberFormatException, InvalidPathException,
            SecurityException, IOException, FileNotFoundException, InvalidInputFormat {
        String str = iFp.poll();
        if (str == null) {
            throw new EmptyFileException("Input file empty.");
        }

        /**
         * Loop until last line
         */
        while (str != null) {
            String tokens[] = str.split(":");
            Integer bNumber = Integer.parseInt(tokens[0]);
            if (String.valueOf(bNumber).length() != 4) {
                throw new InvalidInputFormat("Invalid bNumber");
            }

            str = tokens[1];
            String[] values = str.split(",");
            for (String s : values) {
                if (s.equals("") || s.contains(" "))
                    throw new InvalidInputFormat("Invalid field in input file");
            }
            MyLogger.writeMessage("Reading from input file", MyLogger.DebugLevel.PARSER);

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
            MyLogger.writeMessage("Calling findRecord", MyLogger.DebugLevel.PARSER);

            StudentRecord record = replicaTree_0.findRecord(bNumber);

            /**
             * If block is for when the node dosent already exist. Else block is when a node
             * already exists
             */
            if (record == null) {
                StudentRecord replica_Node_0 = new StudentRecord(bNumber, firstName, lastName, gpa, major, skills);
                StudentRecord replica_Node_1 = replica_Node_0.clone();
                StudentRecord replica_Node_2 = replica_Node_0.clone();

                MyLogger.writeMessage("Calling register to register nodes", MyLogger.DebugLevel.PARSER);
                replica_Node_0.register(replica_Node_1);
                MyLogger.writeMessage("Calling register to register nodes", MyLogger.DebugLevel.PARSER);
                replica_Node_0.register(replica_Node_2);
                MyLogger.writeMessage("Calling add to add node to tree", MyLogger.DebugLevel.PARSER);
                replicaTree_0.add(replica_Node_0);

                MyLogger.writeMessage("Calling register to register nodes", MyLogger.DebugLevel.PARSER);
                replica_Node_1.register(replica_Node_0);
                MyLogger.writeMessage("Calling register to register nodes", MyLogger.DebugLevel.PARSER);
                replica_Node_1.register(replica_Node_2);
                MyLogger.writeMessage("Calling add to add node to tree", MyLogger.DebugLevel.PARSER);
                replicaTree_1.add(replica_Node_1);

                MyLogger.writeMessage("Calling register to register nodes", MyLogger.DebugLevel.PARSER);
                replica_Node_2.register(replica_Node_0);
                MyLogger.writeMessage("Calling register to register nodes", MyLogger.DebugLevel.PARSER);
                replica_Node_2.register(replica_Node_1);
                MyLogger.writeMessage("Calling add to add node to tree", MyLogger.DebugLevel.PARSER);
                replicaTree_2.add(replica_Node_2);
            } else {
                MyLogger.writeMessage("Calling record changed for input file", MyLogger.DebugLevel.PARSER);
                record.recordChanged(bNumber, firstName, lastName, gpa, major, skills);
                MyLogger.writeMessage("Calling notify observers", MyLogger.DebugLevel.PARSER);
                record.notifyObservers(Operation.INSERT);
            }
            str = iFp.poll();
        }
        MyLogger.writeMessage("Calling close from FileProcessor", MyLogger.DebugLevel.PARSER);
        iFp.close();
    }

    /**
     * Parses throught the entire input file and processes each line at a time
     * 
     * @throws EmptyFileException
     * @throws NumberFormatException
     */
    public void processModify() throws EmptyFileException, IOException, NumberFormatException {
        String str = mFp.poll();
        if (str == null) {
            throw new EmptyFileException("Modify file empty.");
        }

        /**
         * Loop until last line
         */
        while (str != null) {
            try {
                for (String s : str.split(",")) {
                    if (s.contains(" "))
                        throw new InvalidInputFormat("Input contains spaces");
                }

                MyLogger.writeMessage("Reading from modify file", MyLogger.DebugLevel.PARSER);
                String treeNumber = str.split(",")[0];
                Integer bNumber = Integer.parseInt(str.split(",")[1]);
                String value = (str.split(",")[2]).split(":")[0];
                String replacement = (str.split(",")[2]).split(":")[1];

                if (treeNumber.equals(Replica.replicaTree_0.getConstantValue())) {
                    SubjectI record = replicaTree_0.findRecord(bNumber);
                    if (record != null) {
                        MyLogger.writeMessage("Calling recordChanged", MyLogger.DebugLevel.PARSER);
                        record.recordChanged(value, replacement);
                        MyLogger.writeMessage("Calling notifyObservers", MyLogger.DebugLevel.PARSER);
                        record.notifyObservers(Operation.MODIFY);
                    }
                } else if (treeNumber.equals(Replica.replicaTree_1.getConstantValue())) {
                    SubjectI record = replicaTree_1.findRecord(bNumber);
                    if (record != null) {
                        MyLogger.writeMessage("Calling recordChanged", MyLogger.DebugLevel.PARSER);
                        record.recordChanged(value, replacement);
                        MyLogger.writeMessage("Calling notifyObservers", MyLogger.DebugLevel.PARSER);
                        record.notifyObservers(Operation.MODIFY);
                    }
                } else if (treeNumber.equals(Replica.replicaTree_2.getConstantValue())) {
                    SubjectI record = replicaTree_2.findRecord(bNumber);
                    if (record != null) {
                        MyLogger.writeMessage("Calling recordChanged", MyLogger.DebugLevel.PARSER);
                        record.recordChanged(value, replacement);
                        MyLogger.writeMessage("Calling notifyObservers", MyLogger.DebugLevel.PARSER);
                        record.notifyObservers(Operation.MODIFY);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                results_e.storeOutput("Input field empty in modify file.\n");
            } catch (InvalidInputFormat e) {
                results_e.storeOutput(e.getMessage()+"\n");
            }

            str = mFp.poll();

        }
        MyLogger.writeMessage("Calling storeOutput", MyLogger.DebugLevel.PARSER);
        results_0.storeOutput(replicaTree_0.printNodes());
        MyLogger.writeMessage("Calling storeOutput", MyLogger.DebugLevel.PARSER);
        results_1.storeOutput(replicaTree_1.printNodes());
        MyLogger.writeMessage("Calling storeOutput", MyLogger.DebugLevel.PARSER);
        results_2.storeOutput(replicaTree_2.printNodes());

        MyLogger.writeMessage("Calling close from FileProcessor", MyLogger.DebugLevel.PARSER);
        mFp.close();
    }

    @Override
    public String toString() {
        return "Class: Parser, Data Members: [ iFp= "+iFp+" mFp= "+ mFp+" results_0= "+results_0+" results_1= "+results_1+" results_2= "+results_2+" results_e= "+results_e+" replicaTree_0= "+replicaTree_0+" replicaTree_1= "+replicaTree_1+" replicaTree_2= "+ replicaTree_2;
    }
}