public class Process {
    private int id;
    private Double arrival,duration,priority;
    public Double waitingTime = 0.0, turnaroundTime = 0.0, start, finish, timeLeft, interruptedTime;
    public boolean interrupted = false;

    public Process(Double duration, Double arrival, Double priority, int id){
        this.duration = duration;
        this.arrival = arrival;
        this.priority = priority;
        this.id = id;
        timeLeft = duration;
    }

    public Double getArrival() {
        return arrival;
    }

    public Double getDuration() {
        return duration;
    }

    public Double getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%5s %20s %20s", "P"+ id, waitingTime, turnaroundTime);
    }
}
