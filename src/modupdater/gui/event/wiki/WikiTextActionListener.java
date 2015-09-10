package modupdater.gui.event.wiki;

import modupdater.gui.ConfigWikiGui;
import modupdater.gui.ModUpdaterGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WikiTextActionListener implements ActionListener {

    public static JTextField jTextField;

    public WikiTextActionListener(JTextField textField) {
        jTextField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (jTextField == ConfigWikiGui.loginPass) {
            ModUpdaterGui.wikiUsername = jTextField.getText();
        }

        if (jTextField == ConfigWikiGui.loginPass) {
            ModUpdaterGui.wikiPassword = jTextField.getText();
        }

        if (jTextField == ConfigWikiGui.pageMain) {
            ModUpdaterGui.wikiMainModPage = jTextField.getText();
        }

        if (jTextField == ConfigWikiGui.pageChangelog) {
            ModUpdaterGui.wikiChangelogPage = jTextField.getText();
        }

        if (jTextField == ConfigWikiGui.sectionSize) {
            ModUpdaterGui.wikiSectionSize = Integer.parseInt(jTextField.getText());
        }
    }
}