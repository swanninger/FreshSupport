package net.freshservers.support.domain;

public enum SystemType {
    Cloud, Tools, Email, Fresh_Schedules;

    @Override
    public String toString() {
        return super.toString().replaceAll("_", " ");
    }
}
