package com.qrgenerator.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;


import com.qrgeneratorapp.max.R;

import java.util.Hashtable;


public class TypeFaceHelper {

    private static Hashtable<String, Typeface> sFontCache = new Hashtable<>();

    private static Typeface get(String name, Context context) {
        Typeface tf = sFontCache.get(name);
        if (tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            } catch (Exception e) {
                return null;
            }
            sFontCache.put(name, tf);
        }
        return tf;
    }


    public static final int
            SIGNIKA_BOLD = 0,
            SIGNIKA_LIGHT = 1,
            SIGNIKA_REGULAR = 2,
            SIGNIKA_SEMI_BOLD = 3,
            SOURCE_SANS_PRO_BOLD = 4,
            SOURCE_SANS_PRO_ITALIC = 5,
            SOURCE_SANS_PRO_LIGHT = 6,
            SOURCE_SANS_PRO_REGULAR = 7,
            SOURCE_SANS_PRO_SEMI_BOLD = 8;

    private enum CustomTypeFace {
        _0("fonts/Signika-Bold.ttf"),
        _1("fonts/Signika-Light.ttf"),
        _2("fonts/Signika-Regular.ttf"),
        _3("fonts/Signika-Semibold.ttf"),
        _4("fonts/SourceSansPro-Bold.ttf"),
        _5("fonts/SourceSansPro-Italic.ttf"),
        _6("fonts/SourceSansPro-Light.ttf"),
        _7("fonts/SourceSansPro-Regular.ttf"),
        _8("fonts/SourceSansPro-Semibold.ttf");

        private final String mFileName;

        private Hashtable<String, Typeface> mFont = new Hashtable<String, Typeface>();

        CustomTypeFace(String fileName) {
            this.mFileName = fileName;
        }

        public Typeface asTypeface(Context context) {
            return TypeFaceHelper.get(mFileName, context);
        }

        /*static CustomTypeFace fromString(String fontName) {
            return CustomTypeFace.valueOf(fontName.toUpperCase(Locale.US));
        }*/

        public static CustomTypeFace fromInt(int fontName) {
            return CustomTypeFace.valueOf("_" + fontName);
        }
    }

    public static Typeface getTypeFaceFromAttr(Context context, AttributeSet attrs, int defaultFont) {
        int fontName = defaultFont;
        if (attrs != null) {

            int[] requireAttributeValues = new int[]{R.attr.custom_font};

            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    requireAttributeValues,
                    0,
                    0
            );
            //0 = index
            fontName = a.getInt(0, defaultFont);

            a.recycle();
        }

        return TypeFaceHelper.CustomTypeFace.fromInt(fontName).asTypeface(context);
    }
}
