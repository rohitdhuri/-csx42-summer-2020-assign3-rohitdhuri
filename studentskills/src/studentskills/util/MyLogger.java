package studentskills.util;

public class MyLogger {
    /** Enums for specifying debugLevel */
    public static enum DebugLevel {
        CONSTRUCTOR, FILE_PROCESSOR, DRIVER, STUDENT_RECORD, PARSER, NONE
    };

    private static DebugLevel debugLevel;

    public static void setDebugValue(int levelIn) {
        switch (levelIn) {
            case 5:
                debugLevel = DebugLevel.PARSER;
                break;
            case 4:
                debugLevel = DebugLevel.STUDENT_RECORD;
                break;
            case 3:
                debugLevel = DebugLevel.DRIVER;
                break;
            case 2:
                debugLevel = DebugLevel.CONSTRUCTOR;
                break;
            case 1:
                debugLevel = DebugLevel.FILE_PROCESSOR;
                break;
            default:
                debugLevel = DebugLevel.NONE;
                break;
        }
    }

    public static void setDebugValue(DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    public static void writeMessage(String message, DebugLevel levelIn) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}