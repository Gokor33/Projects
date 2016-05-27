package coursework2;

import java.awt.Dimension;
import java.util.Scanner;

import javax.swing.JFrame;

import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.ListenableDirectedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;

public class Backup {
	private static final long serialVersionUID = 2202072534703043194L;
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    

    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;
	
	
	public static void main(String[] args) {
		   MoreInfo applet = new MoreInfo();
	        applet.init();

	        JFrame frame = new JFrame();
	        frame.getContentPane().add(applet);
	        frame.setTitle("JGraphT Adapter to JGraph Demo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public void init()
	    {
	        // create a JGraphT graph
	        ListenableGraph<String, DefaultEdge> graph =
	            new ListenableDirectedGraph<String, DefaultEdge>(
	                DefaultEdge.class);
	        
	        Scanner input = new Scanner(System.in);

	        // create a visualization using JGraph, via an adapter
	        jgxAdapter = new JGraphXAdapter<String, DefaultEdge>(graph);

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
	        
	        

	        DefaultEdge e1 = graph.addEdge(Edinburgh, Heathrow);graph;
	        DefaultEdge e2 = graph.addEdge(Heathrow, Amsterdam);
	        DefaultEdge e3 = graph.addEdge(Heathrow, Boston);
	        DefaultEdge e4 = graph.addEdge(Boston, Chicago); 
	        DefaultEdge e5 = graph.addEdge(Boston, Montreal);
	        DefaultEdge e6 = graph.addEdge(Montreal, Toronto); 
	        DefaultEdge e7 = graph.addEdge(Edinburgh, Chicago);
	        DefaultEdge e8 = graph.addEdge(New_Delhi, Shanghai);
	        DefaultEdge e9 = graph.addEdge(Shanghai, Hong_Kong); 
	        
	        
	        
            
	        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
	        layout.execute(jgxAdapter.getDefaultParent());
	        
	        
	    }
	    private static class ListenableDirectedMultigraph<V, E>
        extends DefaultListenableGraph<V, E>
        implements DirectedGraph<V, E>
        {
        private static final long serialVersionUID = 1L;

        ListenableDirectedMultigraph(Class<E> edgeClass)
        {
            super(new DirectedMultigraph<V, E>(edgeClass));
        }
    }
}
