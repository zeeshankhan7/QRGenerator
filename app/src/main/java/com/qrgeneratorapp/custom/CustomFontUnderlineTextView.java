package com.qrgeneratorapp.custom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.StateSet;

import com.qrgeneratorapp.R;


/**
 * Created by zeeshan on 7/15/2017.
 */

public class CustomFontUnderlineTextView extends CustomFontTextView {
    public CustomFontUnderlineTextView(Context context) {
        super(context);
    }

    public CustomFontUnderlineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }


    public CustomFontUnderlineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        int underlineWidth = getContext().getResources().getDimensionPixelOffset(R.dimen.underline_width);
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CustomFontUnderlineTextView, 0, 0);
        ColorStateList colorState = a.getColorStateList(R.styleable.CustomFontUnderlineTextView_underlineColor);
        if (colorState == null) {
            colorState = getTextColors();
        }
        int color = colorState.getDefaultColor();

        int underLinePadding = a.getDimensionPixelOffset(R.styleable.CustomFontUnderlineTextView_underlinePadding, 4);
        underlineWidth = a.getDimensionPixelOffset(R.styleable.CustomFontUnderlineTextView_underlineWidth, underlineWidth);

        int touchColor = ContextCompat.getColor(getContext(), android.R.color.transparent);
        ColorStateList touchColorState = a.getColorStateList(R.styleable.CustomFontUnderlineTextView_touchColor);
        if (touchColorState != null) {
            touchColor = touchColorState.getDefaultColor();
        }

        a.recycle();


        int inset = (-1) * underlineWidth - 1;

        ShapeDrawable underlineDrawable = new ShapeDrawable(new RectShape());
        underlineDrawable.getPaint().setColor(color);
        underlineDrawable.getPaint().setStyle(Paint.Style.STROKE);
        underlineDrawable.getPaint().setStrokeWidth(underlineWidth);
        underlineDrawable.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), underLinePadding);

        LayerDrawable drawable = new LayerDrawable(new Drawable[]{underlineDrawable});
        drawable.setLayerInset(0, inset, inset, inset, 0);


        ShapeDrawable underlineDrawable1 = new ShapeDrawable(new RectShape());
        underlineDrawable1.getPaint().setColor(color);
        underlineDrawable1.getPaint().setStyle(Paint.Style.STROKE);
        underlineDrawable1.getPaint().setStrokeWidth(underlineWidth);
        underlineDrawable1.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), underLinePadding);


        ShapeDrawable solid = new ShapeDrawable(new RectShape());
        solid.getPaint().setColor(touchColor);
        solid.getPaint().setStrokeWidth(underlineWidth);

        LayerDrawable pressed = new LayerDrawable(new Drawable[]{solid, underlineDrawable1});
        pressed.setLayerInset(1, inset, inset, inset, 0);

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
        stateListDrawable.addState(StateSet.WILD_CARD, drawable);


        setBackground(stateListDrawable);
    }
}