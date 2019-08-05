package edu.sam.aveng.domain.enumeration;

public enum Lang {

    ENGLISH("English", "eng"),
    RUSSIAN("Russian", "rus"),
    GERMAN("German", "ger");

    private String name;
    private String code;

    Lang(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static Lang fromName(String name) {
        switch (name) {
            case "English":
                return Lang.ENGLISH;
            case "Russian":
                return Lang.RUSSIAN;
            case "German":
                return Lang.GERMAN;
            default:
                // ToDo handle exception in a proper way and log it
                throw new IllegalArgumentException();
        }
    }

    public static Lang fromCode(String code) {
        switch (code) {
            case "eng":
                return Lang.ENGLISH;
            case "rus":
                return Lang.RUSSIAN;
            case "ger":
                return Lang.GERMAN;
            default:
                // ToDo handle exception in a proper way and log it
                throw new IllegalArgumentException();
        }
    }
}
