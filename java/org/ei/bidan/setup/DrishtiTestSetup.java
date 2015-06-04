package org.ei.bidan.setup;

import org.robolectric.bytecode.ClassInfo;
import org.robolectric.bytecode.Setup;

public class DrishtiTestSetup extends Setup {
    @Override
    public boolean shouldInstrument(ClassInfo classInfo) {
        return super.shouldInstrument(classInfo)
                || classInfo.getName().equals("org.ei.bidan.Context")
                || classInfo.getName().equals("org.ei.bidan.view.controller.ECSmartRegisterController")
                || classInfo.getName().equals("org.ei.bidan.view.controller.VillageController");
    }
}
