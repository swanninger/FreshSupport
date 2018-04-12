package net.freshservers.support.domain;

// Type of system credentials are requested for
public enum SystemType {
    Cloud, Tools, Email, Fresh_Schedules;

    @Override
    public String toString() {
        return super.toString().replaceAll("_", " ");
    }
}
