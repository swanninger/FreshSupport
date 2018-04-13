package net.freshservers.support.domain;

// Type of system credentials are requested for
public enum SystemType {
    Cloud, Tools, Email, Fresh_Schedules;

    @Override
    public String toString() {
        switch(this){
            case Cloud: return "Cloud/Remote Desktop ($50 A Month)";
            case Tools: return "FreshTechnology Dashboard/Tools";
            case Email: return "Email";
            case Fresh_Schedules: return "FreshSchedules (Employee must be entered into EMP MAINT to get access to FreshSchedules)";
            default: return super.toString();
        }
    }
}
