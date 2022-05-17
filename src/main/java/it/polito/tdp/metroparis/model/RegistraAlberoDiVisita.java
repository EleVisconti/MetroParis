package it.polito.tdp.metroparis.model;

import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;

public class RegistraAlberoDiVisita implements TraversalListener<Fermata, DefaultEdge> {
	
	private Graph<Fermata, DefaultEdge> grafo;
	private Map<Fermata,Fermata> alberoInverso ;
	

	public RegistraAlberoDiVisita(Map<Fermata, Fermata> alberoInverso, Graph<Fermata, DefaultEdge> grafo) {
		super();
		this.alberoInverso = alberoInverso;
		this.grafo = grafo ;
	}

	@Override
	public void connectedComponentFinished(ConnectedComponentTraversalEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectedComponentStarted(ConnectedComponentTraversalEvent e) {
		// TODO Auto-generated method stub
		
	}

	//intercetto l'evento di attraversamento di un nuovo vertice
	@Override
	public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> e) {
//		System.out.println(e.getEdge());
		
		Fermata source = this.grafo.getEdgeSource(e.getEdge());
		Fermata target = this.grafo.getEdgeTarget(e.getEdge());
		
		//non contiene il target? ho trovato source
		if(!alberoInverso.containsKey(target)) {
			alberoInverso.put(target, source) ;
//			System.out.println(target + " si raggiunge da "+ source) ;
		}
		
		//non contiene source? ho trovato il target
		else if(!alberoInverso.containsKey(source)) {
			alberoInverso.put(source, target) ;
//			System.out.println(source + " si raggiunge da "+ target) ;

		}
		
		
		
	}

	@Override
	public void vertexTraversed(VertexTraversalEvent<Fermata> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vertexFinished(VertexTraversalEvent<Fermata> e) {
		// TODO Auto-generated method stub
		
	}

}
