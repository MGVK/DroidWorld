package ru.mgvk.droidworld;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Michael_Admin on 12.06.2016.
 */
class Memory extends ImageView {

    Memory memory;
    MainActivity mainActivity;
    int FPS = 60;
    double finalY = 0;

    public Memory(Context context, double X, double Y, double finalY) {
        super(context);
        mainActivity = (MainActivity) context;
        memory = this;
        this.setX((float) X);
        this.setY((float) Y);
        this.finalY = finalY;
        this.setBackgroundResource(R.drawable.memory);
        this.setLayoutParams(new RelativeLayout.LayoutParams(60,30));
    }

    Memory startMooving() {
        new MoovingThread().start();
        return this;
    }
    class MoovingThread extends Thread {

        @Override
        public void run() {
            super.run();

            final int step = (int) (Math.random()*5 +2);

            while (memory.getY() < finalY) {

                try {
                    sleep(1000 / FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        memory.setY(memory.getY() + step);
                    }
                });
            }

            mainActivity.droidlist.get(0).addMovingAim(memory);

        }
    }

}