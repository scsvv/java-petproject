package ua.cours.classes.task;

import ua.cours.classes.Configuration;
import ua.cours.classes.utils.Timer;

import java.util.ArrayList;

public class Queue {
    private final ArrayList<Process> ReadyQueue;
    private final ArrayList<Process> rejectedQueue;
    private final ArrayList<Process> doneProcesses;
    private int PID;

    public Queue()
    {
        this.ReadyQueue = new ArrayList<>();
        this.rejectedQueue = new ArrayList<>();
        this.doneProcesses = new ArrayList<>();
        this.PID=1;
    }

    public void Add(final int PCount)
    {
        for (int i = 0; i < PCount; i++) {
            Process p = new Process(this.PID);
            this.PID++;
            addProcess(p);
        }
    }

    public void addProcess(Process p)
    {
        if (p.getStatus() == Status.Waiting)
            p.setBursTime(p.getBursTime()+(int)(p.getBursTime()/ Configuration.OLD_NEW_PROCESS_TIME));

        if(MemoryScheduler.fillMB(p)) {
            this.ReadyQueue.add(p);
        }
        else {
            if (p.getStatus() == Status.Waiting)
                p.setStatus(Status.Canceled);
            else
                p.setStatus(Status.Rejected);

            rejectedQueue.add(p);
        }
    }

    public void Remove(Process process)
    {
        ReadyQueue.remove(process);
        MemoryScheduler.releaseMB(process);
    }

    public void cancelOutdated()
    {
            for (int i = ReadyQueue.size()-1; i>=0; i--)
                if (Timer.getTick() >= ReadyQueue.get(i).getTimeIn() * Configuration.REMOVE_PROCESS_MULTIPLIER)
                    cancelProcess(ReadyQueue.get(i));
    }

    private void cancelProcess(Process process)
    {
        Remove(process);
        process.setStatus(Status.Canceled);
        rejectedQueue.add(process);
    }
    public Process getNextProcess()
    {
        ReadyQueue.sort(Process.byTime);
        if(ReadyQueue.size()!=0) {
            Process _tmp = ReadyQueue.get(0);
            this.Remove(_tmp);
            _tmp.setStatus(Status.Running);
            return _tmp;
        }
        return null;
    }

    public void finishProcess(Process process)
    {
        doneProcesses.add(process);
    }

    public ArrayList<Process> getDoneProcesses() {
        return doneProcesses;
    }

    public ArrayList<Process> getReadyQueue() {
        return ReadyQueue;
    }

    public ArrayList<Process> getRejectedQueue() {
        return rejectedQueue;
    }

}
