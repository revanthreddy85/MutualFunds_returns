package returns;

import api.APIURL;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.TreeMap;

public class App {

    public static void main(String []args){
        String scheme_id=args[0];
        double poi=Double.parseDouble(args[1]);
        int horizon=Integer.parseInt(args[2]);

        try {
            APIURL urlObject=new APIURL();
            urlObject.queryAPI(scheme_id);
            TreeMap<Date,Double> returns = urlObject.getReturnsData();

            CalculateReturns returnsObj= new CalculateReturns();
            returnsObj.calculate(poi,horizon,returns);
            LinkedList<Data> returnsList = returnsObj.getReturnsList();

            Output outputObj = new Output();
            outputObj.printOutput(returnsList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
