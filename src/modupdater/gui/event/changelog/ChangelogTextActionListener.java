package modupdater.gui.event.changelog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangelogTextActionListener implements ActionListener {

    private JTextField textField;

    public ChangelogTextActionListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
