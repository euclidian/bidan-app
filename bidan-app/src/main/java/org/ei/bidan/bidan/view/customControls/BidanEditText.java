package org.ei.bidan.bidan.view.customControls;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Dimas Ciputra on 7/2/15.
 */
public class BidanEditText extends EditText {

    public BidanEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    public BidanEditText(Context context) {
        super(context);

    }

    public BidanEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.getAction() == KeyEvent.ACTION_UP) {
            this.clearFocus();
        }
        return false;
    }
}
