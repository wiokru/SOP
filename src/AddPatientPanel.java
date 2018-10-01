import users.Patient;
import utils.LabelsConstants;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddPatientPanel extends JFrame {

    private static final int TEXT_FIELD_SIZE = 25;
    private Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");

    //KONSTRUKTOR DO DODANIA PACJENTA
    public AddPatientPanel(){
        JPanel addUserForm = new JPanel();
        GroupLayout form = new GroupLayout(addUserForm);
        form.setAutoCreateGaps(true);
        addUserForm.setLayout(form);

        GroupLayout.Group hg1 = form.createParallelGroup(GroupLayout.Alignment.TRAILING);
        GroupLayout.Group hg2 = form.createParallelGroup();

        GroupLayout.Group verticalGroupName = form.createParallelGroup();
        GroupLayout.Group verticalGroupSurname = form.createParallelGroup();
        GroupLayout.Group verticalGroupAddress = form.createParallelGroup();
        GroupLayout.Group verticalGroupPhone = form.createParallelGroup();
        GroupLayout.Group verticalGroupEmail = form.createParallelGroup();
        GroupLayout.Group verticalGroupPassword = form.createParallelGroup();
        GroupLayout.Group verticalGroupSave = form.createParallelGroup();

        JLabel nameLabel = new JLabel (LabelsConstants.NAME);
        hg1.addComponent(nameLabel);
        verticalGroupName.addComponent(nameLabel);

        JLabel surnameLabel = new JLabel (LabelsConstants.SURNAME);
        hg1.addComponent(surnameLabel);
        verticalGroupSurname.addComponent(surnameLabel);

        JLabel addressLabel = new JLabel(LabelsConstants.ADDRESS);
        hg1.addComponent(addressLabel);
        verticalGroupAddress.addComponent(addressLabel);

        JLabel phoneLabel = new JLabel(LabelsConstants.PHONE);
        hg1.addComponent(phoneLabel);
        verticalGroupPhone.addComponent(phoneLabel);

        JLabel emailLabel = new JLabel (LabelsConstants.EMAIL);
        hg1.addComponent(emailLabel);
        verticalGroupEmail.addComponent(emailLabel);

        JLabel passwordLabel = new JLabel(LabelsConstants.TEMPORARY_PASSWORD);
        hg1.addComponent(passwordLabel);
        verticalGroupPassword.addComponent(passwordLabel);

        JTextField name = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(name);
        verticalGroupName.addComponent(name);

        JTextField surname = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(surname);
        verticalGroupSurname.addComponent(surname);

        JTextField address = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(address);
        verticalGroupAddress.addComponent(address);

        JTextField phone = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(phone);
        verticalGroupPhone.addComponent(phone);

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
        addressLabel.setLabelFor(address);
        phoneLabel.setLabelFor(phone);
        emailLabel.setLabelFor(email);
        passwordLabel.setLabelFor(password);

        name.isEditable();
        surname.isEditable();
        address.isEditable();
        phone.isEditable();
        email.isEditable();
        password.isEditable();

        GroupLayout.SequentialGroup hseq = form.createSequentialGroup();
        hseq.addGroup(hg1);
        hseq.addGroup(hg2);

        GroupLayout.SequentialGroup vseq = form.createSequentialGroup();
        vseq.addGroup(verticalGroupName);
        vseq.addGroup(verticalGroupSurname);
        vseq.addGroup(verticalGroupAddress);
        vseq.addGroup(verticalGroupPhone);
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
                Patient p = Patient.builder().id(LocalDateTime.now().toString())
                        .name(name.getText())
                        .surname(surname.getText())
                        .email(email.getText())
                        .address(address.getText())
                        .phone(phone.getText())
                        .password(password.getText().toCharArray())
                        .build();

                SOP.users.add(p);
                JOptionPane.showMessageDialog(addUserForm, LabelsConstants.PATIENT_ADD_SUCCESS);

                if (JOptionPane.showConfirmDialog(null, LabelsConstants.ADD_VISIT_QUESTION, null,
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    new AddVisitPanel();
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(addUserForm, LabelsConstants.WRONG_EMAIL_ERROR, null,
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    //KONSTRUKTOR DO MODYFIKACJI
    public AddPatientPanel(Patient p){
        JPanel addUserForm = new JPanel();
        GroupLayout form = new GroupLayout(addUserForm);
        form.setAutoCreateGaps(true);
        addUserForm.setLayout(form);

        GroupLayout.Group hg1 = form.createParallelGroup(GroupLayout.Alignment.TRAILING);
        GroupLayout.Group hg2 = form.createParallelGroup();

        GroupLayout.Group verticalGroupName = form.createParallelGroup();
        GroupLayout.Group verticalGroupSurname = form.createParallelGroup();
        GroupLayout.Group verticalGroupAddress = form.createParallelGroup();
        GroupLayout.Group verticalGroupPhone = form.createParallelGroup();
        GroupLayout.Group verticalGroupEmail = form.createParallelGroup();
        GroupLayout.Group verticalGroupSave = form.createParallelGroup();

        JLabel nameLabel = new JLabel (LabelsConstants.NAME);
        hg1.addComponent(nameLabel);
        verticalGroupName.addComponent(nameLabel);

        JLabel surnameLabel = new JLabel (LabelsConstants.SURNAME);
        hg1.addComponent(surnameLabel);
        verticalGroupSurname.addComponent(surnameLabel);

        JLabel addressLabel = new JLabel(LabelsConstants.ADDRESS);
        hg1.addComponent(addressLabel);
        verticalGroupAddress.addComponent(addressLabel);

        JLabel phoneLabel = new JLabel(LabelsConstants.PHONE);
        hg1.addComponent(phoneLabel);
        verticalGroupPhone.addComponent(phoneLabel);

        JLabel emailLabel = new JLabel (LabelsConstants.EMAIL);
        hg1.addComponent(emailLabel);
        verticalGroupEmail.addComponent(emailLabel);

        JTextField name = new JTextField(TEXT_FIELD_SIZE);
        name.setText(p.getName());
        hg2.addComponent(name);
        verticalGroupName.addComponent(name);

        JTextField surname = new JTextField(TEXT_FIELD_SIZE);
        surname.setText(p.getSurname());
        hg2.addComponent(surname);
        verticalGroupSurname.addComponent(surname);

        JTextField address = new JTextField(TEXT_FIELD_SIZE);
        address.setText(p.getAddress());
        hg2.addComponent(address);
        verticalGroupAddress.addComponent(address);

        JTextField phone = new JTextField(TEXT_FIELD_SIZE);
        phone.setText(p.getPhone());
        hg2.addComponent(phone);
        verticalGroupPhone.addComponent(phone);

        JTextField email = new JTextField(TEXT_FIELD_SIZE);
        email.setText(p.getEmail());
        hg2.addComponent(email);
        verticalGroupEmail.addComponent(email);

        JButton save = new JButton(LabelsConstants.SAVE_BTN);
        hg2.addComponent(save);
        verticalGroupSave.addComponent(save);

        nameLabel.setLabelFor(name);
        surnameLabel.setLabelFor(surname);
        addressLabel.setLabelFor(address);
        phoneLabel.setLabelFor(phone);
        emailLabel.setLabelFor(email);

        name.isEditable();
        surname.isEditable();
        address.isEditable();
        phone.isEditable();
        email.isEditable();

        GroupLayout.SequentialGroup hseq = form.createSequentialGroup();
        hseq.addGroup(hg1);
        hseq.addGroup(hg2);

        GroupLayout.SequentialGroup vseq = form.createSequentialGroup();
        vseq.addGroup(verticalGroupName);
        vseq.addGroup(verticalGroupSurname);
        vseq.addGroup(verticalGroupAddress);
        vseq.addGroup(verticalGroupPhone);
        vseq.addGroup(verticalGroupEmail);
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
                p.setName(name.getText());
                p.setSurname(surname.getText());
                p.setAddress(address.getText());
                p.setPhone(phone.getText());
                p.setEmail(email.getText());

                JOptionPane.showMessageDialog(addUserForm, LabelsConstants.MODIFY_PATIENT_SUCCESS);
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(addUserForm, LabelsConstants.WRONG_EMAIL_ERROR, null,
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
