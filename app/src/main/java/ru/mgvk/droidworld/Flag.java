package ru.mgvk.droidworld;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Michael_Admin on 12.06.2016.
 */
public class Flag extends ImageView {
    public Flag(Context context, float X, float Y) {
        super(context);
        this.setX(X);
        this.setLayoutParams(new RelativeLayout.LayoutParams(30,30));
        this.setY(Y-this.getHeight());
        this.setBackgroundResource(R.drawable.flag);
    }
}
