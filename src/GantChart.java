import java.util.*;

public class GantChart {
    private Sorter sorter = new Sorter();
    private Double avgWaitingTime, avgTurnAroundTime, throughput;
    public static void main(String[] args){
        GantChart gantChart = new GantChart();
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of Processes: ");
        n = sc.nextInt();
        Process[] processes = new Process[n];

        System.out.println("...Enter the process ID...");
        for (int i = 0; i < n; i++) {
            int id;
            double arrival,duration, priority;
            System.out.println("...Process " + (i + 1) + "...");
            System.out.println("Enter Process Id: ");
            id = sc.nextInt();
            System.out.println("Enter Arrival Time: ");
            arrival = sc.nextInt();
            System.out.println("Enter Duration Time: ");
            duration = sc.nextInt();
            System.out.println("Enter Process Priority: ");
            priority = sc.nextInt();
            processes[i] = new Process(duration,arrival, priority, id);
        }

        //OR comment out the previous section and input your processes here
        //arguments: duration, arrival, priority, id
//        Process [] processes = {
//                new Process(16.0,7.0,0.0, 0),
//                new Process(17.0,19.0,0.0, 1),
//                new Process(18.0,0.0,0.0, 2),
//                new Process(5.0,6.0,0.0 , 3),
//                new Process(15.0,8.0,0.0 , 4)
//        };

        gantChart.preEmptiveShortestRemainingTimeFirst(processes);
    }


    //Prints out a table of processes scheduled with FCFS
    public void firstComeFirstServe(Process [] processes) {
        System.out.println("RUNNING FIRST COME FIRST SERVE");
        int n = processes.length;
        sorter.sortByArrival(processes);


        processes[0].waitingTime = 0.0;
        processes[0].start = 0.0;
        processes[0].finish = processes[0].getDuration();
        processes[0].turnaroundTime = processes[0].finish - processes[0].start;

        for (int i = 1; i < n; i++) {
            processes[i].finish = processes[i-1].finish + processes[i].getDuration();
            processes[i].waitingTime = processes[i - 1].finish - processes[i].getArrival();
            processes[i].turnaroundTime =  processes[i].finish - processes[i].getArrival();

        }

        print(processes);
    }

    //Prints out a table of processes scheduled with SJF
    public void shortestJobFirst(Process[] processes) {
        System.out.println("RUNNING SHORTEST JOB FIRST");
        int n = processes.length;
        sorter.sortByDuration(processes);

        processes[0].waitingTime = 0.0;
        processes[0].start = 0.0;
        processes[0].finish = processes[0].getDuration();
        processes[0].turnaroundTime = processes[0].finish - processes[0].start;
        System.out.println("Now executing: P" + processes[0].getId() + " Finish time is: " + processes[0].finish);

        double currentTime;
        int val = 0;
        for (int i = 1; i < n; i++) {
            currentTime = processes[i - 1].finish;
            double low = processes[i].getDuration();

            //find process with shortest duration and has already arrived
            for (int j = i; j < n; j++) {
                if (currentTime >= processes[j].getArrival() && low >= processes[j].getDuration()) {
                    low = processes[j].getDuration();
                    val = j;
                }
            }
            processes[val].finish = currentTime + processes[val].getDuration();
            processes[val].waitingTime = processes[i-1].finish - processes[val].getArrival();
            processes[val].turnaroundTime = processes[val].finish - processes[val].getArrival();
            System.out.println("Now executing: P" + processes[val].getId() +
                    " Start time is: " + currentTime + " Finish time is: "
                    + processes[val].finish +  " previous process: P" + processes[i-1].getId());

            //swap process i with process val
            Process temp = processes[val];
            processes[val] = processes[i];
            processes[i] = temp;
        }
        print(processes);

    }

    public void preEmptiveShortestRemainingTimeFirst(Process[] processes) {
        System.out.println("RUNNING PRE-EMPTIVE SHORTEST JOB FIRST SHORTEST REMAINING TIME FIRST (SRTF)");

        double currentTime = 0;
        sorter.sortByArrival(processes);
        ArrayList<Process> processArrayList = new ArrayList<>(Arrays.asList(processes));
        ArrayList<ProcessTableItem> table = new ArrayList<>();
        ArrayList<Process> readyQueue = new ArrayList<>();

        Process current = processArrayList.get(0);
        boolean done = true;
        boolean interrupted = false;

        Iterator<Process> it = processArrayList.iterator();
        while(it.hasNext()){
            //if a process finished in the previous iteration, choose a new process from the ready queue
            if(done){
                for (int j = 0; j < readyQueue.size(); j++) {
                    if(readyQueue.get(j).timeLeft < current.timeLeft){
                        current = readyQueue.get(j);
                    }
                }
                readyQueue.remove(current);
            }

            for (int i = 0; i < processArrayList.size() ; i++) {
                if(processArrayList.get(i).getId() != current.getId()){
                    //process just arrived
                    if (currentTime == processArrayList.get(i).getArrival()) {
                        if(processArrayList.get(i).timeLeft < current.timeLeft){
                            //Any process that gets replaced at the beginning doesn't count as interrupted
                            if(currentTime != 0){
                                interrupted = true;
                            }
                            readyQueue.add(current);
                            current = processArrayList.get(i);
                        }
                        else{
                            //without the if statement the first process will be placed in the queue twice
                            if(!readyQueue.contains(processArrayList.get(i))){
                                readyQueue.add(processArrayList.get(i));
                            }
                        }
                    }
                }
            }

            if(interrupted){
                //process interrupted
                table.get(table.size()-1).end = currentTime;
            }
            if(done || interrupted){
                //process started or resumed
                table.add(new ProcessTableItem(current.getId(), currentTime));
                done = false;
                interrupted = false;
            }

            current.timeLeft--;
            currentTime++;
            if(current.timeLeft<=0){
                table.get(table.size()-1).end = currentTime;
                processArrayList.remove(current);
                if(processArrayList.size()>0){
                    current = processArrayList.get(0);
                }
                done = true;
            }
        }

        printTable(table, processes);
        print(processes);
    }
    //Prints out a table of processes scheduled with Non-pre-emptive Priority Scheduling
    public void nonPreEmptivePriority(Process[] processes) {
        System.out.println("RUNNING NON-PRE-EMPTIVE PRIORITY(lower number higher priority)");
        int n = processes.length;
        sorter.sortByPriority(processes);

        processes[0].waitingTime = 0.0;
        processes[0].start = 0.0;
        processes[0].finish = processes[0].getDuration();
        processes[0].turnaroundTime = processes[0].finish - processes[0].start;
        System.out.println("Now executing: P" + processes[0].getId() + " Finish time is: " + processes[0].finish);

        double currentTime;
        int val = 0;
        for (int i = 1; i < n; i++) {
            currentTime = processes[i - 1].finish;
            double low = processes[i].getDuration();

            //find process with shortest duration and has already arrived
            for (int j = i; j < n; j++) {
                if (currentTime >= processes[j].getArrival() && low >= processes[j].getDuration()) {
                    low = processes[j].getDuration();
                    val = j;
                }
            }
            processes[val].finish = currentTime + processes[val].getDuration();
            processes[val].waitingTime = processes[i-1].finish - processes[val].getArrival();
            processes[val].turnaroundTime = processes[val].finish - processes[val].getArrival();
            System.out.println("Now executing: P" + processes[val].getId() +
                    " Start time is: " + currentTime + " Finish time is: "
                    + processes[val].finish +  " previous process: P" + processes[i-1].getId());

            //swap process i with process val
            Process temp = processes[val];
            processes[val] = processes[i];
            processes[i] = temp;
        }
        print(processes);

    }

    public void preEmptivePriority(Process[] processes) {
        System.out.println("RUNNING PRE-EMPTIVE PRIORITY(lower number higher priority)");

        double currentTime = 0;

        ArrayList<Process> processArrayList = new ArrayList<>(Arrays.asList(processes));
        ArrayList<ProcessTableItem> table = new ArrayList<>();
        ArrayList<Process> readyQueue = new ArrayList<>();

        Process current = processArrayList.get(0);
        boolean done = true;
        boolean interrupted = false;

        Iterator<Process> it = processArrayList.iterator();
        while(it.hasNext()){
            //if a process finished in the previous iteration, choose a new process from the ready queue
            if(done){
                for (int j = 0; j < readyQueue.size(); j++) {
                    if(readyQueue.get(j).getPriority() < current.getPriority()){
                        current = readyQueue.get(j);
                    }
                }
                readyQueue.remove(current);
            }

            for (int i = 0; i < processArrayList.size() ; i++) {
                //process just arrived
                if (currentTime == processArrayList.get(i).getArrival()) {
                    if(processArrayList.get(i).getPriority() < current.getPriority()){
                        //Any process that gets replaced at the beginning doesn't count as interrupted
                        if(currentTime != 0){
                            interrupted = true;
                        }
                        readyQueue.add(current);
                        current = processArrayList.get(i);
                    }
                    else{
                        //without the if statement the first process will be placed in the queue twice
                        if(!readyQueue.contains(processArrayList.get(i))){
                            readyQueue.add(processArrayList.get(i));
                        }
                    }
                }
            }

            if(interrupted){
                //process interrupted
                table.get(table.size()-1).end = currentTime;
            }
            if(done || interrupted){
                //process started or resumed
                table.add(new ProcessTableItem(current.getId(), currentTime));
                done = false;
                interrupted = false;
            }

            current.timeLeft--;
            currentTime++;
            if(current.timeLeft<=0){
               //process finished executing
                table.get(table.size()-1).end = currentTime;
                processArrayList.remove(current);
                if(processArrayList.size()>0){
                    current = processArrayList.get(0);
                }
                done = true;
            }
        }

        printTable(table, processes);
        print(processes);
    }

    public void roundRobin(Process[] processes, int q) {
        System.out.println("RUNNING ROUND ROBIN");


        sorter.sortByArrival(processes);

        ArrayList<Process> processArrayList = new ArrayList<>(Arrays.asList(processes));
        ArrayList<ProcessTableItem> table = new ArrayList<>();
        ArrayList<Process> queue = new ArrayList<>();

        double currentTime = 0;
        processArrayList.forEach(process -> {
            if(process.getArrival() == 0){
                queue.add(process);
            }
        });

        Iterator<Process> it = queue.iterator();
        boolean done = false;
        double executionTime = 0;
        while(it.hasNext()) {

            if(executionTime%q == 0 || done) {
                table.add(new ProcessTableItem(queue.get(0).getId(), currentTime));
                done = false;
            }

            queue.get(0).timeLeft--;
            executionTime++;
            currentTime++;

            for (int i = 0; i < processArrayList.size() ; i++) {
                //process just arrived
                if (currentTime == processArrayList.get(i).getArrival() && !queue.contains(processArrayList.get(i))) {
                    queue.add(processArrayList.get(i));
                }
            }

            if(executionTime%q == 0 || queue.get(0).timeLeft == 0){

                table.get(table.size()-1).end = currentTime;

                if(queue.get(0).timeLeft > 0){
                    queue.add(queue.get(0));
                }

                queue.remove(0);
                done = true;
                executionTime=0;


            }
        }

        printTable(table, processes);
        print(processes);
    }


    private void printTable(ArrayList<ProcessTableItem> table, Process[] processes){
        System.out.println("Gant Chart:");
        System.out.println("-------------------------------------------------------");
        System.out.printf("%10s %20s %20s", "Process ID", "Start Time", "End Time");
        System.out.println();
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < table.size(); i++) {
            System.out.println(table.get(i));
            System.out.println("-------------------------------------------------------");
            for (Process process : processes) {
                if(process.getId() == table.get(i).processId){
                    if(process.start == null){
                        process.start = process.getArrival();
                    }
                    process.waitingTime += table.get(i).start - process.start;
                    process.turnaroundTime = table.get(i).end - process.getArrival();
                    process.start = table.get(i).end; //just to know where it last stopped
                    process.finish = table.get(i).end; //just to calculate throughput
                }
            }
        }
        System.out.println();
        System.out.println("*************************************************************");
        System.out.println();
    }
    private void print(Process [] processes){
        Double totalWaitingTime = 0.0, totalTurnAroundTime = 0.0;
        int n = processes.length;

        System.out.println("-------------------------------------------------------");
        System.out.printf("%10s %20s %20s", "Process ID", "Waiting Time", "Turnaround Time");
        System.out.println();
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i]);
            System.out.println("-------------------------------------------------------");
            totalWaitingTime += processes[i].waitingTime;
            totalTurnAroundTime += processes[i].turnaroundTime;
        }

         avgWaitingTime = totalWaitingTime / n;
         avgTurnAroundTime = totalTurnAroundTime / n;
         throughput = n / processes[processes.length-1].finish;

        System.out.println("Total waiting time: " + totalWaitingTime);
        System.out.println("Total turnaround time: " + totalTurnAroundTime);
        System.out.println();
        System.out.println("Average waiting time: " + avgWaitingTime);
        System.out.println("Average turnaround time: " + avgTurnAroundTime);

        System.out.println("Throughput: " + throughput);
    }

    public Double getAvgTurnAroundTime() {
        return avgTurnAroundTime;
    }

    public Double getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public Double getThroughput() {
        return throughput;
    }
}
