package com.mahendra.problems;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Program to find the shortest path between 2 points in a maze(matrix). The same can be used to find the shortest path to find an obstacle
 */
public class ShortestPathMaze {

    private static int noOfRows = 0;
    private static int noOfColumns = 0;

    // 4 possible movements
    private static final int row[] = { -1, 0, 0, 1 };
    private static final int col[] = { 0, -1, 1, 0 };

    private static boolean isValid(int maze[][], boolean visited[][], int x, int y) {
        if((x >= 0 && x < noOfRows) && (y >= 0 && y < noOfColumns) && maze[x][y] == 1 && visited[x][y] == false) {
            return true;
        }

        return false;
    }

    // find the shortest between 2 nodes using breadth first traversal
    private static int findShortestPath(final int[][] maze, final int source_x, final int source_y,
            final int destination_x, final int destination_y) {

        if(maze[source_x][source_y] != 1 || maze[destination_x][destination_y] != 1) {
            System.out.println("Invalid source or destination");
            return -1;
        }

        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[noOfRows][noOfColumns];

        queue.add(new Node(source_x, source_y, 0));
        visited[source_x][source_y] = true;

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();

            int current_x = currentNode.x;
            int current_y = currentNode.y;
            int currentDistance = currentNode.distance;

            /*
             *  condition for checking the obstacle
            if(maze[current_x][current_y] == 9) {
                return currentDistance;
            }*/

            if(current_x == destination_x && current_y == destination_y) {
                return currentDistance;
            }

            for(int i = 0; i < 4; i++) {
                if(isValid(maze, visited, current_x + row[i], current_y + col[i])) {
                    queue.add(new Node(current_x + row[i], current_y + col[i], currentDistance + 1));
                    visited[current_x + row[i]][current_y + col[i]] = true;
                }
            }
        }

        return -1;
    }

    public static void main(final String[] args) {
        // input maze
        final int[][] maze = {
                { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
                { 0, 0, 1, 0, 1, 1, 1, 0, 0, 1 },
                { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1 },
                { 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
                { 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
                { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                { 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 }
        };

        noOfRows = maze.length;
        noOfColumns = maze[0].length;

        // Find shortest path from source (0, 0) to
        // destination (7, 5)
        int shortestDistance = findShortestPath(maze, 0, 0, 7, 5);
        System.out.println("Shortest Distance " + shortestDistance);
    }
}

class Node {
    int x;
    int y;
    int distance;

    Node(final int x, final int y, final int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}
