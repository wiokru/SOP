import users.Doctor;
import utils.LabelsConstants;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDoctorPanel extends JFrame {

    private static final int TEXT_FIELD_SIZE = 25;

    private Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");

    public AddDoctorPanel(){
        JPanel addUserForm = new JPanel();
        GroupLayout form = new GroupLayout(addUserForm);
        form.setAutoCreateGaps(true);
        addUserForm.setLayout(form);

        GroupLayout.Group hg1 = form.createParallelGroup(GroupLayout.Alignment.TRAILING);
        GroupLayout.Group hg2 = form.createParallelGroup();

        GroupLayout.Group vg_name = form.createParallelGroup();
        GroupLayout.Group vg_surname = form.createParallelGroup();
        GroupLayout.Group vg_spec = form.createParallelGroup();
        GroupLayout.Group vg_email = form.createParallelGroup();
        GroupLayout.Group vg_password = form.createParallelGroup();
        GroupLayout.Group vg_save = form.createParallelGroup();

        JLabel name_label = new JLabel (LabelsConstants.NAME);
        hg1.addComponent(name_label);
        vg_name.addComponent(name_label);

        JLabel surname_label = new JLabel (LabelsConstants.SURNAME);
        hg1.addComponent(surname_label);
        vg_surname.addComponent(surname_label);

        JLabel spec_label = new JLabel (LabelsConstants.SPECIALIZATION);
        hg1.addComponent(spec_label);
        vg_spec.addComponent(spec_label);

        JLabel email_label = new JLabel (LabelsConstants.EMAIL);
        hg1.addComponent(email_label);
        vg_email.addComponent(email_label);

        JLabel password_label = new JLabel (LabelsConstants.TEMPORARY_PASSWORD);
        hg1.addComponent(password_label);
        vg_password.addComponent(password_label);

        JTextField name = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(name);
        vg_name.addComponent(name);

        JTextField surname = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(surname);
        vg_surname.addComponent(surname);

        JTextField spec = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(spec);
        vg_spec.addComponent(spec);

        JTextField email = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(email);
        vg_email.addComponent(email);

        JTextField password = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(password);
        vg_password.addComponent(password);

        JButton save = new JButton("ZAPISZ");
        hg2.addComponent(save);
        vg_save.addComponent(save);

        name_label.setLabelFor(name);
        surname_label.setLabelFor(surname);
        spec_label.setLabelFor(spec);
        email_label.setLabelFor(email);
        password_label.setLabelFor(password);

        name.isEditable();
        surname.isEditable();
        spec.isEditable();
        email.isEditable();
        password.isEditable();

        GroupLayout.SequentialGroup hseq = form.createSequentialGroup();
        hseq.addGroup(hg1);
        hseq.addGroup(hg2);

        GroupLayout.SequentialGroup vseq = form.createSequentialGroup();
        vseq.addGroup(vg_name);
        vseq.addGroup(vg_surname);
        vseq.addGroup(vg_spec);
        vseq.addGroup(vg_email);
        vseq.addGroup(vg_password);
        vseq.addGroup(vg_save);

        form.setHorizontalGroup(hseq);
        form.setVerticalGroup(vseq);

        add(addUserForm);
        this.pack();
        setLocationByPlatform(true);
        setVisible(true);

        save.addActionListener(e -> {
            Matcher m = pattern.matcher(email.getText());

            if (m.matches()) {
                Doctor d = Doctor.builder().id(LocalDateTime.now().toString())
                        .name(name.getText())
                        .surname(surname.getText())
                        .specialization(spec.getText())
                        .email(email.getText())
                        .password(password.getText().toCharArray())
                        .build();
                SOP.users.add(d);
                JOptionPane.showMessageDialog(addUserForm, LabelsConstants.DOCTOR_ADD_SUCCESS);
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(addUserForm, LabelsConstants.WRONG_EMAIL_ERROR, null, JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

