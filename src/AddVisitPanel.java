import users.Doctor;
import users.Patient;
import users.User;
import users.Visit;
import utils.LabelsConstants;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//TODO: replace java.util.Date with Java 8 date API



public class AddVisitPanel extends JFrame{

    private static final int FIELD_SIZE = 25;
    private static final int POSSIBLE_HOURS_COUNT = 20;
    private static final int START_HOUR = 8;
    private static final String FULL_HOUR_SUFFIX = ".00";
    private static final String HALF_HOUR_SUFFIX = ".30";


    //KONSTRUKTOR DO DODANIA WIZYTY
    public AddVisitPanel(){
        JPanel addVisitForm = new JPanel();
        GroupLayout form = new GroupLayout(addVisitForm);
        form.setAutoCreateGaps(true);
        addVisitForm.setLayout(form);

        GroupLayout.Group horizontalGroup1 = form.createParallelGroup(GroupLayout.Alignment.TRAILING);
        GroupLayout.Group horizontalGroup2 = form.createParallelGroup();

        GroupLayout.Group verticalGroupDate = form.createParallelGroup();
        GroupLayout.Group verticalGroupHour = form.createParallelGroup();
        GroupLayout.Group verticalGroupDoctor = form.createParallelGroup();
        GroupLayout.Group verticalGroupPatient = form.createParallelGroup();
        GroupLayout.Group verticalGroupSave = form.createParallelGroup();

        JLabel dateLabel = new JLabel (LabelsConstants.DATE);
        horizontalGroup1.addComponent(dateLabel);
        verticalGroupDate.addComponent(dateLabel);

        JLabel hourLabel = new JLabel (LabelsConstants.HOUR);
        horizontalGroup1.addComponent(hourLabel);
        verticalGroupHour.addComponent(hourLabel);

        JLabel doctorLabel = new JLabel (LabelsConstants.DOCTOR);
        horizontalGroup1.addComponent(doctorLabel);
        verticalGroupDoctor.addComponent(doctorLabel);

        JLabel patientLabel = new JLabel(LabelsConstants.PATIENT);
        horizontalGroup1.addComponent(patientLabel);
        verticalGroupPatient.addComponent(patientLabel);

        JTextField date = new JTextField(LabelsConstants.DATE_FORMAT, FIELD_SIZE);
        horizontalGroup2.addComponent(date);
        verticalGroupDate.addComponent(date);

        String [] hrs = new String[POSSIBLE_HOURS_COUNT];
        int hr = START_HOUR;
        for (int i = 0; i < POSSIBLE_HOURS_COUNT; i++) {
            if (i % 2 == 0)
                hrs[i] = hr + FULL_HOUR_SUFFIX;
            else{
                hrs[i] = hr + HALF_HOUR_SUFFIX;
                hr++;
            }
        }

        JComboBox<String> hours = new JComboBox<>(hrs);
        horizontalGroup2.addComponent(hours);
        verticalGroupHour.addComponent(hours);

        Doctor [] doc = SOP.users.stream()
                .filter(user -> user instanceof Doctor)
                .map(user -> (Doctor) user)
                .toArray(Doctor[]::new);

        JComboBox<Doctor> doctors = new JComboBox<>(doc);
        horizontalGroup2.addComponent(doctors);
        verticalGroupDoctor.addComponent(doctors);

        Patient [] pat = SOP.users.stream()
                .filter(user -> user instanceof Patient)
                .map(user -> (Patient) user)
                .toArray(Patient[]::new);

        JComboBox<Patient> patients = new JComboBox<>(pat);
        horizontalGroup2.addComponent(patients);
        verticalGroupPatient.addComponent(patients);

        JButton save = new JButton(LabelsConstants.SAVE_BTN);
        horizontalGroup2.addComponent(save);
        verticalGroupSave.addComponent(save);

        dateLabel.setLabelFor(date);
        hourLabel.setLabelFor(hours);
        doctorLabel.setLabelFor(doctors);
        patientLabel.setLabelFor(patients);

        date.isEditable();

        GroupLayout.SequentialGroup hseq = form.createSequentialGroup();
        hseq.addGroup(horizontalGroup1);
        hseq.addGroup(horizontalGroup2);

        GroupLayout.SequentialGroup vseq = form.createSequentialGroup();
        vseq.addGroup(verticalGroupDate);
        vseq.addGroup(verticalGroupHour);
        vseq.addGroup(verticalGroupDoctor);
        vseq.addGroup(verticalGroupPatient);
        vseq.addGroup(verticalGroupSave);

        form.setHorizontalGroup(hseq);
        form.setVerticalGroup(vseq);

        save.addActionListener(e -> {
            String d = date.getText();
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date visitDate;

            try {
                visitDate = format.parse(d);
                String hour = (String) hours.getSelectedItem();
                String[] visit_hour = hour.split("\\.");
                visitDate.setHours(Integer.parseInt(visit_hour[0]));
                visitDate.setMinutes(Integer.parseInt(visit_hour[1]));

                Long possibleTermin = SOP.visits.stream()
                        .filter(visit -> visit.getDate().equals(visitDate))
                        .filter(visit -> visit.getDoctor().equals(doctors.getSelectedItem()))
                        .count();

                if (possibleTermin == 0)
                    JOptionPane.showMessageDialog(addVisitForm, LabelsConstants.ADD_VISIT_ERROR, null, JOptionPane.WARNING_MESSAGE);
                else {
                    Visit visit = new Visit(visitDate, (Doctor) doctors.getSelectedItem(), (Patient) patients.getSelectedItem());
                    SOP.visits.add(visit);
                    JOptionPane.showMessageDialog(addVisitForm, LabelsConstants.ADD_VISIT_SUCCESS);
                    dispose();
                }
            }
            catch (ParseException ex){
                JOptionPane.showMessageDialog(addVisitForm, LabelsConstants.DATE_ERROR,null, JOptionPane.ERROR_MESSAGE);
            }
        });

        add(addVisitForm);
        this.pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    //KONSTRUKTOR DO MODYFIKACJI
    public AddVisitPanel(Visit v){
        JPanel addVisitForm = new JPanel();
        GroupLayout form = new GroupLayout(addVisitForm);
        form.setAutoCreateGaps(true);
        addVisitForm.setLayout(form);

        GroupLayout.Group horizontalGroup1 = form.createParallelGroup(GroupLayout.Alignment.TRAILING);
        GroupLayout.Group horizontalGroup2 = form.createParallelGroup();

        GroupLayout.Group verticalGroupDate = form.createParallelGroup();
        GroupLayout.Group verticalGroupHour = form.createParallelGroup();
        GroupLayout.Group verticalGroupDoctor = form.createParallelGroup();
        GroupLayout.Group verticalGroupPatient = form.createParallelGroup();
        GroupLayout.Group verticalGroupSave = form.createParallelGroup();

        JLabel dateLabel = new JLabel (LabelsConstants.DATE);
        horizontalGroup1.addComponent(dateLabel);
        verticalGroupDate.addComponent(dateLabel);

        JLabel hourLabel = new JLabel (LabelsConstants.HOUR);
        horizontalGroup1.addComponent(hourLabel);
        verticalGroupHour.addComponent(hourLabel);

        JLabel doctorLabel = new JLabel (LabelsConstants.DOCTOR);
        horizontalGroup1.addComponent(doctorLabel);
        verticalGroupDoctor.addComponent(doctorLabel);

        JLabel patientLabel = new JLabel(LabelsConstants.PATIENT);
        horizontalGroup1.addComponent(patientLabel);
        verticalGroupPatient.addComponent(patientLabel);

        JTextField date = new JTextField(LabelsConstants.DATE_FORMAT, FIELD_SIZE);
        String temp = String.valueOf(v.getDate().getDay())+"."+String.valueOf(v.getDate().getMonth())+"."+String.valueOf(v.getDate().getYear());
        date.setText(temp);
        horizontalGroup2.addComponent(date);
        verticalGroupDate.addComponent(date);

        String [] hrs = new String[POSSIBLE_HOURS_COUNT];
        String tempHour = String.valueOf(v.getDate().getHours()) + "." + String.valueOf(v.getDate().getMinutes());
        Integer startHour = START_HOUR;
        int ind=0;
        for(int i=0; i<POSSIBLE_HOURS_COUNT; i++){
            if(i%2 ==0) {
                hrs[i] = startHour.toString() + FULL_HOUR_SUFFIX;
            }
            else{
                hrs[i] = startHour.toString() + HALF_HOUR_SUFFIX;
                startHour++;
            }

            if (hrs[i].equals(tempHour))
                ind = i;
        }

        JComboBox<String> hours = new JComboBox<>(hrs);
        hours.setSelectedIndex(ind);
        horizontalGroup2.addComponent(hours);
        verticalGroupHour.addComponent(hours);

        Doctor[] doc = SOP.users.stream()
                .filter(user -> user instanceof Doctor)
                .map(user -> (Doctor) user)
                .toArray(Doctor[]::new);

        JComboBox<Doctor> doctors = new JComboBox<>(doc);
        for (User u : SOP.users){
            if(u.equals(v.getDoctor())) {
                doctors.setSelectedItem(u);
                break;
            }
        }
        horizontalGroup2.addComponent(doctors);
        verticalGroupDoctor.addComponent(doctors);

        Patient[] pat = SOP.users.stream()
                .filter(user -> user instanceof Patient)
                .map(user -> (Patient) user)
                .toArray(Patient[]::new);

        JComboBox<Patient> patients = new JComboBox<>(pat);
        for (User u : SOP.users){
            if(u.equals(v.getPatient())) {
                patients.setSelectedItem(u);
                break;
            }
        }
        horizontalGroup2.addComponent(patients);
        verticalGroupPatient.addComponent(patients);

        JButton save = new JButton(LabelsConstants.SAVE_BTN);
        horizontalGroup2.addComponent(save);
        verticalGroupSave.addComponent(save);

        dateLabel.setLabelFor(date);
        hourLabel.setLabelFor(hours);
        doctorLabel.setLabelFor(doctors);
        patientLabel.setLabelFor(patients);

        date.isEditable();

        GroupLayout.SequentialGroup hseq = form.createSequentialGroup();
        hseq.addGroup(horizontalGroup1);
        hseq.addGroup(horizontalGroup2);

        GroupLayout.SequentialGroup vseq = form.createSequentialGroup();
        vseq.addGroup(verticalGroupDate);
        vseq.addGroup(verticalGroupHour);
        vseq.addGroup(verticalGroupDoctor);
        vseq.addGroup(verticalGroupPatient);
        vseq.addGroup(verticalGroupSave);

        form.setHorizontalGroup(hseq);
        form.setVerticalGroup(vseq);

        save.addActionListener(e -> {
            String d = date.getText();
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            try {
                final Date visitDate = format.parse(d);
                String hr = (String) hours.getSelectedItem();
                String[] visit_hour = hr.split("\\.");
                visitDate.setHours(Integer.parseInt(visit_hour[0]));

                Long possibleTerms = SOP.visits.stream()
                        .filter(visit -> visit.getDate().equals(visitDate))
                        .filter(visit -> visit.getDoctor().equals(doctors.getSelectedItem()))
                        .count();

                if (possibleTerms > 0) {
                    v.setDate(visitDate);
                    v.setDoctor((Doctor) doctors.getSelectedItem());
                    v.setPatient((Patient) patients.getSelectedItem());

                    JOptionPane.showMessageDialog(addVisitForm, LabelsConstants.MODIFY_VISIT_SUCCESS);
                } else {
                    JOptionPane.showMessageDialog(addVisitForm, LabelsConstants.MODIFY_VISIT_ERROR, null, JOptionPane.WARNING_MESSAGE);
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(addVisitForm, LabelsConstants.DATE_ERROR, null, JOptionPane.ERROR_MESSAGE);
            }
        });

        add(addVisitForm);
        this.pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    //KONSTRUKTOR DO DODANIA WIZYTY PRZEZ PACJENTA
    public AddVisitPanel(Patient patient){
        JPanel addVisitForm = new JPanel();
        GroupLayout form = new GroupLayout(addVisitForm);
        form.setAutoCreateGaps(true);
        addVisitForm.setLayout(form);

        GroupLayout.Group horizontalGroup1 = form.createParallelGroup(GroupLayout.Alignment.TRAILING);
        GroupLayout.Group horizontalGroup2 = form.createParallelGroup();

        GroupLayout.Group verticalGroupDate = form.createParallelGroup();
        GroupLayout.Group verticalGroupHour = form.createParallelGroup();
        GroupLayout.Group verticalGroupDoctor = form.createParallelGroup();
        GroupLayout.Group verticalGroupSave = form.createParallelGroup();

        JLabel dateLabel = new JLabel(LabelsConstants.DATE);
        horizontalGroup1.addComponent(dateLabel);
        verticalGroupDate.addComponent(dateLabel);

        JLabel hourLabel = new JLabel(LabelsConstants.HOUR);
        horizontalGroup1.addComponent(hourLabel);
        verticalGroupHour.addComponent(hourLabel);

        JLabel doctorLabel = new JLabel(LabelsConstants.DOCTOR);
        horizontalGroup1.addComponent(doctorLabel);
        verticalGroupDoctor.addComponent(doctorLabel);

        JTextField date = new JTextField(LabelsConstants.DATE_FORMAT, FIELD_SIZE);
        horizontalGroup2.addComponent(date);
        verticalGroupDate.addComponent(date);

        String [] hrs = new String[20];
        Integer startHour = START_HOUR;
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                hrs[i] = startHour.toString() + ".00";
            }
            else{
                hrs[i] = startHour.toString() + ".30";
                startHour++;
            }
        }

        JComboBox<String> hours = new JComboBox<>(hrs);
        horizontalGroup2.addComponent(hours);
        verticalGroupHour.addComponent(hours);

        ArrayList<Doctor> docs = new ArrayList<>();
        for (User u : SOP.users){
            if(u instanceof Doctor)
                docs.add((Doctor) u);
        }
        Doctor[] doc = SOP.users.stream()
                .filter(user -> user instanceof Doctor)
                .map(user -> (Doctor) user)
                .toArray(Doctor[]::new);

        JComboBox<Doctor> doctors = new JComboBox<>(doc);
        horizontalGroup2.addComponent(doctors);
        verticalGroupDoctor.addComponent(doctors);

        JButton save = new JButton(LabelsConstants.SAVE_BTN);
        horizontalGroup2.addComponent(save);
        verticalGroupSave.addComponent(save);

        dateLabel.setLabelFor(date);
        hourLabel.setLabelFor(hours);
        doctorLabel.setLabelFor(doctors);

        date.isEditable();

        GroupLayout.SequentialGroup hseq = form.createSequentialGroup();
        hseq.addGroup(horizontalGroup1);
        hseq.addGroup(horizontalGroup2);

        GroupLayout.SequentialGroup vseq = form.createSequentialGroup();
        vseq.addGroup(verticalGroupDate);
        vseq.addGroup(verticalGroupHour);
        vseq.addGroup(verticalGroupDoctor);
        vseq.addGroup(verticalGroupSave);

        form.setHorizontalGroup(hseq);
        form.setVerticalGroup(vseq);

        save.addActionListener(e -> {
            String d = date.getText();
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            try {
                final Date visitDate = format.parse(d);
                String hr = (String) hours.getSelectedItem();
                String[] visitHour = hr.split("\\.");
                visitDate.setHours(Integer.parseInt(visitHour[0]));
                visitDate.setMinutes(Integer.parseInt(visitHour[1]));

                Long possibleTerms = SOP.visits.stream()
                        .filter(visit -> visit.getDate().equals(visitDate))
                        .filter(visit -> visit.getDoctor().equals(doctors.getSelectedItem()))
                        .count();

                if (possibleTerms == 0)
                    JOptionPane.showMessageDialog(addVisitForm, LabelsConstants.ADD_VISIT_ERROR, null, JOptionPane.WARNING_MESSAGE);
                else {
                    Visit visit = new Visit(visitDate, (Doctor) doctors.getSelectedItem(), patient);
                    SOP.visits.add(visit);
                    JOptionPane.showMessageDialog(addVisitForm, LabelsConstants.ADD_VISIT_SUCCESS);
                }

            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(addVisitForm, LabelsConstants.DATE_ERROR, null, JOptionPane.ERROR_MESSAGE);
            }
        });

        add(addVisitForm);
        this.pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

}
