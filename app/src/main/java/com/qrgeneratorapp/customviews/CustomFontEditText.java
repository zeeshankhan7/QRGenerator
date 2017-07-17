package com.qrgeneratorapp.customviews;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;

/**
 * Created by zeeshan on 7/15/2017.
 */

public class CustomFontEditText extends TextInputEditText {
    private static final int DEFAULT_FONT_FACE = TypeFaceHelper.SOURCE_SANS_PRO_REGULAR;

    public CustomFontEditText(Context context) {
        super(context);
        setTypeface(TypeFaceHelper.getTypeFaceFromAttr(context,null,DEFAULT_FONT_FACE));
    }

    public CustomFontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(TypeFaceHelper.getTypeFaceFromAttr(context,attrs,DEFAULT_FONT_FACE));
    }

    public CustomFontEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(TypeFaceHelper.getTypeFaceFromAttr(context,attrs,DEFAULT_FONT_FACE));
    }

    @Override
    public void setError(CharSequence error) {
        ((TextInputLayout)getParent().getParent()).setError(error);
    }
}
