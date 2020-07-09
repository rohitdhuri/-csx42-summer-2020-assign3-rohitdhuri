package studentskills.util;

public enum Operation {
    INSERT("INSERT"), MODIFY("MODIFY");

    private final String stStr;

    /**
     * StateName constructor used for creating constant
     * 
     * @param - String value
     */
    Operation(String st) {
        stStr = st;
    }

    /**
     * This function returns the constatnt value of an enum
     * 
     */
    public String getConstantValue() {
        return stStr;
    }
}