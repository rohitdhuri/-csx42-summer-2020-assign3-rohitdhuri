package studentskills.driver;

import studentskills.util.FileProcessor;
import studentskills.util.InputParser;

public class Driver {
    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

    public static void main(String[] args) throws Exception {

        /*
         * As the build.xml specifies the arguments as input,output or metrics, in case
         * the argument value is not given java takes the default value specified in
         * build.xml.
         */
        if ((args.length != 5) || (args[0].equals("${input}")) || (args[1].equals("${modify}")) || (args[2].equals("${out1}")) || (args[3].equals("${out2}")) || (args[4].equals("${out3}"))) {
            System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
                    REQUIRED_NUMBER_OF_CMDLINE_ARGS);
            System.exit(0);
        }

        FileProcessor fpInput = new FileProcessor(args[0]);
        FileProcessor fpModify = new FileProcessor(args[1]);


        InputParser ip = new InputParser(fpInput);

        ip.process();
    }
}