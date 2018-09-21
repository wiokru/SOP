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

        GroupLayout.Group vg_name = form.createParallelGroup();
        GroupLayout.Group vg_surname = form.createParallelGroup();
        GroupLayout.Group vg_address = form.createParallelGroup();
        GroupLayout.Group vg_phone = form.createParallelGroup();
        GroupLayout.Group vg_email = form.createParallelGroup();
        GroupLayout.Group vg_password = form.createParallelGroup();
        GroupLayout.Group vg_save = form.createParallelGroup();

        JLabel name_label = new JLabel (LabelsConstants.NAME);
        hg1.addComponent(name_label);
        vg_name.addComponent(name_label);

        JLabel surname_label = new JLabel (LabelsConstants.SURNAME);
        hg1.addComponent(surname_label);
        vg_surname.addComponent(surname_label);

        JLabel address_label = new JLabel(LabelsConstants.ADDRESS);
        hg1.addComponent(address_label);
        vg_address.addComponent(address_label);

        JLabel phone_label = new JLabel(LabelsConstants.PHONE);
        hg1.addComponent(phone_label);
        vg_phone.addComponent(phone_label);

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

        JTextField address = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(address);
        vg_address.addComponent(address);

        JTextField phone = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(phone);
        vg_phone.addComponent(phone);

        JTextField email = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(email);
        vg_email.addComponent(email);

        JTextField password = new JTextField(TEXT_FIELD_SIZE);
        hg2.addComponent(password);
        vg_password.addComponent(password);

        JButton save = new JButton(LabelsConstants.SAVE_BTN);
        hg2.addComponent(save);
        vg_save.addComponent(save);

        name_label.setLabelFor(name);
        surname_label.setLabelFor(surname);
        address_label.setLabelFor(address);
        phone_label.setLabelFor(phone);
        email_label.setLabelFor(email);
        password_label.setLabelFor(password);

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
        vseq.addGroup(vg_name);
        vseq.addGroup(vg_surname);
        vseq.addGroup(vg_address);
        vseq.addGroup(vg_phone);
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
                JOptionPane.showMessageDialog(addUserForm, LabelsConstants.WRONG_EMAIL_ERROR, null, JOptionPane.ERROR_MESSAGE);
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

        GroupLayout.Group vg_name = form.createParallelGroup();
        GroupLayout.Group vg_surname = form.createParallelGroup();
        GroupLayout.Group vg_address = form.createParallelGroup();
        GroupLayout.Group vg_phone = form.createParallelGroup();
        GroupLayout.Group vg_email = form.createParallelGroup();
        GroupLayout.Group vg_save = form.createParallelGroup();

        JLabel name_label = new JLabel (LabelsConstants.NAME);
        hg1.addComponent(name_label);
        vg_name.addComponent(name_label);

        JLabel surname_label = new JLabel (LabelsConstants.SURNAME);
        hg1.addComponent(surname_label);
        vg_surname.addComponent(surname_label);

        JLabel address_label = new JLabel(LabelsConstants.ADDRESS);
        hg1.addComponent(address_label);
        vg_address.addComponent(address_label);

        JLabel phone_label = new JLabel(LabelsConstants.PHONE);
        hg1.addComponent(phone_label);
        vg_phone.addComponent(phone_label);

        JLabel email_label = new JLabel (LabelsConstants.EMAIL);
        hg1.addComponent(email_label);
        vg_email.addComponent(email_label);

        JTextField name = new JTextField(TEXT_FIELD_SIZE);
        name.setText(p.getName());
        hg2.addComponent(name);
        vg_name.addComponent(name);

        JTextField surname = new JTextField(TEXT_FIELD_SIZE);
        surname.setText(p.getSurname());
        hg2.addComponent(surname);
        vg_surname.addComponent(surname);

        JTextField address = new JTextField(TEXT_FIELD_SIZE);
        address.setText(p.getAddress());
        hg2.addComponent(address);
        vg_address.addComponent(address);

        JTextField phone = new JTextField(TEXT_FIELD_SIZE);
        phone.setText(p.getPhone());
        hg2.addComponent(phone);
        vg_phone.addComponent(phone);

        JTextField email = new JTextField(TEXT_FIELD_SIZE);
        email.setText(p.getEmail());
        hg2.addComponent(email);
        vg_email.addComponent(email);

        JButton save = new JButton(LabelsConstants.SAVE_BTN);
        hg2.addComponent(save);
        vg_save.addComponent(save);

        name_label.setLabelFor(name);
        surname_label.setLabelFor(surname);
        address_label.setLabelFor(address);
        phone_label.setLabelFor(phone);
        email_label.setLabelFor(email);

        name.isEditable();
        surname.isEditable();
        address.isEditable();
        phone.isEditable();
        email.isEditable();

        GroupLayout.SequentialGroup hseq = form.createSequentialGroup();
        hseq.addGroup(hg1);
        hseq.addGroup(hg2);

        GroupLayout.SequentialGroup vseq = form.createSequentialGroup();
        vseq.addGroup(vg_name);
        vseq.addGroup(vg_surname);
        vseq.addGroup(vg_address);
        vseq.addGroup(vg_phone);
        vseq.addGroup(vg_email);
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
                p.setName(name.getText());
                p.setSurname(surname.getText());
                p.setAddress(address.getText());
                p.setPhone(phone.getText());
                p.setEmail(email.getText());

                JOptionPane.showMessageDialog(addUserForm, LabelsConstants.MODIFY_PATIENT_SUCCESS);
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(addUserForm, LabelsConstants.WRONG_EMAIL_ERROR, null, JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
