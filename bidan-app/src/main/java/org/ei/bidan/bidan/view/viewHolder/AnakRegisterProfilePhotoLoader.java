package org.ei.bidan.bidan.view.viewHolder;

import android.graphics.drawable.Drawable;

import org.ei.bidan.bidan.view.contract.AnakClient;
import org.ei.bidan.view.contract.SmartRegisterClient;
import org.ei.bidan.view.viewHolder.ProfilePhotoLoader;

import static org.ei.bidan.AllConstants.FEMALE_GENDER_INA;

public class AnakRegisterProfilePhotoLoader implements ProfilePhotoLoader {
    private final Drawable maleInfantDrawable;
    private final Drawable femaleInfantDrawable;

    public AnakRegisterProfilePhotoLoader(Drawable maleInfantDrawable, Drawable femaleInfantDrawable) {
        this.maleInfantDrawable = maleInfantDrawable;
        this.femaleInfantDrawable = femaleInfantDrawable;
    }

    public Drawable get(SmartRegisterClient client) {
        return FEMALE_GENDER_INA.equalsIgnoreCase(((AnakClient) client).gender())
                ? femaleInfantDrawable
                : maleInfantDrawable;
    }
}
