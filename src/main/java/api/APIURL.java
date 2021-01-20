package api;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

public class APIURL {
    private TreeMap<Date,Double> map = new TreeMap<Date, Double>();

    public TreeMap<Date, Double> getReturnsData() {
        return map;
    }

    public void queryAPI(String scheme_id) throws ParseException {
        String url = "https://api.mfapi.in/mf/";
        String queryURL = url + scheme_id;

        HttpResponse<JsonNode> response = Unirest.get(queryURL).asJson();
        JSONArray arr= response.getBody().getObject().getJSONArray("data");
        for(int i = 0; i < arr.length(); i++){
            String date=arr.getJSONObject(i).getString("date");
            String nav=arr.getJSONObject(i).getString("nav");
            map.put(new SimpleDateFormat("dd-MM-yyyy").parse(date),Double.parseDouble(nav));
        }
    }
}
