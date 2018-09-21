import users.Doctor;
import users.Patient;
import users.User;
import users.Visit;

import javax.swing.*;
import java.awt.*;

public class ShowItemPanel extends JFrame{

    public ShowItemPanel(Class<?> cls){
        JPanel showItem = new JPanel(new BorderLayout());
        JList itemList;

        if (cls == Doctor.class){
            DefaultListModel<String> doctors = new DefaultListModel<>();
            SOP.users.stream()
                    .filter(user -> user instanceof Doctor)
                    .map(User::toString)
                    .forEach(doctors::addElement);

            itemList = new JList<>(doctors);
        }
        else if (cls == Patient.class){
            DefaultListModel<String> patients = new DefaultListModel<>();

            SOP.users.stream()
                    .filter(user -> user instanceof Patient)
                    .map(User::toString)
                    .forEach(patients::addElement);

            itemList = new JList<>(patients);
        }
        else{ // (cls == Visit.class)
            DefaultListModel<String> visits = new DefaultListModel<>();
            SOP.visits.stream().map(Visit::toString).forEach(visits::addElement);
            itemList = new JList<>(visits);
        }

        showItem.add(new JScrollPane(itemList));
        add(showItem);
        setSize(800,600);
        setLocationByPlatform(true);
        setVisible(true);
    }

}
