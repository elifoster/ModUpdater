package modupdater.gui.event.curseforge;

import modupdater.gui.ConfigCurseForgeGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurseForgeTextActionListener implements ActionListener {

    public static JTextField jTextField;

    public CurseForgeTextActionListener(JTextField textField) {
        jTextField = textField;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (jTextField == ConfigCurseForgeGui.apiKey) {
            ConfigCurseForgeGui.apiKeyString = jTextField.getText();
        }

        if (jTextField == ConfigCurseForgeGui.fileName) {
            ConfigCurseForgeGui.fileNameString = jTextField.getText();
        }

        if (jTextField == ConfigCurseForgeGui.projectID) {
            ConfigCurseForgeGui.projectIDInt = Integer.parseInt(jTextField.getText());
        }
    }
}
