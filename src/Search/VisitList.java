//A classe armazena um vertice e um boolean para indicar se o vertice foi visitado
package Search;

import Graph.Vertex;

public class VisitList {

    private Vertex vertex;
    private boolean visited;
    
    public VisitList(Vertex vertex, boolean visited){
        this.vertex = vertex;
        this.visited = visited;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
}
