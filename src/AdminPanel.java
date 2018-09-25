import utils.LabelsConstants;
import users.Doctor;
import users.Patient;
import users.Visit;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class AdminPanel extends JFrame {

    private JPanel mainMenu;

    public AdminPanel() {
        super(LabelsConstants.ADMIN_TITLE);

        mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(12, 1));

        JButton logOutButton = new JButton(LabelsConstants.LOGOUT);
        JButton addDoctorButton = new JButton(LabelsConstants.ADD_DOCTOR);
        JButton addPatientButton = new JButton(LabelsConstants.ADD_PATIENT);
        JButton addVisitButton = new JButton(LabelsConstants.ADD_VISIT);
        JButton modyfiPatientButton = new JButton(LabelsConstants.MODIFY_PATIENT);
        JButton modifyVisitButton = new JButton(LabelsConstants.MODIFY_VISIT);
        JButton cancelVisitButton = new JButton(LabelsConstants.CANCEL_VISIT);
        JButton deleteDoctorButton = new JButton(LabelsConstants.DELETE_DOCTOR);
        JButton deletePatientButton = new JButton(LabelsConstants.DELETE_PATIENT);
        JButton showVisitsButton = new JButton(LabelsConstants.SHOW_VISITS);
        JButton showDoctorsButton = new JButton(LabelsConstants.SHOW_DOCTORS);
        JButton showPatientsButton = new JButton(LabelsConstants.SHOW_PATIENTS);

        mainMenu.add(logOutButton);
        mainMenu.add(addDoctorButton);
        mainMenu.add(addPatientButton);
        mainMenu.add(addVisitButton);
        mainMenu.add(modyfiPatientButton);
        mainMenu.add(modifyVisitButton);
        mainMenu.add(cancelVisitButton);
        mainMenu.add(deleteDoctorButton);
        mainMenu.add(deletePatientButton);
        mainMenu.add(showVisitsButton);
        mainMenu.add(showDoctorsButton);
        mainMenu.add(showPatientsButton);

        logOutButton.addActionListener(e -> dispose());
        addDoctorButton.addActionListener(e -> new AddDoctorPanel());
        addPatientButton.addActionListener(e -> new AddPatientPanel());
        addVisitButton.addActionListener(e -> new AddVisitPanel());

        modyfiPatientButton.addActionListener(e -> {
            ArrayList<Patient> pats = new ArrayList<>();
            SOP.users.stream()
                    .filter(u -> u instanceof Patient)
                    .forEach(u -> pats.add((Patient) u));
            JComboBox<Patient> patients = new JComboBox<>(pats.toArray(new Patient[pats.size()]));
            JOptionPane.showMessageDialog(mainMenu, patients, LabelsConstants.CHOOSE_PATIENT, JOptionPane.QUESTION_MESSAGE);

            Patient patient = (Patient) patients.getSelectedItem();

            if (patient != null)
                new AddPatientPanel(patient);
            else
                JOptionPane.showMessageDialog(mainMenu, LabelsConstants.NO_CHOICE_ERROR, null, JOptionPane.ERROR_MESSAGE);

        });

        modifyVisitButton.addActionListener(e -> {
            Visit[] vts = SOP.visits.toArray(new Visit[SOP.visits.size()]);
            JComboBox<Visit> visits = new JComboBox<>(vts);
            JOptionPane.showMessageDialog(mainMenu, visits, LabelsConstants.CHOOSE_VISIT, JOptionPane.QUESTION_MESSAGE);

            Visit v = (Visit) visits.getSelectedItem();
            if (v != null)
                new AddVisitPanel(v);
            else
                JOptionPane.showMessageDialog(mainMenu, LabelsConstants.NO_CHOICE_ERROR, null, JOptionPane.ERROR_MESSAGE);
        });

        cancelVisitButton.addActionListener(e -> {
            Visit[] vts = SOP.visits.toArray(new Visit[SOP.visits.size()]);
            JComboBox<Visit> vis = new JComboBox<>(vts);

            if (JOptionPane.showConfirmDialog(mainMenu, vis, LabelsConstants.CHOOSE_VISIT,
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Visit visit = (Visit) vis.getSelectedItem();
                if (visit != null)
                    SOP.visits.remove(visit);
                else
                    JOptionPane.showMessageDialog(mainMenu, LabelsConstants.NO_CHOICE_ERROR, null,
                            JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteDoctorButton.addActionListener(e -> {
            Doctor[] doc = SOP.users.stream()
                    .filter(user -> user instanceof Doctor)
                    .map(user -> (Doctor) user)
                    .toArray(Doctor[]::new);
            JComboBox<Doctor> doctors = new JComboBox<>(doc);

            if (JOptionPane.showConfirmDialog(mainMenu, doctors, LabelsConstants.CHOOSE_DOCTOR,
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Doctor doctor = (Doctor) doctors.getSelectedItem();
                if (doctor != null)
                    SOP.users.remove(doctor);
                else
                    JOptionPane.showMessageDialog(mainMenu, LabelsConstants.NO_CHOICE_ERROR, null,
                            JOptionPane.ERROR_MESSAGE);
            }
        });

        deletePatientButton.addActionListener(e -> {
            Patient[] pats = SOP.users.stream()
                    .filter(user -> user instanceof Patient)
                    .map(user -> (Patient) user)
                    .toArray(Patient[]::new);
            JComboBox<Patient> patients = new JComboBox<>(pats);

            if (JOptionPane.showConfirmDialog(mainMenu, patients, LabelsConstants.CHOOSE_PATIENT,
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Patient patient = (Patient) patients.getSelectedItem();
                if (patient != null) {
                    SOP.users.remove(patient);
                    SOP.visits.stream().filter(visit -> visit.getPatient().equals(patients.getSelectedItem()))
                            .forEach(visit -> SOP.visits.remove(visit));
                } else
                    JOptionPane.showMessageDialog(mainMenu, LabelsConstants.NO_CHOICE_ERROR, null,
                            JOptionPane.ERROR_MESSAGE);
            }
        });

        showVisitsButton.addActionListener(e -> new ShowItemPanel(Visit.class));
        showPatientsButton.addActionListener(e -> new ShowItemPanel(Patient.class));
        showDoctorsButton.addActionListener(e -> new ShowItemPanel(Doctor.class));

        add(mainMenu);
        this.pack();
        this.setSize(800, 600);
        setVisible(true);
    }

}
