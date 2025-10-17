package ru.mephi.malskiy.enums;

public enum Coordinates {
    MSK("lat=55.75&lon=37.62"),
    SPB("lat=59.57&lon=30.19");
    private final String location;

    Coordinates(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

}
