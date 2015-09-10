package modupdater.gui.event.curseforge;

import modupdater.gui.ConfigCurseForgeGui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CurseForgeBrowseActionListener implements ActionListener {

    private JFrame parent;

    public CurseForgeBrowseActionListener(JFrame frame) {
        parent = frame;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();

        File curDir = new File(".");
        chooser.setCurrentDirectory(curDir);
        chooser.setFileFilter(new FileNameExtensionFilter("JAR/ZIP files", "jar", "zip"));

        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            ConfigCurseForgeGui.fileBrowseOutput.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }
}
