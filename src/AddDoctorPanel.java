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

        GroupLayout.Group verticalGroupName = form.createParallelGroup();
        GroupLayout.Group verticalGroupSurname = form.createParallelGroup();
        GroupLayout.Group verticalGroupSpecialization = form.createParallelGroup();
        GroupLayout.Group verticalGroupEmail = form.createParallelGroup();
        GroupLayout.Group verticalGroupPassword = form.createParallelGroup();
        GroupLayout.Group verticalGroupSave = form.createParallelGroup();

        JLabel nameLabel = new JLabel (LabelsConstants.NAME);
        hg1.addComponent(nameLabel);
        verticalGroupName.addComponent(nameLabel);

        JLabel surnameLabel = new JLabel (LabelsConstants.SURNAME);
        hg1.addComponent(surnameLabel);
        verticalGroupSurname.addComponent(surnameLabel);

        JLabel specializationLabel = new JLabel (LabelsConstants.SPECIALIZATION);
        hg1.addComponent(specializationLabel);
        verticalGroupSpecialization.addComponent(specializationLabel);

        JLabel emailLabel = new JLabel (LabelsConstants.EMAIL);
        hg1.addComponent(emailLabel);
        verticalGroupEmail.addComponent(emailLabel);

        JLabel passwordLabel = new JLabel (LabelsConstants.TEMPORARY_PASSWORD);
        hg1.addComponent(passwordLabel);
        verticalGroupPassword.addComponent(passwordLabel);

        JTextField name = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(name);
        verticalGroupName.addComponent(name);

        JTextField surname = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(surname);
        verticalGroupSurname.addComponent(surname);

        JTextField specialization = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(specialization);
        verticalGroupSpecialization.addComponent(specialization);

        JTextField email = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(email);
        verticalGroupEmail.addComponent(email);

        JTextField password = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(password);
        verticalGroupPassword.addComponent(password);

        JButton save = new JButton(LabelsConstants.SAVE_BTN);
        hg2.addComponent(save);
        verticalGroupSave.addComponent(save);

        nameLabel.setLabelFor(name);
        surnameLabel.setLabelFor(surname);
        specializationLabel.setLabelFor(specialization);
        emailLabel.setLabelFor(email);
        passwordLabel.setLabelFor(password);

        name.isEditable();
        surname.isEditable();
        specialization.isEditable();
        email.isEditable();
        password.isEditable();

        GroupLayout.SequentialGroup hseq = form.createSequentialGroup();
        hseq.addGroup(hg1);
        hseq.addGroup(hg2);

        GroupLayout.SequentialGroup vseq = form.createSequentialGroup();
        vseq.addGroup(verticalGroupName);
        vseq.addGroup(verticalGroupSurname);
        vseq.addGroup(verticalGroupSpecialization);
        vseq.addGroup(verticalGroupEmail);
        vseq.addGroup(verticalGroupPassword);
        vseq.addGroup(verticalGroupSave);

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
                        .specialization(specialization.getText())
                        .email(email.getText())
                        .password(password.getText().toCharArray())
                        .build();
                SOP.users.add(d);
                JOptionPane.showMessageDialog(addUserForm, LabelsConstants.DOCTOR_ADD_SUCCESS);
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(addUserForm, LabelsConstants.WRONG_EMAIL_ERROR, null,
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

