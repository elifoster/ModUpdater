package modupdater.gui.event;

import modupdater.gui.ModUpdaterGui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class JSONBrowseActionListener implements ActionListener {

    private JFrame parent;

    public JSONBrowseActionListener(JFrame parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();

        File curDir = new File(".");
        chooser.setCurrentDirectory(curDir);
        chooser.setFileFilter(new FileNameExtensionFilter("JSON files", "json"));

        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            ModUpdaterGui.browseJSONOutput.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }
}
