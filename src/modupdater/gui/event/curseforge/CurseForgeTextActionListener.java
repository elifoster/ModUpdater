package modupdater.gui.event.curseforge;

import modupdater.gui.ConfigCurseForgeGui;
import modupdater.gui.ModUpdaterGui;

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
            ModUpdaterGui.apiKeyString = jTextField.getText();
        }

        if (jTextField == ConfigCurseForgeGui.fileName) {
            ModUpdaterGui.fileNameString = jTextField.getText();
        }

        if (jTextField == ConfigCurseForgeGui.projectID) {
            ModUpdaterGui.projectIDInt = Integer.parseInt(jTextField.getText());
        }
    }
}
