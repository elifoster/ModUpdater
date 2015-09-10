package modupdater.gui.event.wiki;

import modupdater.gui.ConfigWikiGui;

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
            ConfigWikiGui.wikiUsername = jTextField.getText();
        }

        if (jTextField == ConfigWikiGui.loginPass) {
            ConfigWikiGui.wikiPassword = jTextField.getText();
        }

        if (jTextField == ConfigWikiGui.pageMain) {
            ConfigWikiGui.wikiMainModPage = jTextField.getText();
        }

        if (jTextField == ConfigWikiGui.pageChangelog) {
            ConfigWikiGui.wikiChangelogPage = jTextField.getText();
        }

        if (jTextField == ConfigWikiGui.sectionSize) {
            ConfigWikiGui.wikiSectionSize = Integer.parseInt(jTextField.getText());
        }
    }
}