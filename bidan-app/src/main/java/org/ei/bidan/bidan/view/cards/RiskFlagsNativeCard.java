package org.ei.bidan.bidan.view.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardWithList;

/**
 * Created by Dimas Ciputra on 6/20/15.
 */
public class RiskFlagsNativeCard extends CardWithList {

    public RiskFlagsNativeCard(Context context) { super(context); }

    @Override
    protected CardHeader initCardHeader() {
        return null;
    }

    @Override
    protected void initCard() {

    }

    @Override
    protected List<ListObject> initChildren() {
        return null;
    }

    @Override
    public View setupChildView(int i, ListObject listObject, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getChildLayoutId() {
        return 0;
    }
}
