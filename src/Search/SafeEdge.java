package Search;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;

public class SafeEdge implements Search {
    
    private VisitList[] list;
 
    private Edge edge;
    //private Vertex v;
    
    @Override
    public void search(Graph graph) {

        //lista de vertices nao visitados
        this.list = new VisitList[graph.getLength()];

        //Insere os vertices do grafo na lista
        //Atribui "nao visitado" para todos os vertices
        init(graph, list);

        edge = list[0].getVertex().getEdge(0);
        safeEdge(list[0].getVertex());
        

        //showVisited(list);

    }

    private void safeEdge(Vertex vertex){
        
        try{Thread.sleep(1000);}catch(Exception er){}
        System.err.println("Estou no Vertice " + vertex.getId());
                
        //Marca o primeiro vertice da lista como visitado
        //A busca sera iniciada por ele
        int vertice = vertex.getId();
        list[vertice].setVisited(true);

        Vertex nextVertex;
                
        for (int i = 0; i < vertex.lengthEdge(); i++){
            
            //Identifica qual o vertice ligado ao vertice atual
            nextVertex =  list[vertice].getVertex().getEdge(i).getNextVertex();
            
            if (edge.getValue() < vertex.getEdge(i).getValue())
                edge = vertex.getEdge(i);

            System.err.println("Aresta " +  edge.getValue());
            //Caso ele nao tenha sido visitado, visite-o
            if( !list[nextVertex.getId()].isVisited() )
                safeEdge(nextVertex);
        }
        
    }
    
    
    //Inicializa a lista de vertices visitados e obtem uma lista de vertices
    private void init(Graph graph, VisitList[] list) {
        for (int i = 0; i < graph.getLength(); i++) 
            list[i] = new VisitList(graph.getVertex(i), false);
    }
    
    private void showVisited(VisitList[] list){
        for (int i = 0; i < list.length; i++){
            System.out.println("Vertice " + list[i].getVertex().getId());
        
            if (list[i].isVisited())
                System.err.println("Foi Visitado");
            else 
                System.err.println("Nao foi Visitado");
            
            try{Thread.sleep(100);}catch(Exception e){}
        }
    }
}
