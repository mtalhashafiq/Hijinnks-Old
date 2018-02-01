package com.example.mtalh.hijinnks;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;


/**
 * Created by CP on 1/9/2018.
 */

public class Custome_EditText_Varela_Regular extends android.support.v7.widget.AppCompatEditText{


    public Custome_EditText_Varela_Regular(Context context) {
        super(context);
        applyCustome_fonts(context);
    }

    public Custome_EditText_Varela_Regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustome_fonts(context);
    }

    public Custome_EditText_Varela_Regular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustome_fonts(context);
    }

    private void applyCustome_fonts(Context context){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "VarelaRound-Regular.ttf");
        setTypeface(tf ,1);



    }
}
