import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GantChartTest {

    @Test
    public void SJFSLectureWorks() {
        GantChart gantChart = new GantChart();
        //from lecture slides
        Process [] processes = {
                new Process(5.0,0.0,0.0 , 1),
                new Process(4.0,0.0,0.0, 2),
                new Process(2.0,1.0,0.0, 3),
                new Process(3.0,10.0,0.0 , 4)
        };
        gantChart.shortestJobFirst(processes);
        //check order
        assertEquals(processes[0].getId(),2);
        assertEquals(processes[1].getId(),3);
        assertEquals(processes[2].getId(),1);
        assertEquals(processes[3].getId(),4);
        //check waiting times
        assertEquals(processes[0].waitingTime,0.0);
        assertEquals(processes[1].waitingTime,3.0);
        assertEquals(processes[2].waitingTime,6.0);
        assertEquals(processes[3].waitingTime,1.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,4.0);
        assertEquals(processes[1].turnaroundTime,5.0);
        assertEquals(processes[2].turnaroundTime,11.0);
        assertEquals(processes[3].turnaroundTime,4.0);

    }
    @Test
    public void SJFTutorialQ3Works() {
        GantChart gantChart = new GantChart();

        Process [] processes = {
                new Process(8.0,0.0,0.0 , 1),
                new Process(4.0,0.4,0.0, 2),
                new Process(1.0,1.0,0.0, 3),

        };
        gantChart.shortestJobFirst(processes);
        //check order
        assertEquals(processes[0].getId(),1);
        assertEquals(processes[1].getId(),3);
        assertEquals(processes[2].getId(),2);
        //check waiting times
        assertEquals(processes[0].waitingTime,0.0);
        assertEquals(processes[1].waitingTime,7.0);
        assertEquals(processes[2].waitingTime,8.6);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,8.0);
        assertEquals(processes[1].turnaroundTime,8.0);
        assertEquals(processes[2].turnaroundTime,12.6);
    }
    @Test
    public void SJFTutorialQ9Works() {
        GantChart gantChart = new GantChart();

        Process [] processes = {
                new Process(10.0,0.0,3.0 , 1),
                new Process(1.0,0.0,1.0, 2),
                new Process(2.0,0.0,3.0, 3),
                new Process(1.0,0.0,4.0, 4),
                new Process(5.0,0.0,2.0, 5),

        };
        gantChart.shortestJobFirst(processes);
        //check order
        assertEquals(processes[0].getId(),2);
        assertEquals(processes[1].getId(),4);
        assertEquals(processes[2].getId(),3);
        assertEquals(processes[3].getId(),5);
        assertEquals(processes[4].getId(),1);
        //check waiting times
        assertEquals(processes[0].waitingTime,0.0);
        assertEquals(processes[1].waitingTime,1.0);
        assertEquals(processes[2].waitingTime,2.0);
        assertEquals(processes[3].waitingTime,4.0);
        assertEquals(processes[4].waitingTime,9.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,1.0);
        assertEquals(processes[1].turnaroundTime,2.0);
        assertEquals(processes[2].turnaroundTime,4.0);
        assertEquals(processes[3].turnaroundTime,9.0);
        assertEquals(processes[4].turnaroundTime,19.0);
    }
    @Test
    public void FCFSWorks() {
        GantChart gantChart = new GantChart();

        Process [] processes = {
                new Process(3.0,0.0,0.0 , 0),
                new Process(1.0,4.0,4.0, 1),
                new Process(2.0,3.0,3.0, 2),
                new Process(9.0,2.0,2.0, 3),
                new Process(1.0,9.0,9.0, 4),

        };
        gantChart.firstComeFirstServe(processes);
        //check order
        assertEquals(processes[0].getId(),0);
        assertEquals(processes[1].getId(),3);
        assertEquals(processes[2].getId(),2);
        assertEquals(processes[3].getId(),1);
        assertEquals(processes[4].getId(),4);
        //check waiting times
        assertEquals(processes[0].waitingTime,0.0);
        assertEquals(processes[1].waitingTime,1.0);
        assertEquals(processes[2].waitingTime,9.0);
        assertEquals(processes[3].waitingTime,10.0);
        assertEquals(processes[4].waitingTime,6.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,3.0);
        assertEquals(processes[1].turnaroundTime,10.0);
        assertEquals(processes[2].turnaroundTime,11.0);
        assertEquals(processes[3].turnaroundTime,11.0);
        assertEquals(processes[4].turnaroundTime,7.0);

        assertEquals(gantChart.getAvgTurnAroundTime(),8.4);
    }

    @Test
    public void FCFSLectureWorks() {
        GantChart gantChart = new GantChart();
        //from lecture slides
        Process [] processes = {
                new Process(5.0,4.0,0.0 , 1),
                new Process(3.0,0.0,0.0, 2),
                new Process(4.0,2.0,0.0, 3),
                new Process(2.0,12.0,0.0, 4),

        };
        gantChart.firstComeFirstServe(processes);
        //check order
        assertEquals(processes[0].getId(),2);
        assertEquals(processes[1].getId(),3);
        assertEquals(processes[2].getId(),1);
        assertEquals(processes[3].getId(),4);
        //check waiting times
        assertEquals(processes[0].waitingTime,0.0);
        assertEquals(processes[1].waitingTime,1.0);
        assertEquals(processes[2].waitingTime,3.0);
        assertEquals(processes[3].waitingTime,0.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,3.0);
        assertEquals(processes[1].turnaroundTime,5.0);
        assertEquals(processes[2].turnaroundTime,8.0);
        assertEquals(processes[3].turnaroundTime,2.0);
    }

    @Test
    public void FCFSTutorialQ9Works() {
        GantChart gantChart = new GantChart();

        Process [] processes = {
                new Process(10.0,0.0,3.0 , 1),
                new Process(1.0,0.0,1.0, 2),
                new Process(2.0,0.0,3.0, 3),
                new Process(1.0,0.0,4.0, 4),
                new Process(5.0,0.0,2.0, 5),

        };
        gantChart.firstComeFirstServe(processes);
        //check order
        assertEquals(processes[0].getId(),1);
        assertEquals(processes[1].getId(),2);
        assertEquals(processes[2].getId(),3);
        assertEquals(processes[3].getId(),4);
        assertEquals(processes[4].getId(),5);
        //check waiting times
        assertEquals(processes[0].waitingTime,0.0);
        assertEquals(processes[1].waitingTime,10.0);
        assertEquals(processes[2].waitingTime,11.0);
        assertEquals(processes[3].waitingTime,13.0);
        assertEquals(processes[4].waitingTime,14.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,10.0);
        assertEquals(processes[1].turnaroundTime,11.0);
        assertEquals(processes[2].turnaroundTime,13.0);
        assertEquals(processes[3].turnaroundTime,14.0);
        assertEquals(processes[4].turnaroundTime,19.0);
    }

    @Test
    public void NonPreEmptivePriorityLectureWorks() {
        GantChart gantChart = new GantChart();
        //from lecture slides
        Process [] processes = {
                new Process(2.0,2.0,3.0 , 1),
                new Process(4.0,0.0,4.0, 2),
                new Process(3.0,0.0,0.0, 3),
                new Process(5.0,6.0,2.0 , 4)
        };
        gantChart.nonPreEmptivePriority(processes);
        //check order
        assertEquals(processes[0].getId(),3);
        assertEquals(processes[1].getId(),1);
        assertEquals(processes[2].getId(),2);
        assertEquals(processes[3].getId(),4);
        //check waiting times
        assertEquals(processes[0].waitingTime,0.0);
        assertEquals(processes[1].waitingTime,1.0);
        assertEquals(processes[2].waitingTime,5.0);
        assertEquals(processes[3].waitingTime,3.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,3.0);
        assertEquals(processes[1].turnaroundTime,3.0);
        assertEquals(processes[2].turnaroundTime,9.0);
        assertEquals(processes[3].turnaroundTime,8.0);
    }
    @Test
    public void PreEmptiveSRTFLectureWorks() {
        GantChart gantChart = new GantChart();
        //from lecture slides
        Process [] processes = {
                new Process(5.0,0.0,0.0, 1),
                new Process(4.0,0.0,0.0, 2),
                new Process(2.0,1.0,0.0 , 3),
                new Process(3.0,10.0,0.0 , 4)
        };
        gantChart.preEmptiveShortestRemainingTimeFirst(processes);
        //check waiting times
        assertEquals(processes[0].waitingTime,6.0);
        assertEquals(processes[1].waitingTime,2.0);
        assertEquals(processes[2].waitingTime,0.0);
        assertEquals(processes[3].waitingTime,1.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,11.0);
        assertEquals(processes[1].turnaroundTime,6.0);
        assertEquals(processes[2].turnaroundTime,2.0);
        assertEquals(processes[3].turnaroundTime,4.0);
    }
    @Test
    public void PreEmptivePriorityWorks() {
        GantChart gantChart = new GantChart();
        //from lecture slides
        Process [] processes = {
                new Process(10.0,15.0,17.0, 0),
                new Process(10.0,0.0,7.0, 1),
                new Process(13.0,18.0,5.0, 2),
                new Process(3.0,8.0,8.0 , 3),
                new Process(7.0,6.0,10.0 , 4)
        };
        gantChart.preEmptivePriority(processes);
        //check waiting times
        assertEquals(processes[0].waitingTime,18.0);
        assertEquals(processes[1].waitingTime,0.0);
        assertEquals(processes[2].waitingTime,0.0);
        assertEquals(processes[3].waitingTime,2.0);
        assertEquals(processes[4].waitingTime,20.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,28.0);
        assertEquals(processes[1].turnaroundTime,10.0);
        assertEquals(processes[2].turnaroundTime,13.0);
        assertEquals(processes[3].turnaroundTime,5.0);
        assertEquals(processes[4].turnaroundTime,27.0);

        assertEquals(gantChart.getAvgTurnAroundTime(),16.6);
        assertEquals(gantChart.getAvgWaitingTime(),8);
    }
    @Test
    public void PreEmptivePriorityQuizWorks() {
        GantChart gantChart = new GantChart();
        //from lecture slides
        Process [] processes = {
                new Process(5.0,7.0,1.0, 0),
                new Process(14.0,11.0,10.0, 1),
                new Process(13.0,15.0,14.0, 2),
                new Process(9.0,0.0,2.0 , 3),
                new Process(5.0,21.0,8.0 , 4)
        };

        gantChart.preEmptivePriority(processes);
        //check waiting times
        assertEquals(processes[0].waitingTime,0.0);
        assertEquals(processes[1].waitingTime,8.0);
        assertEquals(processes[2].waitingTime,18.0);
        assertEquals(processes[3].waitingTime,5.0);
        assertEquals(processes[4].waitingTime,0.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,5.0);
        assertEquals(processes[1].turnaroundTime,22.0);
        assertEquals(processes[2].turnaroundTime,31.0);
        assertEquals(processes[3].turnaroundTime,14.0);
        assertEquals(processes[4].turnaroundTime,5.0);

        assertEquals(gantChart.getAvgTurnAroundTime(),15.4);
        assertEquals(gantChart.getAvgWaitingTime(),6.2);
    }
    @Test
    public void PreEmptivePriorityLectureWorks() {
        GantChart gantChart = new GantChart();
        //from lecture slides
        Process [] processes = {
                new Process(2.0,2.0,7.0, 1),
                new Process(4.0,0.0,14.0, 2),
                new Process(3.0,0.0,0.0 , 3),
                new Process(5.0,6.0,3.0 , 4)
        };
        gantChart.preEmptivePriority(processes);
        //check waiting times
        assertEquals(processes[0].waitingTime,1.0);
        assertEquals(processes[1].waitingTime,10.0);
        assertEquals(processes[2].waitingTime,0.0);
        assertEquals(processes[3].waitingTime,0.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,3.0);
        assertEquals(processes[1].turnaroundTime,14.0);
        assertEquals(processes[2].turnaroundTime,3.0);
        assertEquals(processes[3].turnaroundTime,5.0);
    }

    @Test
    public void SRTFWorks() {
        GantChart gantChart = new GantChart();
        //from lecture slides
        Process [] processes = {
                new Process(16.0,7.0,0.0, 0),
                new Process(17.0,19.0,0.0, 1),
                new Process(18.0,0.0,0.0, 2),
                new Process(5.0,6.0,0.0 , 3),
                new Process(15.0,8.0,0.0 , 4)
        };
        gantChart.preEmptiveShortestRemainingTimeFirst(processes);
        //check waiting times
        assertEquals(processes[0].waitingTime,5.0);
        assertEquals(processes[1].waitingTime,0.0);
        assertEquals(processes[2].waitingTime,31.0);
        assertEquals(processes[3].waitingTime,15.0);
        assertEquals(processes[4].waitingTime,35.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,23.0);
        assertEquals(processes[1].turnaroundTime,5.0);
        assertEquals(processes[2].turnaroundTime,47.0);
        assertEquals(processes[3].turnaroundTime,30.0);
        assertEquals(processes[4].turnaroundTime,52.0);

        assertEquals(gantChart.getAvgTurnAroundTime(),31.4);
        assertEquals(gantChart.getAvgWaitingTime(),17.2);
    }

    @Test
    public void roundRobinWorks(){
        GantChart gantChart = new GantChart();
        Process [] processes = {
                new Process(11.0,0.0,0.0, 0),
                new Process(5.0,14.0,0.0, 1),
                new Process(7.0,1.0,0.0, 2),
                new Process(5.0,7.0,0.0 , 3),
                new Process(3.0,10.0,0.0 , 4),
        };
        gantChart.roundRobin(processes, 3);
        //Order here should be P0, P2, P3, P4, P1
        //check waiting times
        assertEquals(processes[0].waitingTime,18.0);
        assertEquals(processes[1].waitingTime,14.0);
        assertEquals(processes[2].waitingTime,15.0);
        assertEquals(processes[3].waitingTime,8.0);
        assertEquals(processes[4].waitingTime,12.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,29.0);
        assertEquals(processes[1].turnaroundTime,21.0);
        assertEquals(processes[2].turnaroundTime,20.0);
        assertEquals(processes[3].turnaroundTime,11.0);
        assertEquals(processes[4].turnaroundTime,17.0);

        assertEquals(gantChart.getAvgTurnAroundTime(),19.6);
        assertEquals(gantChart.getAvgWaitingTime(),13.4);
    }

    @Test
    public void roundRobinLectureWorks(){
        GantChart gantChart = new GantChart();
        Process [] processes = {
                new Process(4.0,4.0,0.0, 1),
                new Process(10.0,0.0,0.0, 2),
                new Process(17.0,17.0,0.0 , 3),
                new Process(18.0,2.0,0.0 , 4)
        };
        gantChart.roundRobin(processes, 5);
        //Order here should be P2, P4, P1, P3
        //check waiting times
        assertEquals(processes[0].waitingTime,9.0);
        assertEquals(processes[1].waitingTime,22.0);
        assertEquals(processes[2].waitingTime,6.0);
        assertEquals(processes[3].waitingTime,15.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,19.0);
        assertEquals(processes[1].turnaroundTime,40.0);
        assertEquals(processes[2].turnaroundTime,10.0);
        assertEquals(processes[3].turnaroundTime,32.0);
    }
    @Test
    public void roundRobinTutorialWorks(){
        GantChart gantChart = new GantChart();
        Process [] processes = {
                new Process(10.0,0.0,0.0, 1),
                new Process(1.0,0.0,0.0, 2),
                new Process(2.0,0.0,0.0 , 3),
                new Process(1.0,0.0,0.0 , 4),
                new Process(5.0,0.0,0.0 , 5)
        };
        gantChart.roundRobin(processes, 1);
        //Order here should be P2, P4, P1, P3
        //check waiting times
        assertEquals(processes[0].waitingTime,9.0);
        assertEquals(processes[1].waitingTime,1.0);
        assertEquals(processes[2].waitingTime,5.0);
        assertEquals(processes[3].waitingTime,3.0);
        assertEquals(processes[4].waitingTime,9.0);
        //check turnaround times
        assertEquals(processes[0].turnaroundTime,19.0);
        assertEquals(processes[1].turnaroundTime,2.0);
        assertEquals(processes[2].turnaroundTime,7.0);
        assertEquals(processes[3].turnaroundTime,4.0);
        assertEquals(processes[4].turnaroundTime,14.0);
    }
}
