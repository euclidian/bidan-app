package org.ei.bidan.view.viewHolder;

import android.graphics.drawable.Drawable;
import org.ei.bidan.view.contract.SmartRegisterClient;

public interface ProfilePhotoLoader {
    public Drawable get(SmartRegisterClient client);
}
