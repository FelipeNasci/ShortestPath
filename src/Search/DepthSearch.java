package Search;

import Graph.Graph;
import Graph.Vertex;

public class DepthSearch implements Search {

    @Override
    public void search(Graph graph) {

        //lista de vertices nao visitados
        VisitList[] list = new VisitList[graph.getLength()];
        
        //Insere os vertices do grafo na lista
        //Atribui "nao visitado" para todos os vertices
        init(graph, list);
        
        
        showVisited(list);
        
    }

    private void depth(VisitList[] list){
        //Marca o primeiro vertice da lista como visitado
        //A busca sera iniciada por ele
        list[0].setVisited(true);
        
        while (true) {
            
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
