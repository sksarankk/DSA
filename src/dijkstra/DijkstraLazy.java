package dijkstra;

import java.util.*;

public class DijkstraLazy {
    public static class Edge{
        int from;
        int to;
        int cost;
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    public static class Pair{
        int key;
        int val;
        public Pair(int key, int val){
            this.key = key;
             this.val = val;
        }
    }
    public int[] findShortestPath(Map<Integer, List<Edge>> graph, int start){
        int numOfNodes = graph.size();
        int[] dist = new int[numOfNodes];
        boolean[] visited = new boolean[numOfNodes];
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<Pair> q = new PriorityQueue<>((a,b) -> Integer.compare(a.val,b.val));
        dist[start] = 0;
        q.offer(new Pair(start,0));
        while(!q.isEmpty()){
            Pair at  = q.poll();
            if(dist[at.key] < at.val) continue;
            visited[at.key] = true;
            List<Edge> children = graph.get(at.key);
            for(Edge edge: children){
                if(edge != null){
                    if(visited[edge.to]) continue;
                    if (dist[edge.to] > at.val+ edge.cost){
                        dist[edge.to] = at.val+ edge.cost;
                        q.offer(new Pair(edge.to, dist[edge.to]));
                    }
                }
            }

        }
        return dist;
    }
    public static void main(String[] args) {
        Map<Integer,List<Edge>> graph = new HashMap<>();
        addDirectedEdge(graph, 0, 1, 4);
        addDirectedEdge(graph, 0, 2, 1);
        addDirectedEdge(graph, 2, 1, 2);
        addDirectedEdge(graph, 1, 3, 1);
        addDirectedEdge(graph, 2, 3, 5);
        addDirectedEdge(graph, 3, 4, 3);
        graph.put(4, new ArrayList<>());
        DijkstraLazy dijkstraLazy = new DijkstraLazy();
        int[] path = dijkstraLazy.findShortestPath(graph, 0);
        System.out.println(Arrays.toString(path));
    }
    private static void addDirectedEdge(Map<Integer, List<Edge>> graph, int from, int to, int cost){
        graph.computeIfAbsent(from, k -> new ArrayList<Edge>()).add(new Edge(from, to, cost));
    }
}
