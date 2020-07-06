package studentskills.util;

import java.io.IOException;
import java.util.ArrayList;

public class InputParser {
    private FileProcessor fp;

    public InputParser(FileProcessor fp) {
        this.fp = fp;
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
                if (values[i+4] != null) {
                    skills.add(values[i+4]);
                    i++;
                } else {
                    break;
                }
            }


            System.out.println(bNumber + firstName + lastName + gpa + major+"  "+skills);

            str = fp.poll();
        }
    }
}