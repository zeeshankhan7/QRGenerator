package com.qrgeneratorapp.customviews;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by Tejas Sherdiwala on 17/01/17.
 */

public class CustomFontButton extends AppCompatButton {

    private static final int DEFAULT_FONT_FACE = TypeFaceHelper.SIGNIKA_SEMI_BOLD;

    public CustomFontButton(Context context) {
        super(context);
        setTypeface(TypeFaceHelper.getTypeFaceFromAttr(context,null,DEFAULT_FONT_FACE));
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(TypeFaceHelper.getTypeFaceFromAttr(context,attrs,DEFAULT_FONT_FACE));
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(TypeFaceHelper.getTypeFaceFromAttr(context,attrs,DEFAULT_FONT_FACE));
    }

}
