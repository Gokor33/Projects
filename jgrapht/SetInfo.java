package coursework2;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class SetInfo {
	
	private String flight;
	private int departureTime;
	private int arrivalTime;
	private int flightDuration;
	private int ticketPrice;
	private int firstHour;
	
   public SetInfo(String flightNumber, int departure, int arrival, int duration, int ticket) {
		// TODO Auto-generated constructor stub
	   addInformation(flightNumber, departure, arrival, duration, ticket);
	}

   public void addInformation(String flightNumber, int departure, int arrival, int duration, int ticket){
	   setFlight(flightNumber);
	   setDeparture(departure);
	   setArrival(arrival);
	   setFlightDuration(duration);
	   setTicket(ticket);
   }
   
	
   public void setFlight(String flightNumber){
	   flight = flightNumber;
   }
   public void setDeparture(int departure){
	   departureTime = departure;
   }
   public void setArrival(int arrival){
	   arrivalTime = arrival;
   }
   public void setFlightDuration(int duration){
	   flightDuration = duration;
   }
   public void setTicket(int ticket){
	   ticketPrice = ticket;
   }
   public String getFlight(){
	   return flight;
   }
   public int getDeparture(){
	   return departureTime;
   }
   public int getArrival(){
	   return arrivalTime;
   }
   public int getFlightDuration(){
	   return flightDuration;
   }
   public int getTicket(){
	   return ticketPrice;
   }
   public int setHour(int first){
	   String str = Integer.toOctalString(first);
	   int i = Integer.parseInt(str);
	   firstHour = i / 100;
	   return firstHour;
   }
   public int setMinute(int firstm){
	   String str = Integer.toOctalString(firstm);
	   int i = Integer.parseInt(str);
	   firstHour = i % 100;
	   return firstHour;
   }
}
