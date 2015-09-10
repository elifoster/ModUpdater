package modupdater.gui.event.twitter;

import modupdater.gui.ConfigTwitterGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwitterTextActionListener implements ActionListener {

    public static JTextField jTextField;

    public TwitterTextActionListener(JTextField textField) {
        jTextField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (jTextField == ConfigTwitterGui.loginUser) {
            ConfigTwitterGui.twitterUsername = jTextField.getText();
        }

        if (jTextField == ConfigTwitterGui.loginPass) {
            ConfigTwitterGui.twitterPassword = jTextField.getText();
        }
    }
}