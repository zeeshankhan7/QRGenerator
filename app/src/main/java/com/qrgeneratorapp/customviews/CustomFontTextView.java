package com.qrgeneratorapp.customviews;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by zeeshan on 7/15/2017.
 */

public class CustomFontTextView extends AppCompatTextView {

    private static final int DEFAULT_FONT_FACE = TypeFaceHelper.SOURCE_SANS_PRO_REGULAR;

    public CustomFontTextView(Context context) {
        super(context);
        setTypeface(TypeFaceHelper.getTypeFaceFromAttr(context, null, DEFAULT_FONT_FACE));
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(TypeFaceHelper.getTypeFaceFromAttr(context, attrs, DEFAULT_FONT_FACE));
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(TypeFaceHelper.getTypeFaceFromAttr(context, attrs, DEFAULT_FONT_FACE));
    }
}
