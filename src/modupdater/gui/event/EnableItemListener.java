package modupdater.gui.event;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EnableItemListener implements ItemListener {

    private JButton button;
    private boolean toSet;

    /**
     * @param button Button to disable.
     * @param toSet  Boolean to set and use in itemStateChanged(ItemEvent)
     */
    public EnableItemListener(JButton button, boolean toSet) {
        this.button = button;
        this.toSet = toSet;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.DESELECTED) {
            toSet = false;
        } else if (event.getStateChange() == ItemEvent.SELECTED) {
            toSet = true;
        }

        button.setEnabled(toSet);
    }
}
