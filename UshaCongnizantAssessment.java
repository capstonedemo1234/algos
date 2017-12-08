package interview2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeSet;

public class UshaCongnizantAssessment {
	
	public static void main(String[] args) {
		UshaCongnizantAssessment u = new UshaCongnizantAssessment();
		u.getAvailableBuses("/Users/reddyk/Documents/usha_congnizant/bus_routes.txt", 15, 30, "14-11-2017");
		Map<Integer, TreeSet<String>> map= u.getBusesRunningOn("/Users/reddyk/Documents/usha_congnizant/bus_routes.txt");
		System.out.println("map.toString() : "+ map.toString());
		
	}
	
	
	public void getAvailableBuses(String filePath, int source , int destination, String dateString){
	List<Bus> totalBusList = readFile(filePath);
	System.out.println("totalBusList.size() : "+ totalBusList.size());
	
	List<Bus> availableBusList = new ArrayList<>();
	for (Bus bus : totalBusList) {
		boolean running = isBusRunningOnThisDay(dateString, bus.getDayInfo());
		System.out.println(" bus.getBusRoute() "+ bus.getBusRoute() + " running "+ running);
		if(running && isRouteWithinRange(bus, source, destination)){
			double totalFare = bus.getBaseFare() + (destination - source)*bus.getAdditionalFare();
			System.out.println("----------------------------Bus : "+ bus.getBusNumber() + ", totalFare : "+totalFare);
		}
	}
		
		
	}
	
	public Map<Integer, TreeSet<String>> getBusesRunningOn(String filePath){
		
		List<Bus> totalBusList =  readFile(filePath);
		Map<Integer, TreeSet<String>> map = new HashMap<>();
		TreeSet<String> sundaySet = new TreeSet<>();
		TreeSet<String> saturdaySet  = new TreeSet<>();
		for (Bus bus : totalBusList) {
			String dayInfo = bus.getDayInfo();
			if(dayInfo.charAt(0)=='1'){
				sundaySet.add(bus.getBusNumber());
			}
			if (dayInfo.charAt(6)=='1'){
				saturdaySet.add(bus.getBusNumber());
			}
		}
		map.put(1, saturdaySet);
		map.put(2, sundaySet);
		
		return map;
		
	}
	
	public boolean isRouteWithinRange(Bus b, int source , int dest){
		
		if(b.getSource()<=source && b.getDestination()>=dest){
			return true;
		}
		
		return false;
	}
	public boolean isBusRunningOnThisDay(String dateString, String dayInfo ){
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	    Date date = null;
	    try {
	        date = format.parse(dateString);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	    calendar.setTime(date);
	    int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);
	    System.out.println("Day of the week = "
	            + dayOfTheWeek);
	     
	    char[] charArray = dayInfo.toCharArray();
	    if(charArray[dayOfTheWeek-1]-'0'==1){
	    	System.out.println("dateString : "+ dateString + " is running bus " + dayInfo);
	    	return true;
	    }
//	    System.out.println("Saturday? "
//	            + (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY));
	    
	    return false;
	}
	
	public  List<Bus> readFile(String filePath){
//		String csvFile = "/Users/mkyong/csv/country.csv";
		List<Bus> busList = new ArrayList<>();
		
		
        BufferedReader br = null;
        String line = "";
        String splitBy = "\\|";

        try {

            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {

                String[] busInfo = line.split(splitBy);
            	Bus b = new Bus();
            	b.setBusNumber(busInfo[0]);
            	b.setBusRoute(busInfo[1]);
            	b.setSource((Integer.parseInt(busInfo[2])));
            	b.setDestination(Integer.parseInt(busInfo[3]));
            	b.setDayInfo(busInfo[4]);
            	b.setBaseFare((Double.parseDouble(busInfo[5])));
            	b.setAdditionalFare((Double.parseDouble(busInfo[6])));
            	busList.add(b);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return busList;

    }

}

class Bus{ 
	private String busNumber;
	private String busRoute;
	private int source;
	private int destination;
	private String dayInfo;
	private double baseFare;
	private double additionalFare;
	
	
	
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public String getBusRoute() {
		return busRoute;
	}
	public void setBusRoute(String busRoute) {
		this.busRoute = busRoute;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	public String getDayInfo() {
		return dayInfo;
	}
	public void setDayInfo(String dayInfo) {
		this.dayInfo = dayInfo;
	}
	public double getBaseFare() {
		return baseFare;
	}
	public void setBaseFare(double baseFare) {
		this.baseFare = baseFare;
	}
	public double getAdditionalFare() {
		return additionalFare;
	}
	public void setAdditionalFare(double additionalFare) {
		this.additionalFare = additionalFare;
	}
	
}

