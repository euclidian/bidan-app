package org.ei.bidan.setup;

import org.ei.bidan.BuildConfig;
import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.bytecode.Setup;

public class DrishtiTestRunner extends RobolectricTestRunner {

    public DrishtiTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        String buildVariant = (BuildConfig.FLAVOR.isEmpty()? "" : BuildConfig.FLAVOR) + "/" + BuildConfig.BUILD_TYPE;
        System.setProperty("android.package", BuildConfig.APPLICATION_ID);
        System.setProperty("android.manifest", "build/intermediates/manifests/full/" + buildVariant + "/AndroidManifest.xml");
        System.setProperty("android.resources", "build/intermediates/res/" + buildVariant);
        System.setProperty("android.assets", "build/intermediates/assets/" + buildVariant);
    }

    @Override
    public Setup createSetup() {
        return new DrishtiTestSetup();
    }
}
