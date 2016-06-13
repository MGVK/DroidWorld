package ru.mgvk.droidworld;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    int width=0,height=0;

    RelativeLayout mainLayout;

    Context context;
    Environment environment;

    ArrayList<Droid> droidlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_UP){
                    Flag flag = new Flag(context,event.getX(),event.getY());
                    mainLayout.addView(flag);
                    for (Droid droid : droidlist) {
                        droid.addMovingAim(flag);
                    }
                }

                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        environment = new Environment(context);
        environment.setGeneratingBeer(true);
        addDroid();

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        width = mainLayout.getWidth();
        height = mainLayout.getHeight();
        Log.d("size",width+" "+height);
    }




    void addDroid(){
        Droid droid = new Droid(context);
        mainLayout.addView(droid);
        droidlist.add(droid);
    }


}
