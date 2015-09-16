package modupdater.gui;

import modupdater.Rubyer;
import modupdater.gui.event.EnableItemListener;
import modupdater.gui.event.JSONBrowseActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ModUpdaterGui {

    private JFrame frameModUpdater;
    private JCheckBox enableCurseForge;
    private JCheckBox enableTwitter;
    private JCheckBox enableWiki;
    public static JButton buttonConfigWiki;
    public static JButton buttonConfigCurseForge;
    public static JButton buttonConfigTwitter;
    private JButton buttonChangelog;
    private JButton buttonGo;
    private JButton browseJSON;
    public static JTextField browseJSONOutput;

    public static boolean isWikiEnabled = true;
    public static String wikiUsername = "";
    public static String wikiPassword = "";
    public static String wikiMainModPage = "";
    public static String wikiChangelogPage = "";
    public static int wikiSectionSize = 2;

    public static boolean isTwitterEnabled = true;
    public static String twitterUsername = "";
    public static String twitterPassword = "";
    public static String tweet = "";

    public static String apiKeyString = "";
    public static String fileNameString = "";
    public static int projectIDInt = 000000;
    public static boolean isCurseForgeEnabled = true;

    public ModUpdaterGui() {
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    for (UIManager.LookAndFeelInfo lafi : UIManager.getInstalledLookAndFeels()) {
                        System.out.println(String.format("Installed look and feel: %s @ %s", lafi.getName(), lafi.getClassName()));
                    }
                    ModUpdaterGui window = new ModUpdaterGui();
                    window.frameModUpdater.setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public void initialize() {
        frameModUpdater = new JFrame();
        frameModUpdater.setLayout(new GridBagLayout());
        frameModUpdater.setTitle("ModUpdater");
        frameModUpdater.setBounds(100, 100, 560, 285);
        frameModUpdater.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonConfigWiki = new JButton("Configure Wiki Settings");
        buttonConfigWiki.setVisible(true);
        buttonConfigWiki.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ConfigWikiGui wikiConfig = new ConfigWikiGui();
            }
        });
        GridBagConstraints gbcButtonConfigWiki = new GridBagConstraints();
        gbcButtonConfigWiki.insets = new Insets(2, 2, 2, 2);
        gbcButtonConfigWiki.gridx = 1;
        gbcButtonConfigWiki.gridy = 0;

        enableWiki = new JCheckBox("Enable Wiki");
        enableWiki.setSelected(true);
        enableWiki.addItemListener(new EnableItemListener(buttonConfigWiki, isWikiEnabled));
        GridBagConstraints gbcEnableWiki = new GridBagConstraints();
        gbcEnableWiki.insets = new Insets(2, 2, 2, 2);
        gbcEnableWiki.gridx = 0;
        gbcEnableWiki.gridy = 0;

        buttonConfigCurseForge = new JButton("Configure CurseForge Settings");
        buttonConfigCurseForge.setVisible(true);
        buttonConfigCurseForge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ConfigCurseForgeGui cfConfig = new ConfigCurseForgeGui();
            }
        });
        GridBagConstraints gbcButtonConfigCurseForge = new GridBagConstraints();
        gbcButtonConfigCurseForge.insets = new Insets(2, 2, 2, 2);
        gbcButtonConfigCurseForge.gridx = 1;
        gbcButtonConfigCurseForge.gridy = 1;

        enableCurseForge = new JCheckBox("Enable CurseForge");
        enableCurseForge.setSelected(true);
        enableCurseForge.addItemListener(new EnableItemListener(buttonConfigCurseForge, isCurseForgeEnabled));
        GridBagConstraints gbcEnableCurseForge = new GridBagConstraints();
        gbcEnableCurseForge.insets = new Insets(2, 2, 2, 2);
        gbcEnableCurseForge.gridx = 0;
        gbcEnableCurseForge.gridy = 1;

        buttonConfigTwitter = new JButton("Configure Twitter Settings");
        buttonConfigTwitter.setVisible(true);
        buttonConfigTwitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ConfigTwitterGui twitterConfig = new ConfigTwitterGui();
            }
        });
        GridBagConstraints gbcButtonConfigTwitter = new GridBagConstraints();
        gbcButtonConfigTwitter.insets = new Insets(2, 2, 2, 2);
        gbcButtonConfigTwitter.gridx = 1;
        gbcButtonConfigTwitter.gridy = 2;

        enableTwitter = new JCheckBox("Enable Twitter");
        enableTwitter.setSelected(true);
        enableTwitter.addItemListener(new EnableItemListener(buttonConfigTwitter, isTwitterEnabled));
        GridBagConstraints gbcEnableTwitter = new GridBagConstraints();
        gbcEnableTwitter.insets = new Insets(2, 2, 2, 2);
        gbcEnableTwitter.gridx = 0;
        gbcEnableTwitter.gridy = 2;

        buttonChangelog = new JButton("Create Changelog");
        buttonChangelog.setVisible(true);
        buttonChangelog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangelogGui gui = new ChangelogGui();
            }
        });
        GridBagConstraints gbcButtonChangelog = new GridBagConstraints();
        gbcButtonChangelog.insets = new Insets(2, 2, 2, 2);
        gbcButtonChangelog.gridx = 1;
        gbcButtonChangelog.gridy = 3;

        browseJSON = new JButton("Use Old JSON Configuration");
        browseJSON.setVisible(true);
        browseJSON.addActionListener(new JSONBrowseActionListener(frameModUpdater));
        GridBagConstraints gbcBrowseJSON = new GridBagConstraints();
        gbcBrowseJSON.insets = new Insets(2, 2, 2, 2);
        gbcBrowseJSON.gridx = 0;
        gbcBrowseJSON.gridy = 4;

        browseJSONOutput = new JTextField(20);
        browseJSONOutput.setVisible(true);
        GridBagConstraints gbcBrowseJSONOutput = new GridBagConstraints();
        gbcBrowseJSONOutput.insets = new Insets(2, 2, 2, 2);
        gbcBrowseJSONOutput.gridx = 1;
        gbcBrowseJSONOutput.gridy = 4;

        buttonGo = new JButton("Go!");
        buttonGo.setVisible(true);
        buttonGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Rubyer.exec("ruby ../base.rb");
            }
        });
        GridBagConstraints gbcButtonGo = new GridBagConstraints();
        gbcButtonGo.insets = new Insets(2, 2, 2, 2);
        gbcButtonGo.gridx = 0;
        gbcButtonGo.gridy = 5;

        frameModUpdater.getContentPane().add(enableWiki, gbcEnableWiki);
        frameModUpdater.getContentPane().add(buttonConfigWiki, gbcButtonConfigWiki);
        frameModUpdater.getContentPane().add(enableCurseForge, gbcEnableCurseForge);
        frameModUpdater.getContentPane().add(buttonConfigCurseForge, gbcButtonConfigCurseForge);
        frameModUpdater.getContentPane().add(enableTwitter, gbcEnableTwitter);
        frameModUpdater.getContentPane().add(buttonConfigTwitter, gbcButtonConfigTwitter);
        frameModUpdater.getContentPane().add(buttonChangelog, gbcButtonChangelog);
        frameModUpdater.getContentPane().add(browseJSON, gbcBrowseJSON);
        frameModUpdater.getContentPane().add(browseJSONOutput, gbcBrowseJSONOutput);
        frameModUpdater.getContentPane().add(buttonGo, gbcButtonGo);
    }
}
