package ua.site.prototype.entities;

public enum Month {
    Jan("January"),
    Feb("February"),
    Mar("March"),
    Apr("April"),
    May("May"),
    Jun("June"),
    Jul("July"),
    Aug("August"),
    Sep("September"),
    Oct("October"),
    Nov("November"),
    Dec("December");

    private String fullName;

    Month(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
