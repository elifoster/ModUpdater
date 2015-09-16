package modupdater.gui.event;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EnableItemListener implements ItemListener {

    private JComponent[] components;
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

    public EnableItemListener(JComponent[] components, boolean toSet) {
        this.components = components;
        this.toSet = toSet;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.DESELECTED) {
            this.toSet = false;
        } else if (event.getStateChange() == ItemEvent.SELECTED) {
            this.toSet = true;
        }

        if (this.button != null) {
            this.button.setEnabled(toSet);
        }

        if (this.components != null) {
            for (int i = 0; i < this.components.length; i++) {
                this.components[i].setEnabled(toSet);
            }
        }
    }
}
