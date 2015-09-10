package modupdater.gui.event.wiki;

import modupdater.gui.ConfigWikiGui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class WikiEnableItemListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent event) {
        boolean enabled = ConfigWikiGui.isWikiEnabled;
        if (event.getStateChange() == ItemEvent.DESELECTED) {
            enabled = false;
        } else if (event.getStateChange() == ItemEvent.SELECTED) {
            enabled = true;
        }

        ConfigWikiGui.loginPass.setEnabled(enabled);
        ConfigWikiGui.loginUser.setEnabled(enabled);
        ConfigWikiGui.pageChangelog.setEnabled(enabled);
        ConfigWikiGui.pageMain.setEnabled(enabled);
        ConfigWikiGui.sectionSize.setEnabled(enabled);
    }
}
