package modupdater.gui.event.curseforge;

import modupdater.gui.ConfigCurseForgeGui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CurseForgeEnableItemListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent event) {
        boolean enabled = ConfigCurseForgeGui.isCurseForgeEnabled;
        if (event.getStateChange() == ItemEvent.DESELECTED) {
            enabled = false;
        } else if (event.getStateChange() == ItemEvent.SELECTED) {
            enabled = true;
        }

        ConfigCurseForgeGui.projectID.setEnabled(enabled);
        ConfigCurseForgeGui.apiKey.setEnabled(enabled);
        ConfigCurseForgeGui.fileName.setEnabled(enabled);
        ConfigCurseForgeGui.gameVersions.setEnabled(enabled);
        ConfigCurseForgeGui.releaseTypes.setEnabled(enabled);
        ConfigCurseForgeGui.fileBrowse.setEnabled(enabled);
        ConfigCurseForgeGui.fileBrowseOutput.setEnabled(enabled);
        ConfigCurseForgeGui.javaVersions.setEnabled(enabled);
    }
}
