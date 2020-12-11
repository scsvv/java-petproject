package ua.cours.classes.task;

import java.util.Comparator;

public class MemoryBlock {
    int start;
    int end;
    Process process;

    public static Comparator<MemoryBlock> byEnd = Comparator.comparingInt(o -> o.end);

    public MemoryBlock(int start, int end, Process process) {
        this.start=start;
        this.end=end;
        this.process=process;
    }
}
