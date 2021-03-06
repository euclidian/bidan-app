package org.ei.bidan.view.customControls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import org.ei.bidan.R;
import org.ei.bidan.util.Cache;
import org.ei.bidan.util.CacheableData;

public class CustomFontTextView extends TextView {

    private Cache<Typeface> cache;

    @SuppressWarnings("UnusedDeclaration")
    public CustomFontTextView(Context context) {
        this(context, null, 0);
        if (isInEditMode()) return;
    }

    @SuppressWarnings("UnusedDeclaration")
    public CustomFontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        if (isInEditMode()) return;
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, 0);
        if (isInEditMode()) return;

        cache = org.ei.bidan.Context.getInstance().typefaceCache();
        TypedArray attributes = context.obtainStyledAttributes(
                attrs, R.styleable.org_ei_bidan_view_customControls_CustomFontTextView, 0, defStyle);
        int variant = attributes.getInt(
                R.styleable.org_ei_bidan_view_customControls_CustomFontTextView_fontVariant, 0);
        attributes.recycle();

        setFontVariant(variant);
    }

    public void setFontVariant(int variant) {
        setFontVariant(FontVariant.tryParse(variant, FontVariant.REGULAR));
    }

    public void setFontVariant(final FontVariant variant) {
        if (isInEditMode()) return;
        setTypeface(cache.get(variant.name(), new CacheableData<Typeface>() {
            @Override
            public Typeface fetch() {
                return Typeface.createFromAsset(
                        org.ei.bidan.Context.getInstance().applicationContext().getAssets(),
                        variant.fontFile());

            }
        }));

    }
}
