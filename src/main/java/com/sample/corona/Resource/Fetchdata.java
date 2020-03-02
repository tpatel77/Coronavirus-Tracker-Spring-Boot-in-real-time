package com.sample.corona.Resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.sample.corona.model.LocationStats;

@Service
public class Fetchdata {
	
	
	private static String Data_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
    
	 private List<LocationStats> allStats = new ArrayList<>();

	    public List<LocationStats> getAllStats() {
	        return allStats;
	    }
	
	@PostConstruct
	public void fetch_data() throws Exception
    {
    	URL myUrl = new URL(Data_url);
        HttpsURLConnection conn = (HttpsURLConnection)myUrl.openConnection();
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = br.readLine()) != null) {
          response.append(line);
          response.append('\r');
        }
              
        br.close();
        
        StringReader csvBodyReader = new StringReader(response.toString());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        List<LocationStats> newStats = new ArrayList<>();
        for (CSVRecord record : records) {
        	LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            locationStat.setLatestTotalCases(latestCases);
            locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
            locationStat.str();
            newStats.add(locationStat);
        }
        this.allStats = newStats;
    }
	
}
