package modupdater.gui;

import modupdater.gui.event.curseforge.CurseForgeBrowseActionListener;
import modupdater.gui.event.curseforge.CurseForgeTextActionListener;
import modupdater.gui.util.GameVersions;
import sun.jvm.hotspot.types.JIntField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ConfigCurseForgeGui extends JFrame {

    private JFrame frameConfigCurseForge;
    public static JPasswordField apiKey;
    public static JTextField fileName;
    public static JTextField projectID;
    public static JComboBox gameVersions;
    public static JComboBox releaseTypes;
    public static JComboBox javaVersions;
    public static JButton fileBrowse;
    public static JTextField fileBrowseOutput;

    public ConfigCurseForgeGui() {
        initialize();
    }

    public void initialize() {
        frameConfigCurseForge = new JFrame();
        frameConfigCurseForge.setLayout(new GridBagLayout());
        frameConfigCurseForge.setTitle("CurseForge Configuration");
        frameConfigCurseForge.setBounds(100, 100, 550, 285);
        frameConfigCurseForge.setVisible(true);
        frameConfigCurseForge.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel apiKeyLabel = new JLabel("API Key");
        GridBagConstraints gbcLabelApiKey = new GridBagConstraints();
        gbcLabelApiKey.insets = new Insets(2, 1, 2, 2);
        gbcLabelApiKey.gridx = 0;
        gbcLabelApiKey.gridy = 0;

        apiKey = new JPasswordField(20);
        apiKey.setEchoChar('@');
        apiKey.addActionListener(new CurseForgeTextActionListener(apiKey));
        GridBagConstraints gbcApiKey = new GridBagConstraints();
        gbcApiKey.insets = new Insets(2, 2, 2, 1);
        gbcApiKey.gridx = 1;
        gbcApiKey.gridy = 0;

        JLabel fileNameLabel = new JLabel("File Display Name");
        GridBagConstraints gbcLabelFileName = new GridBagConstraints();
        gbcLabelFileName.insets = new Insets(2, 1, 2, 2);
        gbcLabelFileName.gridx = 0;
        gbcLabelFileName.gridy = 1;

        fileName = new JTextField(15);
        fileName.addActionListener(new CurseForgeTextActionListener(fileName));
        GridBagConstraints gbcFileName = new GridBagConstraints();
        gbcFileName.insets = new Insets(2, 2, 2, 1);
        gbcFileName.gridx = 1;
        gbcFileName.gridy = 1;

        JLabel projectIDLabel = new JLabel("Project ID");
        GridBagConstraints gbcLabelProjectID = new GridBagConstraints();
        gbcLabelProjectID.insets = new Insets(2, 1, 2, 2);
        gbcLabelProjectID.gridx = 0;
        gbcLabelProjectID.gridy = 2;

        projectID = new JTextField(10);
        projectID.addActionListener(new CurseForgeTextActionListener(projectID));
        GridBagConstraints gbcProjectID = new GridBagConstraints();
        gbcProjectID.insets = new Insets(2, 2, 2, 1);
        gbcProjectID.gridx = 1;
        gbcProjectID.gridy = 2;

        ArrayList<String> versions = new ArrayList<String>();
        for (GameVersions vers : GameVersions.values()) {
            versions.add(vers.toString());
        }
        gameVersions = new JComboBox(versions.toArray());
        gameVersions.setSelectedItem(GameVersions.R1710.toString());
        GridBagConstraints gbcGameVersions = new GridBagConstraints();
        gbcGameVersions.insets = new Insets(2, 2, 2, 1);
        gbcGameVersions.gridx = 0;
        gbcGameVersions.gridy = 3;

        String types[] = {"release", "beta", "alpha"};
        releaseTypes = new JComboBox(types);
        GridBagConstraints gbcReleaseTypes = new GridBagConstraints();
        gbcReleaseTypes.insets = new Insets(2, 1, 2, 1);
        gbcReleaseTypes.gridx = 1;
        gbcReleaseTypes.gridy = 3;

        String javaStrings[] = {"Java 6", "Java 7", "Java 8"};
        javaVersions = new JComboBox(javaStrings);
        GridBagConstraints gbcJavaVersions = new GridBagConstraints();
        gbcJavaVersions.insets = new Insets(2, 1, 2, 2);
        gbcJavaVersions.gridx = 2;
        gbcJavaVersions.gridy = 3;

        fileBrowse = new JButton("Browse");
        fileBrowse.addActionListener(new CurseForgeBrowseActionListener(frameConfigCurseForge));
        GridBagConstraints gbcFileBrowse = new GridBagConstraints();
        gbcFileBrowse.insets = new Insets(2, 1, 2, 2);
        gbcFileBrowse.gridx = 0;
        gbcFileBrowse.gridy = 4;

        fileBrowseOutput = new JTextField(20);
        GridBagConstraints gbcFileBrowseOutput = new GridBagConstraints();
        gbcFileBrowseOutput.insets = new Insets(2, 2, 2, 1);
        gbcFileBrowseOutput.gridx = 1;
        gbcFileBrowseOutput.gridy = 4;

        frameConfigCurseForge.getContentPane().add(apiKeyLabel, gbcLabelApiKey);
        frameConfigCurseForge.getContentPane().add(apiKey, gbcApiKey);
        frameConfigCurseForge.getContentPane().add(fileNameLabel, gbcLabelFileName);
        frameConfigCurseForge.getContentPane().add(fileName, gbcFileName);
        frameConfigCurseForge.getContentPane().add(projectIDLabel, gbcLabelProjectID);
        frameConfigCurseForge.getContentPane().add(projectID, gbcProjectID);
        frameConfigCurseForge.getContentPane().add(gameVersions, gbcGameVersions);
        frameConfigCurseForge.getContentPane().add(releaseTypes, gbcReleaseTypes);
        frameConfigCurseForge.getContentPane().add(javaVersions, gbcJavaVersions);
        frameConfigCurseForge.getContentPane().add(fileBrowse, gbcFileBrowse);
        frameConfigCurseForge.getContentPane().add(fileBrowseOutput, gbcFileBrowseOutput);
    }
}
