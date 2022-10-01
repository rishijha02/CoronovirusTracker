package com.rs.corona.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rs.corona.models.locationstat;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;


@Service
public class coronaservice {
	
	private List<locationstat>allstat=new ArrayList<>();
	
	
	
	public List<locationstat> getAllstat() {
		return allstat;
	}

	public void setAllstat(List<locationstat> allstat) {
		this.allstat = allstat;
	}

	private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	@PostConstruct
	public void fetchvirus() throws IOException, InterruptedException
	{
		List<locationstat> newstat=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest request=HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		
		HttpResponse<String>response=client.send(request,HttpResponse.BodyHandlers.ofString());
		
		StringReader csvbodyreader=new StringReader(response.body());
		Iterable<CSVRecord> records=CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvbodyreader);
		
		for(CSVRecord record:records)
		{
			locationstat stat=new locationstat();
			stat.setCountry(record.get("Country/Region"));
			stat.setState(record.get("Province/State"));
			stat.setLatestcases(Integer.parseInt(record.get(record.size()-1)));
			int latest=Integer.parseInt(record.get(record.size()-1));
			int prev=Integer.parseInt(record.get(record.size()-2));
			stat.setPrvDayDiff(latest-prev);
			System.out.println(stat);
			newstat.add(stat);
		}
		this.allstat=newstat;
		//System.out.println(response.body());
	}
}
