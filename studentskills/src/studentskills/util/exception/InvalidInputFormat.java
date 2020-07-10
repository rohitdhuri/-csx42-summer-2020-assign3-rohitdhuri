package studentskills.util.exception;

public class InvalidInputFormat extends Exception{
    
    /**
     * InvalidInputFormat class
     * 
     * @author Rohit Mahendra Dhuri
     * 
     */    

        /**
         * InvalidInputFormat constructor calling the parent class
         * object
         * 
         * @param s - The error message
         */

        public InvalidInputFormat(String s){
            super(s);
        }


        @Override
        public String toString() {
            return "Class: InvalidInputFormat, Data Members: [ ]";
        }


}