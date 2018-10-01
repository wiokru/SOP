import users.Admin;
import users.Doctor;
import users.Patient;
import users.User;
import users.Visit;
import utils.LabelsConstants;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;

public class SOP extends JFrame {

    protected static ArrayList<User> users = new ArrayList<>();
    protected static ArrayList<Visit> visits = new ArrayList<>();
    private Admin admin = Admin.getInstance();

    public static void main(String[] args) {
        EventQueue.invokeLater(SOP::new);
    }

    private SOP() {
        super(LabelsConstants.TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton logInButton = new JButton(LabelsConstants.LOGIN_CONFIRM);
        JLabel log_label = new JLabel(LabelsConstants.LOGIN_LABEL);
        JLabel pasw_label = new JLabel(LabelsConstants.PASSWORD_LABEL);
        JTextField login = new JTextField(33);
        JPasswordField password = new JPasswordField(35);

        log_label.setLabelFor(login);
        pasw_label.setLabelFor(password);

        login.isEditable();

        panel.add(log_label);
        panel.add(login);
        panel.add(pasw_label);
        panel.add(password);
        panel.add(logInButton);

        add(panel);
        setSize(500, 300);
        setVisible(true);

        logInButton.addActionListener(e -> {
            String temp_login = login.getText();
            char[] temp_password = password.getPassword();

            if (temp_login.equals(admin.getLogin())) {
                if (admin.checkPassword(temp_password)) {
                    //otworzenie panelu ADMINA
                    new AdminPanel();
                } else
                    //alert o złym haśle
                    JOptionPane.showMessageDialog(panel, LabelsConstants.PASSWORD_ERROR, null, JOptionPane.WARNING_MESSAGE);
            }
            else {
                boolean user_found = false;

                for (User u : users) {
                    if (temp_login.equals(u.getEmail())) {
                        user_found = true;
                        if (u.checkPassword(temp_password)) {
                            if (u instanceof Doctor) {
                                new DoctorPanel((Doctor) u);
                            } else //jesli jest pacjentem
                                new PatientPanel((Patient) u);
                        } else
                            //alert o złym haśle
                            JOptionPane.showMessageDialog(panel, LabelsConstants.PASSWORD_ERROR, null, JOptionPane.WARNING_MESSAGE);
                    }
                }
                if (!user_found)
                    JOptionPane.showMessageDialog(panel, LabelsConstants.USER_ERROR, null, JOptionPane.WARNING_MESSAGE);
            }
            password.setText("");
        });
    }
}