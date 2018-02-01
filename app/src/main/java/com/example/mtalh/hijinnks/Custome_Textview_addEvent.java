package com.example.mtalh.hijinnks;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by CP on 1/4/2018.
 */

public class Custome_Textview_addEvent extends android.support.v7.widget.AppCompatEditText{


    public Custome_Textview_addEvent(Context context) {
        super(context);
        applyCustome_fonts(context);
    }

    public Custome_Textview_addEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustome_fonts(context);
    }

    public Custome_Textview_addEvent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustome_fonts(context);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused){
            setHintTextColor(Color.parseColor("#1f4ba4"));
            setTextColor(Color.parseColor("#1f4ba4"));
        }
        else {
            setHintTextColor(Color.parseColor("#e0e0e0"));
            setTextColor(Color.parseColor("#e0e0e0"));
        }

    }
    private void applyCustome_fonts(Context context){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "VarelaRound-Regular.ttf");
        setTypeface(tf ,1);



    }
}
