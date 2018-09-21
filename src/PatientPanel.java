import users.Patient;
import users.Visit;
import utils.LabelsConstants;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatientPanel extends JFrame {

    public PatientPanel(Patient patient) {
        super(LabelsConstants.HELLO + patient.getName() + " " + patient.getSurname());

        JPanel main_menu = new JPanel();
        main_menu.setLayout(new GridLayout(6, 1));

        JButton logOutButton = new JButton(LabelsConstants.LOGOUT);
        JButton changeEmailButton = new JButton(LabelsConstants.CHANGE_EMAIL_BTN);
        JButton changePasswordButton = new JButton(LabelsConstants.CHANGE_PASSWORD_BTN);
        JButton addVisitButton = new JButton(LabelsConstants.ADD_VISIT);
        JButton cancelVisitButton = new JButton(LabelsConstants.CANCEL_VISIT);
        JButton showVisitsButton = new JButton(LabelsConstants.SHOW_VISITS);

        main_menu.add(logOutButton);
        main_menu.add(changeEmailButton);
        main_menu.add(changePasswordButton);
        main_menu.add(addVisitButton);
        main_menu.add(cancelVisitButton);
        main_menu.add(showVisitsButton);

        logOutButton.addActionListener(e -> dispose());
        changeEmailButton.addActionListener(e -> {
            Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
            String temp = JOptionPane.showInputDialog(main_menu, LabelsConstants.EMAIL_CHANGE);
            Matcher m = pattern.matcher(temp);
            if (m.matches())
                patient.setEmail(temp);
            else
                JOptionPane.showMessageDialog(main_menu, LabelsConstants.WRONG_EMAIL_ERROR, null, JOptionPane.ERROR_MESSAGE);
        });

        changePasswordButton.addActionListener(e -> {
            String temp = JOptionPane.showInputDialog(main_menu, LabelsConstants.PASSWORD_CHANGE);
            patient.setPassword(temp.toCharArray());
        });

        addVisitButton.addActionListener(e -> new AddVisitPanel(patient));
        cancelVisitButton.addActionListener(e -> {
            Visit[] my_vts = SOP.visits.stream()
                    .filter(visit -> visit.getPatient().equals(patient))
                    .toArray(Visit[]::new);
            JComboBox<Visit> vis = new JComboBox<>(my_vts);
            JOptionPane.showMessageDialog(main_menu, vis, LabelsConstants.CHOOSE_VISIT, JOptionPane.QUESTION_MESSAGE);
            Visit visit = (Visit) vis.getSelectedItem();

            if (visit != null)
                SOP.visits.remove(visit);
            else
                JOptionPane.showMessageDialog(main_menu, LabelsConstants.NO_CHOICE_ERROR, null, JOptionPane.ERROR_MESSAGE);
        });

        showVisitsButton.addActionListener(e -> {
            DefaultListModel<Visit> my_vts = new DefaultListModel<>();
            SOP.visits.stream()
                    .filter(visit -> visit.getPatient().equals(patient))
                    .forEach(my_vts::addElement);
            JList<Visit> visits = new JList<>(my_vts);
            JScrollPane visits_scroll = new JScrollPane(visits);
            JPanel panel = new JPanel();
            panel.add(visits_scroll);
            visits_scroll.getViewport().add(visits);
            JOptionPane.showMessageDialog(main_menu, visits_scroll, LabelsConstants.YOUR_VISITS, JOptionPane.PLAIN_MESSAGE);
        });

        add(main_menu);
        this.pack();
        this.setSize(800, 600);
        setVisible(true);
    }
}
