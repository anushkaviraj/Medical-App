package com.industrialmaster.jewakamedicare.models;

public class Session {
    private int id;
    private String doctorName;
    private String specialityName;
    private String hospitalName;
    private String date;

    public Session(int id, String doctorName, String specialityName, String hospitalName, String date) {
        this.setId(id);
        this.setDoctorName(doctorName);
        this.setSpecialityName(specialityName);
        this.setHospitalName(hospitalName);
        this.setDate(date);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
