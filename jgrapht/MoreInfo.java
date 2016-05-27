package coursework2;

import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;

import java.util.List;
import java.util.Scanner;

import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.ListenableDirectedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;

public class MoreInfo extends JApplet{


    private static final long serialVersionUID = 2202072534703043194L;
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    

    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;
	
	
	public static void main(String[] args) throws NullPointerException {
		// create a JGraphT graph
        ListenableGraph<String, SetInfo> graph =
            new ListenableDirectedGraph<String, SetInfo>(
                SetInfo.class);
        
        Scanner input = new Scanner(System.in);

        // create a visualization using JGraph, via an adapter
        //jgxAdapter = new JGraphXAdapter<String, SetInfo>(graph);

        String Edinburgh = "Edinburgh";
        String Heathrow = "Heathrow";
        String Amsterdam = "Amsterdam";
        String Boston = "Boston";
        String Chicago = "Chicago";
        String Montreal = "Montreal";
        String Toronto = "Toronto";
        String New_Delhi = "New_Delhi";
        String Shanghai = "Shanghai";
        String Hong_Kong = "Hong_Kong";
        
        graph.addVertex(Edinburgh);
        graph.addVertex(Heathrow);
        graph.addVertex(Amsterdam);
        graph.addVertex(Boston);
        graph.addVertex(Chicago);
        graph.addVertex(Montreal);
        graph.addVertex(Toronto);
        graph.addVertex(New_Delhi);
        graph.addVertex(Shanghai);
        graph.addVertex(Hong_Kong);
        
        SetInfo e1 = new SetInfo("BK780", 1030, 1130, 0130, 110);graph.addEdge("Edinburgh", "Heathrow", e1);
        SetInfo e2 = new SetInfo("AL901", 1030, 1530, 0230, 230);graph.addEdge("Heathrow", "Boston", e2);
        SetInfo e3 = new SetInfo("NM198", 1800, 1930, 0130, 100);graph.addEdge("Boston", "Montreal", e3);
        SetInfo e4 = new SetInfo("BT986", 2200, 2330, 0130, 90);graph.addEdge("Montreal", "Toronto", e4);
        SetInfo e5 = new SetInfo("JI116", 0700, 1000, 0300, 100);graph.addEdge("Heathrow", "Amsterdam", e5);
        SetInfo e6 = new SetInfo("TY7309", 1000, 1400, 0400, 150);graph.addEdge("Boston", "Chicago", e6);
        SetInfo e7 = new SetInfo("VO664", 1900, 2130, 0230, 560);graph.addEdge("Edinburgh", "Chicago", e7);
        SetInfo e8 = new SetInfo("EQ387", 1100, 1430, 0330, 430);graph.addEdge("New_Delhi", "Shanghai", e8);
        SetInfo e9 = new SetInfo("UI690", 2000, 2337, 0337, 230);graph.addEdge("Shanghai", "Hong_Kong", e9);
        
        System.out.println("Please enter the first airport");
        String vertex1 = input.next();
        System.out.println("Please enter the second airport");
        String vertex2 = input.next();
        System.out.println();
        System.out.println("Leg Leave      At         On         Arrive     At");
	    
        int i = 1;
        int totalCost = 0;
        int totalHour = 0;
        int totalMinute = 0;
        if(vertex1 != null && vertex2 != null){
        List<SetInfo> shortest_path = DijkstraShortestPath.findPathBetween(graph, vertex1, vertex2);
        for(SetInfo s : shortest_path){
          if(s == e1){
        	  System.out.print(i + ".  ");
        	  System.out.printf("%-10.10s %-10.10s %-10.10s %-10.10s %-10.10s", 
        			  Edinburgh, e1.getDeparture(), e1.getFlight(), Heathrow, e1.getArrival());
        	  totalCost = totalCost + e1.getTicket();
        	  int e1Hour = e1.setHour(e1.getFlightDuration());
        	  int e1Minute = e1.setMinute(e1.getFlightDuration());
        	  totalHour = totalHour + e1Hour;
        	  totalMinute = totalMinute + e1Minute;
          }
          if(s == e2){
        	  System.out.print(i + ".  ");
        	  System.out.printf("%-10.10s %-10.10s %-10.10s %-10.10s %-10.10s", 
        			  Heathrow, e2.getDeparture(), e2.getFlight(), Boston, e2.getArrival());
        	  totalCost = totalCost + e2.getTicket();
        	  int e2Hour = e2.setHour(e2.getFlightDuration());
        	  int e2Minute = e2.setMinute(e2.getFlightDuration());
        	  totalHour = totalHour + e2Hour;
        	  totalMinute = totalMinute + e2Minute;
          }
          if(s == e3){
        	  System.out.print(i + ".  ");
        	  System.out.printf("%-10.10s %-10.10s %-10.10s %-10.10s %-10.10s", 
        			  Boston, e3.getDeparture(), e3.getFlight(), Montreal, e3.getArrival());
        	  totalCost = totalCost + e3.getTicket();
        	  int e3Hour = e3.setHour(e3.getFlightDuration());
        	  int e3Minute = e3.setMinute(e3.getFlightDuration());
        	  totalHour = totalHour + e3Hour;
        	  totalMinute = totalMinute + e3Minute;
          }
          if(s == e4){
        	  System.out.print(i + ".  ");
        	  System.out.printf("%-10.10s %-10.10s %-10.10s %-10.10s %-10.10s", 
        			  Montreal, e4.getDeparture(), e4.getFlight(), Toronto, e4.getArrival());
        	  totalCost = totalCost + e4.getTicket();
        	  int e4Hour = e4.setHour(e4.getFlightDuration());
        	  int e4Minute = e4.setMinute(e4.getFlightDuration());
        	  totalHour = totalHour + e4Hour;
        	  totalMinute = totalMinute + e4Minute;
          }
          if(s == e5){
        	  System.out.print(i + ".  ");
        	  System.out.printf("%-10.10s %-10.10s %-10.10s %-10.10s %-10.10s", 
        			  Heathrow, e5.getDeparture(), e5.getFlight(), Amsterdam, e5.getArrival());
        	  totalCost = totalCost + e5.getTicket();
        	  int e5Hour = e5.setHour(e5.getFlightDuration());
        	  int e5Minute = e5.setMinute(e5.getFlightDuration());
        	  totalHour = totalHour + e5Hour;
        	  totalMinute = totalMinute + e5Minute;
          }
          if(s == e6){
        	  System.out.print(i + ".  ");
        	  System.out.printf("%-10.10s %-10.10s %-10.10s %-10.10s %-10.10s", 
        			  Boston, e6.getDeparture(), e6.getFlight(), Chicago, e6.getArrival());
        	  totalCost = totalCost + e6.getTicket();
        	  int e6Hour = e6.setHour(e6.getFlightDuration());
        	  int e6Minute = e6.setMinute(e6.getFlightDuration());
        	  totalHour = totalHour + e6Hour;
        	  totalMinute = totalMinute + e6Minute;
          }
          if(s == e7){
        	  System.out.print(i + ".  ");
        	  System.out.printf("%-10.10s %-10.10s %-10.10s %-10.10s %-10.10s", 
        			  Edinburgh, e7.getDeparture(), e7.getFlight(), Chicago, e7.getArrival());
        	  totalCost = totalCost + e7.getTicket();
        	  int e7Hour = e7.setHour(e7.getFlightDuration());
        	  int e7Minute = e7.setMinute(e7.getFlightDuration());
        	  totalHour = totalHour + e7Hour;
        	  totalMinute = totalMinute + e7Minute;
          }
          if(s == e8){
        	  System.out.print(i + ".  ");
        	  System.out.printf("%-10.10s %-10.10s %-10.10s %-10.10s %-10.10s", 
        			  New_Delhi, e8.getDeparture(), e8.getFlight(), Shanghai, e8.getArrival());
        	  totalCost = totalCost + e8.getTicket();
        	  int e8Hour = e8.setHour(e8.getFlightDuration());
        	  int e8Minute = e8.setMinute(e8.getFlightDuration());
        	  totalHour = totalHour + e8Hour;
        	  totalMinute = totalMinute + e8Minute;
          }
          if(s == e9){
        	  System.out.print(i + ".  ");
        	  System.out.printf("%-10.10s %-10.10s %-10.10s %-10.10s %-10.10s", 
        			  Shanghai, e9.getDeparture(), e9.getFlight(), Hong_Kong, e9.getArrival());
        	  totalCost = totalCost + e9.getTicket();
        	  int e9Hour = e9.setHour(e9.getFlightDuration());
        	  int e9Minute = e9.setMinute(e9.getFlightDuration());
        	  totalHour = totalHour + e9Hour;
        	  totalMinute = totalMinute + e9Minute;
          }
          i++;
          System.out.println();
        }
        System.out.printf("Total cost of the journy = £"
        		+ "%s", totalCost);
        }
        if(totalMinute<60)
        	System.out.printf("\nTotal time in the air = %s%shrs", totalHour, totalMinute);
        else if(totalMinute>=60){
        	totalHour = totalHour + 1;
        	totalMinute = 60-(120-totalMinute);
        	if(totalMinute > 9)
        	System.out.printf("\nTotal time in the air = %s%shrs", totalHour, totalMinute);
        	if(totalMinute < 10){
            System.out.printf("\nTotal time in the air = %s0%shrs", totalHour, totalMinute);	
            }
        	
        }
        
        
        
        
    }
    private static class ListenableDirectedMultigraph<V, E>
    extends DefaultListenableGraph<V, E>
    implements DirectedGraph<V, E>
    {
    private static final long serialVersionUID = 1L;
    
    ListenableDirectedMultigraph(Class<E> edgeClass){
        super(new DirectedMultigraph<V, E>(edgeClass));
    }
    }
}
    
		