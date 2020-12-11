package ua.cours.classes.IFace;

import ua.cours.classes.task.GlobalScheduler;

public class ThreadStarter implements Runnable {
    public static GlobalScheduler sc;

    @Override
    public void run() {
        sc = new GlobalScheduler();
        sc.Start();

    }

}
