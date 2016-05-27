package coursework2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedMultigraph;


public class DisplayGraph extends JApplet {
    

    private static final long serialVersionUID = 3256444702936019250L;
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FFFF99");
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    

    //
    private JGraphModelAdapter<String, DefaultEdge> jgAdapter;

    

    /**
     * An alternative starting point for this demo, to also allow running this
     * applet as an application.
     *
     * @param args ignored.
     */
    public static void main(String [] args)
    {
        DisplayGraph applet = new DisplayGraph();
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
            new ListenableDirectedMultigraph<String, DefaultEdge>(
                DefaultEdge.class);

        // create a visualization using JGraph, via an adapter
        jgAdapter = new JGraphModelAdapter<String, DefaultEdge>(graph);

        JGraph jgraph = new JGraph(jgAdapter);

        adjustDisplaySettings(jgraph);
        getContentPane().add(jgraph);
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

        // add some sample data (graph manipulated via JGraphT)
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

        graph.addEdge(Edinburgh, Heathrow);
        graph.addEdge(Heathrow, Amsterdam);
        graph.addEdge(Heathrow, Boston);
        graph.addEdge(Boston, Chicago); 
        graph.addEdge(Boston, Montreal);
        graph.addEdge(Montreal, Toronto); 
        graph.addEdge(Edinburgh, Chicago);
        graph.addEdge(New_Delhi, Shanghai);
        graph.addEdge(Shanghai, Hong_Kong); 
        // position vertices nicely within JGraph component
        positionVertexAt(Edinburgh, 130, 40);
        positionVertexAt(Heathrow, 60, 200);
        positionVertexAt(Amsterdam, 310, 230);
        positionVertexAt(Boston, 380, 70);
        positionVertexAt(Chicago,460 , 30);
        positionVertexAt(Montreal,210 ,60);
        positionVertexAt(Toronto,100 ,20);
        positionVertexAt(New_Delhi,30 ,50);
        positionVertexAt(Shanghai,10 ,10);
        positionVertexAt(Hong_Kong,250 ,10);

        // that's all there is to it!...
    }

    private void adjustDisplaySettings(JGraph jg)
    {
        jg.setPreferredSize(DEFAULT_SIZE);

        Color c = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter("bgcolor");
        } catch (Exception e) {
        }

        if (colorStr != null) {
            c = Color.decode(colorStr);
        }

        jg.setBackground(c);
    }

    @SuppressWarnings("unchecked") // FIXME hb 28-nov-05: See FIXME below
    private void positionVertexAt(Object vertex, int x, int y)
    {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds =
            new Rectangle2D.Double(
                x,
                y,
                bounds.getWidth(),
                bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);

        // TODO: Clean up generics once JGraph goes generic
        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
    }

    

    /**
     * a listenable directed multigraph that allows loops and parallel edges.
     */
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

// End JGraphAdapterDemo.java
