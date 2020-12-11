package ua.cours.classes.IFace;

import ua.cours.classes.task.Process;
import ua.cours.classes.task.Status;

public class CoreInfo {
    private String name;
    private int id;
    private Status status;
    private int timeIn;
    private int bursTime;

    public CoreInfo(String core, Process process) {
        this.name = core;

        if(process!=null) {
            this.id = process.getId();
            this.status = process.getStatus();
            this.timeIn = process.getTimeIn();
            this.bursTime = process.getBursTime();
            this.timeIn = process.getTimeIn();
        }
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public Status getStatus() {
        return status;
    }
    public int getTimeIn() {
        return timeIn;
    }
    public int getBursTime() {
        return bursTime;
    }
}
