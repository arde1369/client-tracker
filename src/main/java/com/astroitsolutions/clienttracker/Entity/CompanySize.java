package com.astroitsolutions.clienttracker.Entity;

public enum CompanySize {
    S("Start-up"),
    SM("Small"),
    M("Medium"),
    L("Large");
    
    private String description;

    private CompanySize(String desc){
        this.description = desc;
    }

    public String getValue(){
        return this.description;
    }
}
