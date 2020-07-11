package studentskills.util;

public enum Replica {
    replicaTree_0("0"), replicaTree_1("1"), replicaTree_2("2");

    private final String stStr;

    /**
     * Replica constructor used for creating constant
     * 
     * @param - String value
     */
    Replica(String st) {
        stStr = st;
    }

    /**
     * This function returns the constant value of an enum
     * 
     */
    public String getConstantValue() {
        return stStr;
    }
}