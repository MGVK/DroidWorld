package ru.mgvk.droidworld;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by Michael_Admin on 11.06.2016.
 */
public class Droid extends ImageView {

    Context context;

    MainActivity mainActivity;
    Droid droid;
    private boolean works = false;

    ArrayList<Object> movingAims = new ArrayList<>();

    Environment environment;


    public Droid(Context context) {
        super(context);
        this.context = context;
        droid = this;
        mainActivity = (MainActivity) context;
        this.setBackgroundResource(R.drawable.droid);
        this.setLayoutParams(new RelativeLayout.LayoutParams(120, 100));

        new MoovingThread().start();
    }

    void addMovingAim(Object object) {
        movingAims.add(object);
    }


    void stopDroid() {
        works = false;
    }

    class MoovingThread extends Thread {

        int FPS = 60;

        @Override
        public void run() {
            works = true;
            boolean aims=false;
            while (works) {

                try {
                    sleep((long) (1000 * (Math.random() * 7)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int newX, newY;

                if (movingAims.size() != 0) {
                    aims = true;
                    newX = (int) ((View) movingAims.get(0)).getX();
                    newY = (int) ((View) movingAims.get(0)).getY();
                } else {
                    aims = false;
                    newX = (int) (Math.random() * (mainActivity.width - 100));
                    newY = (int) (Math.random() * (mainActivity.height - 100));
                }

                final int directionX = (int) Math.signum(newX - droid.getX());
                final int directionY = (int) Math.signum(newY - droid.getY());
                Log.d("DroidMooving", "mooving to " + newX + " " + newY);

                int step = (int) (Math.random() * 3 + 2);
                final double pixelsPerFrameX =  (step * Math.abs(newX - droid.getX()) /
                        (double) (Math.abs(newX - droid.getX()) + Math.abs(newY - droid.getY())));
                final double pixelsPerFrameY =  (step * Math.abs(newY - droid.getY()) /
                        (double) (Math.abs(newX - droid.getX()) + Math.abs(newY - droid.getY())));

                while (Math.abs(newX - droid.getX()) > 10 || Math.abs(newY - droid.getY()) > 10) {

                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            droid.setX((float) (droid.getX() + directionX * pixelsPerFrameX));
                            droid.setY((float) (droid.getY() + directionY * pixelsPerFrameY));
//                            Log.i("droid",droid.getX()+" "+droid.getY());

                        }
                    });
                    try {
                        sleep(1000 / FPS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

               if(aims){
                   mainActivity.runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           ((RelativeLayout)((View)movingAims.get(0)).getParent()).removeView((View) movingAims.get(0));
                           movingAims.remove(0);
                       }
                   });

               }

            }


        }
    }


    void environmentAction(Object o) {


    }


}
