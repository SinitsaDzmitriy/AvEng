package edu.sam.aveng.base.model.enumeration;

public enum Lang {

    ENGLISH("English", "en"),
    RUSSIAN("Russian", "ru"),
    GERMAN("German", "de");

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
                return null;
        }
    }

    public static Lang fromCode(String code) {
        switch (code) {
            case "en":
                return Lang.ENGLISH;
            case "ru":
                return Lang.RUSSIAN;
            case "de":
                return Lang.GERMAN;
            default:
                return null;
        }
    }
}
