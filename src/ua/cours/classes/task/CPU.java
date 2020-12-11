package ua.cours.classes.task;

import ua.cours.classes.utils.TimerListener;

public class CPU implements TimerListener
{
    Core[] cores;

    public CPU(final int number)
    {
        this.cores = new Core[number];

        for(int i = 0; i<number; i++)
            cores[i]=new Core();
    }
    public Core[] getCores(){return cores;}

    public void setCoreJob(int coreNumb, Process process)
    {
        if(process!=null) {
            cores[coreNumb].currentProcess = process;
            cores[coreNumb].isFree = false;
        }
    }

public int getFreeCore()
{
    for (int i =0; i<cores.length; i++)
        if(cores[i].isFree)
            return i;
    return -1;
}




    @Override
    public void tickEvent() {
        for(Core core : cores)
        {
            if(!core.isFree) {
                Process _tmProcess = core.getCurrentProcess();

                _tmProcess.setBursTime(_tmProcess.getBursTime() + 1);
                if (_tmProcess.getBursTime() == _tmProcess.getTickWorks()) {
                    GlobalScheduler.PDone(core.currentProcess);
                    core.currentProcess = null;
                    core.isFree = true;
                }
            }
        }
    }
}
