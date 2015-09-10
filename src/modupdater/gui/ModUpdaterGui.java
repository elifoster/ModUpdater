package modupdater.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModUpdaterGui {

    private JFrame frameModUpdater;
    private JButton buttonConfigWiki;
    private JButton buttonConfigCurseForge;
    private JButton buttonConfigTwitter;

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
        frameModUpdater.setBounds(100, 100, 450, 285);
        frameModUpdater.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonConfigWiki = new JButton("Configure Wiki Settings");
        buttonConfigWiki.setVisible(true);
        buttonConfigWiki.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ConfigWikiGui wikiConfig = new ConfigWikiGui();
            }
        });
        GridBagConstraints gbcButtonConfigWiki = new GridBagConstraints();
        gbcButtonConfigWiki.insets = new Insets(0, 0, 5, 0);
        gbcButtonConfigWiki.gridx = 0;
        gbcButtonConfigWiki.gridy = 0;

        buttonConfigCurseForge = new JButton("Configure CurseForge Settings");
        buttonConfigCurseForge.setVisible(true);
        buttonConfigCurseForge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ConfigCurseForgeGui cfConfig = new ConfigCurseForgeGui();
            }
        });
        GridBagConstraints gbcButtonConfigCurseForge = new GridBagConstraints();
        gbcButtonConfigCurseForge.insets = new Insets(0, 0, 5, 0);
        gbcButtonConfigCurseForge.gridx = 0;
        gbcButtonConfigCurseForge.gridy = 1;

        buttonConfigTwitter = new JButton("Configure Twitter Settings");
        buttonConfigTwitter.setVisible(true);
        buttonConfigTwitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ConfigTwitterGui twitterConfig = new ConfigTwitterGui();
            }
        });
        GridBagConstraints gbcButtonConfigTwitter = new GridBagConstraints();
        gbcButtonConfigTwitter.insets = new Insets(0, 0, 5, 0);
        gbcButtonConfigTwitter.gridx = 0;
        gbcButtonConfigTwitter.gridy = 2;

        frameModUpdater.getContentPane().add(buttonConfigWiki, gbcButtonConfigWiki);
        frameModUpdater.getContentPane().add(buttonConfigCurseForge, gbcButtonConfigCurseForge);
        frameModUpdater.getContentPane().add(buttonConfigTwitter, gbcButtonConfigTwitter);
    }
}
