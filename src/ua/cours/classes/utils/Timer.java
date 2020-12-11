package ua.cours.classes.utils;

import ua.cours.classes.Configuration;

import java.util.ArrayList;
import java.util.TimerTask;

public class Timer extends TimerTask {
    ArrayList<TimerListener> listenersList = new ArrayList<>();

    private static int tick;
    public static int getTick() {
        return tick;
    }
    public static void TickUP(){
        tick++;
    }
//    Queue q;
//    /////////////////////////
//
//
//    public Queue getQ() {
//        return q;
//    }
//
//    public void setQ(Queue q) {
//        this.q = q;
//    }
//
//    ////////////////////
    public static void clearTime(){tick =0;}

    @Override
    public void run() {
        while (true)
        {
            try {
                Thread.sleep(1000, Configuration.TICK_PER_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TickUP();

            for(TimerListener listener : listenersList)
                listener.tickEvent();

        }
    }

    public void addListener(TimerListener listener)
    {
        listenersList.add(listener);
    }
}
