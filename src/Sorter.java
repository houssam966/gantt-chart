public class Sorter {

    public void sortByArrival(Process [] processes){
        //bubble sort
        for (int i = 0; i < processes.length - 1; i++) {
            for (int j = 0; j < processes.length - i - 1; j++) {
                if (processes[j].getArrival() > processes[j + 1].getArrival()) {
                    //swap
                    Process temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;
                }
            }
        }
    }

    public void sortByPriority(Process [] processes){
        sortByArrival(processes);
        int n = processes.length;

        int indexOfFirstElement = 0;
        Process first = processes[0];
        //in reverse so in case there is a clash it favours lower id
        for (int i = n -1; i > 0; i--) {
            //find process with shortest duration and at time 0
            if (processes[i].getArrival() <= first.getArrival() && first.getPriority() >= processes[i].getPriority()) {
                first = processes[i];
                indexOfFirstElement = i;
            }
        }

        //swap the current first element with the new one
        Process temp = processes[indexOfFirstElement];
        processes[indexOfFirstElement] = processes[0];
        processes[0] = temp;
    }

    public void sortByDuration(Process [] processes){
        sortByArrival(processes);
        int n = processes.length;

        int indexOfFirstElement = 0;
        Process first = processes[0];
        //in reverse so in case there is a clash it favours lower id
        for (int i = n -1; i > 0; i--) {
            //find process with shortest duration and at time 0
            if (processes[i].getArrival() <= first.getArrival() && first.getDuration() >= processes[i].getDuration()) {
                first = processes[i];
                indexOfFirstElement = i;
            }
        }

        //swap the current first element with the new one
        Process temp = processes[indexOfFirstElement];
        processes[indexOfFirstElement] = processes[0];
        processes[0] = temp;
    }
}
