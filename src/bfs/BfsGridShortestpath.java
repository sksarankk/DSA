package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BfsGridShortestpath {
    //number of row and columns
    int r, c;
    // matrix of size rxc
    char[][] m ;
    // # 'S' symbol row and column values
    int sr, sc;
    //new row and column q
    Queue<Integer> rq = new LinkedList<>();
    Queue<Integer> cq = new LinkedList<>();
    int move_count = 0;
    int nodes_left_in_layer = 1;
    int nodes_in_next_layer = 0;
    boolean reachedEnd = false;
    boolean[][] visited ;
    int[] dr = {-1, +1, 0, 0};
    int[] dc = {0, 0, +1, -1};
    ArrayList<pathCoordinate> path = new ArrayList<>();
    public BfsGridShortestpath(char[][] m, int sr, int sc){
        this.m = m;
        this.r = m.length;
        this.c = m[0].length;
        this.sr = sr;
        this.sc = sc;
        this.visited = new boolean[this.r][this.c];
    }
    public static class pathCoordinate {
        int row, col;
        public pathCoordinate(int row, int col){
            this.row = row;
            this.col = col;
        }
        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }
    public ArrayList<pathCoordinate> solve(){
        rq.offer(sr);
        cq.offer(sc);
        visited[sr][sc] = true;
        while(!rq.isEmpty()){
            int r = rq.poll();
            int c = cq.poll();
            if(m[r][c] == 'E') {
                reachedEnd = true;
                path.add(new pathCoordinate(r, c));
                break;
            }
            expolreNeighbours(r, c);
            nodes_left_in_layer--;
            if(nodes_left_in_layer == 0){
                nodes_left_in_layer = nodes_in_next_layer;
                nodes_in_next_layer = 0;
                path.add(new pathCoordinate(r, c));
            }
        }
        return reachedEnd ? path: new ArrayList<>();
    }
    public void expolreNeighbours(int row, int col){
        for (int i = 0; i < 4; i++){
            int rr = row+dr[i];
            int cc = col+dc[i];
            if (rr < 0 || cc < 0) continue;
            if ( rr >= r || cc >= c) continue;
            if(visited[rr][cc]) continue;
            if(m[rr][cc] == '#') continue;
            rq.offer(rr);
            cq.offer(cc);
            visited[rr][cc] = true;
            nodes_in_next_layer++;
        }
    }
    public static void main(String[] args) {
        char[][] m = new char[3][3];
        for (int i = 0 ;i < m.length; i++){
            for(int j = 0; j < m[0].length; j++){
                m[i][j] = '.';
            }
        }
        m[0][0] = 'S';
        m[1][1] = '#';
        m[2][1] = 'E';
        m[1][0] = '#';
        m[0][1] = '#';
        BfsGridShortestpath grid = new
                BfsGridShortestpath(m, 0, 0);
        ArrayList<pathCoordinate> path = grid.solve();
        System.out.println(path);
    }
}
