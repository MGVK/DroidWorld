package ru.mgvk.droidworld;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Michael_Admin on 12.06.2016.
 */
public class Environment {


    Context context;
    MainActivity mainActivity;
    private boolean generatingBeer=false;


    Environment(Context context) {
        this.context = context;
        mainActivity = (MainActivity) context;

    }

    public void setGeneratingBeer(boolean generatingBeer) {
        if(this.generatingBeer = generatingBeer){
            try {
                new GeneratingBeerThread().start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    class GeneratingBeerThread extends Thread {
        @Override
        public void run() {
            generatingBeer = true;
            while (generatingBeer) {
                super.run();
                
                long timetosleep = (long) (1000*(Math.random()*100+20));
                Log.d("Environment","через "+ timetosleep/1000 +" cекунд...");
                try {
                    sleep(timetosleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int memoriessCount = (int) (Math.random() * 6 + 3);

                Log.d("Environmetn","ПАААМЯЯЯТЬ "+memoriessCount);

                //// TODO: 12.06.2016 сделать beerList

                ArrayList<Memory> memoryList = new ArrayList<>();

                for (int i = 0; i < memoriessCount; i++) {
                    memoryList.add(new Memory(context, (Math.random() * mainActivity.width), -100,Math.random() * mainActivity.height));
                }

                for (final Memory memory :
                        memoryList) {
                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainActivity.mainLayout.addView(memory.startMooving());
                        }
                    });
                }

            }
        }
    }


}
