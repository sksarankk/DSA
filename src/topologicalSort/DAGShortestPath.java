package topologicalSort;

import java.util.*;

public class DAGShortestPath {
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
    public int[] findShortestPath(Map<Integer, List<Edge>> graph, int start){
        int numOfNodes = graph.size();
        int[] cost = new int[numOfNodes];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[numOfNodes];
        visited[start] =true;
        q.offer(start);
        while(!q.isEmpty()){
            int at = q.poll();
            List<Edge> nextNodes = graph.get(at);
            for (Edge next: nextNodes){
                if(next != null) {
                    cost[next.to] = Math.min(cost[next.to], cost[at]+next.cost);
                    if(!visited[next.to]) {
                        visited[next.to] = true;
                        q.offer(next.to);
                    }
                }
            }
        }
        return cost;

    }
    public static void main(String[] args) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        addDirectedEdge(graph, 0, 1, 3);
        addDirectedEdge(graph, 0, 2, 6);
        addDirectedEdge(graph, 1, 2, 4);
        addDirectedEdge(graph, 1, 3, 4);
        addDirectedEdge(graph, 2, 3, 8);
        graph.put(3, new ArrayList<>());
        DAGShortestPath shortestPath = new DAGShortestPath();
        int[] path = shortestPath.findShortestPath(graph, 1);
        System.out.println(Arrays.toString(path));
    }
    private static void addDirectedEdge(Map<Integer, List<Edge>> graph, int from, int to, int cost){
        graph.computeIfAbsent(from, k -> new ArrayList<Edge>()).add(new Edge(from, to, cost));
    }
}
