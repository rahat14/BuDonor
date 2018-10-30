package com.me.toma.budonor;

public class model_blood_req {
    String rname;
    String phone;
    String blodgroup;

    public model_blood_req() {
    }

    public model_blood_req(String rname, String phone, String blodgroup) {
        this.rname = rname;
        this.phone = phone;
        this.blodgroup = blodgroup;
    }

    public String getRname() {
        return rname;
    }

    public String getPhone() {
        return phone;
    }

    public String getBlodgroup() {
        return blodgroup;
    }
}
