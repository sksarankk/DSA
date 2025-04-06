package graphs.dfs;

import java.util.*;

public class ConnectedComponent {
    //number of nodes
    private final int n;
    private final Map<Integer, List<Integer>> graph;
    private final boolean[] visited ;
    private final int[] components;
    private  int count = 0;

    public ConnectedComponent(Map<Integer, List<Integer>> graph){
        if (graph == null) throw new NullPointerException();
        this.n = graph.size();
        this.graph = graph;
        this.visited = new boolean[n];
        this.components = new int[n];
    }
    public static class CountAndComponent{
        int count;
        int[] component;
        public CountAndComponent(int count, int[] component){
            this.count = count;
            this.component = component;
        }
    }
    public CountAndComponent findComponents(){
        for (int i = 0; i < n; i++){
            if (!visited[i]){
                count++;
                dfs(i);
            }
        }
        return new CountAndComponent(count,components);

    }
    public void dfs(int at){
        visited[at] = true;
        components[at] = count;
        List<Integer>neighbours = graph.get(at);
        for(int neighbour : neighbours){
            if(!visited[neighbour]){
                dfs(neighbour);
            }
        }
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        addEdge(0, 1, graph);
        addEdge(1,2, graph);
        addEdge(2,0, graph);
        addEdge(3,4,graph);
        addEdge(5,6,graph);
        ConnectedComponent connectedComponent = new ConnectedComponent(graph);
        CountAndComponent countAndComponent = connectedComponent.findComponents();
        System.out.println("Count:"+ connectedComponent.count);
        System.out.println("Here are the components: " + Arrays.toString(connectedComponent.components));

    }
    private static void addEdge(int u, int v, Map<Integer, List<Integer>> graph) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u); // Undirected
    }
}
