package returns;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

class Output {

    private String format(Date date){
        return new SimpleDateFormat("dd-MMM-yyyy").format(date);
    }

    void printOutput(LinkedList<Data> returnsList){

        System.out.printf("%-15s %-15s %-15s %n", "Month","Returns%","Calculation");
        for (Data data : returnsList) {
            String startDate = format(data.getStartDate());
            String endDate = format(data.getEndDate());
            System.out.printf("%-15s %-15s %s %n", endDate.substring(3), (int)data.getReturns()+"%", "Start nav - " + startDate);
            System.out.printf("%53s %n", "End nav - " + endDate);
        }
    }

}
