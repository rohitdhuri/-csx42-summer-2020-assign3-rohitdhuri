package studentskills.util.exception;

public class EmptyFileException extends Exception{

    /**
     * EmptyFileException class
     * 
     * @author Rohit Mahendra Dhuri
     * 
     */    

        /**
         * EmptyFileException constructor calling the parent class
         * object
         * 
         * @param s - The error message
         */

        public EmptyFileException(String s){
            super(s);
        }


        @Override
        public String toString() {
            return "Class: EmptyFileException, Data Members: [ ]";
        }


}