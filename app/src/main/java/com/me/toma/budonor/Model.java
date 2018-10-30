package com.me.toma.budonor;

public class Model {
    String namee ;
    String ph ;
    String blodgroup ;
    String identity_number ;


    public Model() {
    }


    public Model(String namee, String ph, String blodgroup, String identity_number) {
        this.namee = namee;
        this.ph = ph;
        this.blodgroup = blodgroup;
        this.identity_number = identity_number;
    }


    public String getNamee() {
        return namee;
    }

    public String getPh() {
        return ph;
    }

    public String getBlodgroup() {
        return blodgroup;
    }

    public String getIdentity_number() {
        return identity_number;
    }
}
