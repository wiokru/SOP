import users.Doctor;
import users.Visit;
import utils.LabelsConstants;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoctorPanel extends JFrame {

    public DoctorPanel(Doctor doctor) {
        super(LabelsConstants.HELLO + doctor.getName() + " " + doctor.getSurname());

        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(5, 1));

        JButton logOutButton = new JButton(LabelsConstants.LOGOUT);
        JButton changeEmailButton = new JButton(LabelsConstants.CHANGE_EMAIL_BTN);
        JButton changePasswordButton = new JButton(LabelsConstants.CHANGE_PASSWORD_BTN);
        JButton cancelVisitButton = new JButton(LabelsConstants.CANCEL_VISIT);
        JButton showVisitsButton = new JButton(LabelsConstants.SHOW_VISITS);

        mainMenu.add(logOutButton);
        mainMenu.add(changeEmailButton);
        mainMenu.add(changePasswordButton);
        mainMenu.add(cancelVisitButton);
        mainMenu.add(showVisitsButton);

        logOutButton.addActionListener(e -> dispose());

        changeEmailButton.addActionListener(e -> {
            Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
            String temp = JOptionPane.showInputDialog(mainMenu, LabelsConstants.EMAIL_CHANGE);
            Matcher matcher = pattern.matcher(temp);
            if (matcher.matches())
                doctor.setEmail(temp);
            else
                JOptionPane.showMessageDialog(mainMenu, LabelsConstants.WRONG_EMAIL_ERROR, null, JOptionPane.ERROR_MESSAGE);
        });

        changePasswordButton.addActionListener(e -> {
            String temp = JOptionPane.showInputDialog(mainMenu, LabelsConstants.PASSWORD_CHANGE);
            doctor.setPassword(temp.toCharArray());
        });

        cancelVisitButton.addActionListener(e -> {
            Visit[] my_vts = SOP.visits.stream()
                    .filter(visit -> visit.getDoctor().equals(doctor))
                    .toArray(Visit[]::new);
            JComboBox<Visit> vis = new JComboBox<>(my_vts);
            JOptionPane.showMessageDialog(mainMenu, vis, LabelsConstants.CHOOSE_VISIT, JOptionPane.QUESTION_MESSAGE);
            Visit visit = (Visit) vis.getSelectedItem();

            if (visit != null)
                SOP.visits.remove(visit);
            else
                JOptionPane.showMessageDialog(mainMenu, LabelsConstants.NO_CHOICE_ERROR, null, JOptionPane.ERROR_MESSAGE);
        });

        showVisitsButton.addActionListener(e -> {
            DefaultListModel<Visit> my_vts = new DefaultListModel<>();
            SOP.visits.stream()
                    .filter(visit -> visit.getDoctor().equals(doctor))
                    .forEach(my_vts::addElement);
            JList<Visit> visits = new JList<>(my_vts);
            JScrollPane visits_scroll = new JScrollPane(visits);
            JPanel panel = new JPanel();
            panel.add(visits_scroll);
            visits_scroll.getViewport().add(visits);
            JOptionPane.showMessageDialog(mainMenu, visits_scroll, LabelsConstants.YOUR_VISITS, JOptionPane.PLAIN_MESSAGE);
        });

        add(mainMenu);
        this.pack();
        this.setSize(800, 600);
        setVisible(true);
    }
}
