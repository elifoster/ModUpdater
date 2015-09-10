package modupdater.gui.event.twitter;

import modupdater.gui.ConfigTwitterGui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TwitterEnableItemListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent event) {
        boolean enabled = ConfigTwitterGui.isTwitterEnabled;
        if (event.getStateChange() == ItemEvent.DESELECTED) {
            enabled = false;
        } else if (event.getStateChange() == ItemEvent.SELECTED) {
            enabled = true;
        }

        ConfigTwitterGui.loginPass.setEnabled(enabled);
        ConfigTwitterGui.loginUser.setEnabled(enabled);
        ConfigTwitterGui.tweet.setEnabled(enabled);
    }
}
