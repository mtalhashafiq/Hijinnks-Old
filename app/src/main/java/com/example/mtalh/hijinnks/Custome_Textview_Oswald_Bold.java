package com.example.mtalh.hijinnks;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;


/**
 * Created by CP on 1/9/2018.
 */

public class Custome_Textview_Oswald_Bold extends android.support.v7.widget.AppCompatTextView{

    public Custome_Textview_Oswald_Bold(Context context) {
        super(context);
        applyCustome_fonts(context);
    }

    public Custome_Textview_Oswald_Bold(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustome_fonts(context);
    }

    public Custome_Textview_Oswald_Bold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustome_fonts(context);
    }

    private void applyCustome_fonts(Context context){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Oswald_Bold.ttf");
        setTypeface(tf ,1);



    }
}
