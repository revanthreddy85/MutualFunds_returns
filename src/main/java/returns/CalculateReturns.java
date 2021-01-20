package returns;

import java.util.*;

class CalculateReturns {

    private LinkedList<Data> returnsList = null;

    LinkedList<Data> getReturnsList() {
        return returnsList;
    }

    private Date getPrevMonth(Date date){
        Calendar c= Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

    private Date getPrevYear(Date date,int poi){
        Calendar c= Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -1*poi);
        return c.getTime();
    }

    private double calculateCurrent(double poi, Date currentDate, Date prevYear, TreeMap<Date, Double> returnsMap){
        if(returnsMap.lowerKey(currentDate) == null)
            return 0;
        Double currentNav = returnsMap.get(returnsMap.lowerKey(currentDate));
        if(returnsMap.ceilingKey(prevYear) == null)
            return 0;
        Double prevNav = returnsMap.get(returnsMap.ceilingKey(prevYear));

        return (double) Math.round((Math.pow(currentNav/prevNav , 1/poi) - 1) * 100);
    }

    private double calculateRemaining(double poi, Date currentDate, Date prevYear, TreeMap<Date, Double> returnsMap){
        if(returnsMap.ceilingKey(currentDate) == null)
            return 0;
        Double currentNav = returnsMap.get(returnsMap.ceilingKey(currentDate));
        if(returnsMap.ceilingKey(prevYear) == null)
            return 0;
        Double prevNav = returnsMap.get(returnsMap.ceilingKey(prevYear));

        return (double) Math.round((Math.pow(currentNav/prevNav , 1/poi) - 1) * 100);
    }

    void calculate(double poi, int horizon, TreeMap<Date, Double> returnsMap){
        Date date= new Date();
        returnsList = new LinkedList<Data>();
        for(int i = 0; i < horizon * 12; i++){
            Date prevYear=getPrevYear(date, (int) poi);
            if(i==0){
                returnsList.addFirst(new Data(prevYear,date,calculateCurrent(poi,date,prevYear,returnsMap)));
            }else{
                returnsList.addFirst(new Data(prevYear,date,calculateRemaining(poi,date,prevYear,returnsMap)));
            }
            date = getPrevMonth(date);
        }
    }
}
