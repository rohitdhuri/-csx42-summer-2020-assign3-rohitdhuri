package studentskills.driver;

import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;

import studentskills.util.FileDisplayInterface;
import studentskills.util.FileProcessor;
import studentskills.util.Parser;
import studentskills.util.Results;
import studentskills.util.exception.EmptyFileException;
import studentskills.util.exception.InvalidInputFormat;

public class Driver {
    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;

    public static void main(String[] args) throws Exception {

        /*
         * As the build.xml specifies the arguments as input,output or metrics, in case
         * the argument value is not given java takes the default value specified in
         * build.xml.
         */
        if ((args.length != 7) || (args[0].equals("${input}")) || (args[1].equals("${modify}"))
                || (args[2].equals("${out1}")) || (args[3].equals("${out2}")) || (args[4].equals("${out3}"))
                || (args[5].equals("${error}")) || (args[6].equals("${debug}"))) {
            System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
                    REQUIRED_NUMBER_OF_CMDLINE_ARGS);
            System.exit(0);
        }

        FileProcessor fpInput = new FileProcessor(args[0]);
        FileProcessor fpModify = new FileProcessor(args[1]);

        Results results_0 = new Results(args[2]);
        Results results_1 = new Results(args[3]);
        Results results_2 = new Results(args[4]);
        Results error = new Results(args[5]);
        Results debug = new Results(args[6]);

        try {
            Parser ip = new Parser(fpInput, fpModify, results_0, results_1, results_2, error, debug);
            ip.processInput();
            ip.processModify();

            FileDisplayInterface fdi_0 = results_0;
            FileDisplayInterface fdi_1 = results_1;
            FileDisplayInterface fdi_2 = results_2;
            FileDisplayInterface fdi_3 = error;

            fdi_0.writeToFile();
            fdi_1.writeToFile();
            fdi_2.writeToFile();
            fdi_3.writeToFile();
        } catch (NumberFormatException | EmptyFileException | InvalidPathException | SecurityException
                | FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidInputFormat e) {
            e.getMessage();
        }

    }
}