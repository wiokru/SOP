package users;

import java.util.Date;

public class Visit {

    private Date date;
    private Doctor doctor;
    private Patient patient;

    public Visit(Date date, Doctor doctor, Patient patient){
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Wizyta {" +
                "DATA=" + date +
                ", DOKTOR=" + doctor +
                ", PACJENT=" + patient +
                '}';
    }
}