package modupdater.gui;

import modupdater.gui.event.wiki.WikiEnableItemListener;
import modupdater.gui.event.wiki.WikiTextActionListener;
import modupdater.gui.util.MaxLengthDocument;

import javax.swing.*;
import java.awt.*;

public class ConfigWikiGui extends JFrame {

    private JFrame frameConfigWiki;
    private JCheckBox enableWiki;
    public static JTextField pageMain;
    public static JTextField pageChangelog;
    public static JTextField loginUser;
    public static JPasswordField loginPass;
    public static JTextField sectionSize;

    public static boolean isWikiEnabled = true;
    public static String wikiUsername = "";
    public static String wikiPassword = "";
    public static String wikiMainModPage = "";
    public static String wikiChangelogPage = "";
    public static int wikiSectionSize = 2;

    public ConfigWikiGui() {
        initialize();
    }

    public void initialize() {
        frameConfigWiki = new JFrame();
        frameConfigWiki.setLayout(new GridBagLayout());
        frameConfigWiki.setTitle("Wiki Configuration");
        frameConfigWiki.setBounds(100, 100, 450, 285);
        frameConfigWiki.setVisible(true);
        frameConfigWiki.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enableWiki = new JCheckBox("Enable Wiki");
        enableWiki.setSelected(true);
        enableWiki.addItemListener(new WikiEnableItemListener());
        GridBagConstraints gbcEnableWiki = new GridBagConstraints();
        gbcEnableWiki.insets = new Insets(2, 2, 2, 2);
        gbcEnableWiki.gridx = 0;
        gbcEnableWiki.gridy = 0;

        JLabel labelPageMain = new JLabel("Main Mod Page");
        GridBagConstraints gbcLabelPageMain = new GridBagConstraints();
        gbcLabelPageMain.insets = new Insets(2, 1, 2, 2);
        gbcLabelPageMain.gridx = 0;
        gbcLabelPageMain.gridy = 1;

        pageMain = new JTextField(15);
        pageMain.addActionListener(new WikiTextActionListener(pageMain));
        GridBagConstraints gbcPageMain = new GridBagConstraints();
        gbcPageMain.insets = new Insets(2, 2, 2, 1);
        gbcPageMain.gridx = 1;
        gbcPageMain.gridy = 1;

        JLabel labelPageChangelog = new JLabel("Changelog Page");
        GridBagConstraints gbcLabelPageChangelog = new GridBagConstraints();
        gbcLabelPageChangelog.insets = new Insets(2, 1, 2, 2);
        gbcLabelPageChangelog.gridx = 0;
        gbcLabelPageChangelog.gridy = 2;

        pageChangelog = new JTextField(15);
        pageChangelog.addActionListener(new WikiTextActionListener(pageChangelog));
        GridBagConstraints gbcPageChangelog = new GridBagConstraints();
        gbcPageChangelog.insets = new Insets(2, 2, 2, 1);
        gbcPageChangelog.gridx = 1;
        gbcPageChangelog.gridy = 2;

        JLabel labelLoginUser = new JLabel("Username");
        GridBagConstraints gbcLabelLoginUser = new GridBagConstraints();
        gbcLabelLoginUser.insets = new Insets(2, 1, 2, 2);
        gbcLabelLoginUser.gridx = 0;
        gbcLabelLoginUser.gridy = 3;

        loginUser = new JTextField(15);
        loginUser.addActionListener(new WikiTextActionListener(loginUser));
        GridBagConstraints gbcLoginUser = new GridBagConstraints();
        gbcLoginUser.insets = new Insets(2, 2, 2, 1);
        gbcLoginUser.gridx = 1;
        gbcLoginUser.gridy = 3;

        JLabel labelLoginPass = new JLabel("Password");
        GridBagConstraints gbcLabelLoginPass = new GridBagConstraints();
        gbcLabelLoginPass.insets = new Insets(2, 1, 2, 2);
        gbcLabelLoginPass.gridx = 0;
        gbcLabelLoginPass.gridy = 4;

        loginPass = new JPasswordField(15);
        loginPass.setEchoChar('!');
        loginPass.addActionListener(new WikiTextActionListener(loginPass));
        GridBagConstraints gbcLoginPass = new GridBagConstraints();
        gbcLoginPass.insets = new Insets(2, 2, 2, 1);
        gbcLoginPass.gridx = 1;
        gbcLoginPass.gridy = 4;

        JLabel labelSectionSize = new JLabel("New Section Size");
        GridBagConstraints gbcLabelSectionSize = new GridBagConstraints();
        gbcLabelSectionSize.insets = new Insets(2, 1, 2, 2);
        gbcLabelSectionSize.gridx = 0;
        gbcLabelSectionSize.gridy = 5;

        MaxLengthDocument sectionSizeLength = new MaxLengthDocument();
        sectionSizeLength.setMaxChars(1);
        sectionSize = new JTextField(2);
        sectionSize.setDocument(sectionSizeLength);
        sectionSize.addActionListener(new WikiTextActionListener(sectionSize));
        GridBagConstraints gbcSectionSize = new GridBagConstraints();
        gbcSectionSize.insets = new Insets(2, 2, 2, 1);
        gbcSectionSize.gridx = 1;
        gbcSectionSize.gridy = 5;

        frameConfigWiki.getContentPane().add(enableWiki, gbcEnableWiki);
        frameConfigWiki.getContentPane().add(labelPageMain, gbcLabelPageMain);
        frameConfigWiki.getContentPane().add(pageMain, gbcPageMain);
        frameConfigWiki.getContentPane().add(labelPageChangelog, gbcLabelPageChangelog);
        frameConfigWiki.getContentPane().add(pageChangelog, gbcPageChangelog);
        frameConfigWiki.getContentPane().add(labelLoginUser, gbcLabelLoginUser);
        frameConfigWiki.getContentPane().add(loginUser, gbcLoginUser);
        frameConfigWiki.getContentPane().add(labelLoginPass, gbcLabelLoginPass);
        frameConfigWiki.getContentPane().add(loginPass, gbcLoginPass);
        frameConfigWiki.getContentPane().add(labelSectionSize, gbcLabelSectionSize);
        frameConfigWiki.getContentPane().add(sectionSize, gbcSectionSize);
    }
}
