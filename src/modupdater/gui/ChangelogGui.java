package modupdater.gui;

import modupdater.gui.event.EnableItemListener;
import modupdater.gui.event.changelog.ChangelogTextActionListener;

import javax.swing.*;
import java.awt.*;

public class ChangelogGui {

    private int numberOfEntries;
    private boolean areIssuesEnabled;
    private JFrame frame;
    private JButton buttonAddEntry;
    private JTextField type;
    private JTextField change;
    private JTextField issueNumber;
    private JCheckBox enableIssues;
    private JTextField issueURL;

    public ChangelogGui() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        frame.setTitle("Changelog Configuration");
        frame.setBounds(100, 100, 450, 285);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        issueURL = new JTextField(15);
        issueURL.addActionListener(new ChangelogTextActionListener(issueURL));
        GridBagConstraints gbcFileName = new GridBagConstraints();
        gbcFileName.insets = new Insets(2, 2, 2, 1);
        gbcFileName.gridx = 1;
        gbcFileName.gridy = 1;

        enableIssues = new JCheckBox("Enable Issues");
        enableIssues.setSelected(true);
        enableIssues.addItemListener(new EnableItemListener(new JComponent[] {issueNumber, issueURL}, areIssuesEnabled));
        GridBagConstraints gbcEnableTwitter = new GridBagConstraints();
        gbcEnableTwitter.insets = new Insets(2, 2, 2, 2);
        gbcEnableTwitter.gridx = 0;
        gbcEnableTwitter.gridy = 0;
    }
}
