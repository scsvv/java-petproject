package ua.cours.classes.task;

import ua.cours.classes.Configuration;
import ua.cours.classes.utils.Utils;
import ua.cours.classes.utils.Timer;

import java.util.Comparator;

public class Process {
    private final int id;        //after create
    private final String name;    //rand
    private Status status;    //rand + on work
    private  int tickWorks;       //rand
    private final int memory;     //rand
    private final int timeIn;     //after create
    private int bursTime;   //on work

    public Process(int id) {
        this.id = id;
        this.name = "P_"+this.id+"_"+ Utils.getRandString(Utils.getRandInt(Configuration.MIN_PROCESS_NAME_L,Configuration.MAX_PROCESS_NAME_L));
        this.memory = Utils.getRandInt(Configuration.MIN_PROCESS_MEMORY_REQUIRED, Configuration.MAX_PROCESS_MEMORY_REQUIRED);
        this.tickWorks = Utils.getRandInt(Configuration.MIN_PROCESS_TIME_REQUIRED, Configuration.MAX_PROCESS_TIME_REQUIRED);
        this.timeIn = Timer.getTick();
        this.bursTime=0;
        this.status = Status.Ready;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setBursTime(int bursTime) {
        this.bursTime = bursTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTickWorks() {
        return tickWorks;
    }

    public int getMemory() {
        return memory;
    }

    public int getTimeIn() {
        return timeIn;
    }

    public int getBursTime() {
        return bursTime;
    }

    public Status getStatus(){return status;}


    public static Comparator<Process> byTime = Comparator.comparingInt(o -> o.tickWorks);

}
