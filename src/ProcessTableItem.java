public class ProcessTableItem {
    public int processId;
    public double start;
    public double end;

    public ProcessTableItem(int processId, double start, double end){
        this.processId = processId;
        this.start = start;
        this.end = end;
    }

    public ProcessTableItem(int processId, double start){
        this.processId = processId;
        this.start = start;
    }

    @Override
    public String toString() {
        return String.format("%5s %20s %20s", "P"+ processId, start, end);
    }
}
