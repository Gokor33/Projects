package coursework2;

import java.awt.Dimension;
import java.util.List;
import java.util.Scanner;
import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;

public class Multiarrays extends JApplet{

    private static final long serialVersionUID = 2202072534703043194L;
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    private JGraphXAdapter<String, DefaultWeightedEdge> jgxAdapter;
	private Scanner input;
	
	public static void main(String[] args){
		 DisplayGraph applet = new DisplayGraph();
	        applet.init();

	        JFrame frame = new JFrame();
	        frame.getContentPane().add(applet);
	        frame.setTitle("Airport Graph");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
	    }
		
	public void init(){
		SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = 
				new SimpleDirectedWeightedGraph<String,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		jgxAdapter = new JGraphXAdapter<String, DefaultWeightedEdge>(graph);
		
		input = new Scanner(System.in);
		
        getContentPane().add(new mxGraphComponent(jgxAdapter));
        resize(DEFAULT_SIZE);
        
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
        
        DefaultWeightedEdge e1 = graph.addEdge(Edinburgh, Heathrow); 
        graph.setEdgeWeight(e1, 110); 
        DefaultWeightedEdge e2 = graph.addEdge(Heathrow, Amsterdam);
        graph.setEdgeWeight(e2, 100);
        DefaultWeightedEdge e3 = graph.addEdge(Heathrow, Boston); 
        graph.setEdgeWeight(e3, 230);
        DefaultWeightedEdge e4 = graph.addEdge(Boston, Chicago); 
        graph.setEdgeWeight(e4, 150);
        DefaultWeightedEdge e5 = graph.addEdge(Boston, Montreal); 
        graph.setEdgeWeight(e5, 100);
        DefaultWeightedEdge e6 = graph.addEdge(Montreal, Toronto); 
        graph.setEdgeWeight(e6, 90);
        DefaultWeightedEdge e7 = graph.addEdge(Edinburgh, Chicago); 
        graph.setEdgeWeight(e7, 560);
        DefaultWeightedEdge e8 = graph.addEdge(New_Delhi, Shanghai); 
        graph.setEdgeWeight(e8, 430);
        DefaultWeightedEdge e9 = graph.addEdge(Shanghai, Hong_Kong); 
        graph.setEdgeWeight(e9, 230);
	
	    mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());
        
        System.out.println("The following airports have been used");
        System.out.println(Edinburgh);
        System.out.println(Heathrow);
        System.out.println(Amsterdam);
        System.out.println(Boston);
        System.out.println(Chicago);
        System.out.println(Montreal);
        System.out.println(Toronto);
        System.out.println(New_Delhi);
        System.out.println(Shanghai);
        System.out.println(Hong_Kong);
        
        //System.out.println();
        System.out.println("\nPlease enter the first airport");
        String vertex1 = input.next();
        System.out.println("Please enter the second airport");
        String vertex2 = input.next();
	    
        int i = 1;
        if(vertex1 != null && vertex2 != null){
        System.out.printf("Shortest path from %s to %s:", vertex1, vertex2);
        List<DefaultWeightedEdge> shortest_path = DijkstraShortestPath.findPathBetween(graph, vertex1, vertex2);
        for(DefaultWeightedEdge s : shortest_path){
        System.out.printf("\n%d."+ s, i);
        i++;
        }
        double totalWeight = 0;
        for(DefaultWeightedEdge s : shortest_path){
        totalWeight = totalWeight + graph.getEdgeWeight(s);
        }
        System.out.printf("\nThe cost of the shortest path is = £%s", totalWeight);
        }
        
	}
	

	private static class SimpleDirectedWeightedGraph<V,E> 
	extends SimpleDirectedGraph<V,E>
	implements WeightedGraph<V,E>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SimpleDirectedWeightedGraph(Class<? extends E> edgeClass) {
			super(edgeClass);
	}
	}
	
}