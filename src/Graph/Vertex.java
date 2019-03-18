package Graph;

import java.util.LinkedList;

public class Vertex {

    private int id;
    private LinkedList<Edge> listEdge;
    public String label;

    public Vertex(int id) {
        this.id = id;
        this.label = "Vertice nao rotulado";
        listEdge = new LinkedList<>();
    }
    
    //Construtor vertice com rotulo
    public Vertex (int id, String label){
        this(id);
        this.label = label;
    }

    protected void addVertex(Vertex vertex, int value) {
        
        //Nos garante que o conjunto de arestas estaja ordenado
        int i = 0;
        
        while (i < listEdge.size()){
            if(listEdge.get(i).getValue() > value)
                break;
            i++;
        }
        
        //Se liga a outro vertice por meio de uma aresta ponderada
        listEdge.add(i, new Edge(this, vertex, value));
    }

    //Exibe todos os outros vertices conectados
    protected void showVertices() {

        for (int i = 0; i < listEdge.size(); i++) 

            System.out.println(getId() + " <-- " +
                    listEdge.get(i).getValue() +
                    " --> " + listEdge.get(i).getNextVertex().getId());
    }

    public int getId() {
        return id;
    }

    public Edge getEdge(int i){
        
        if(lengthEdge() > 0)
            return listEdge.get(i);
        
        return null;
    }
    
    public int lengthEdge(){
        return listEdge.size();
    }
}
