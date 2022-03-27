package com.letscodefortest.medium.bfs;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/shortest-bridge/

/**
 * time complexity: O(N^2)
 * space complexity: O(N^2)
 * Runtime: 635 ms, faster than 5.04% of Java online submissions for Shortest Bridge.
 * Memory Usage: 43.8 MB, less than 80.48% of Java online submissions for Shortest Bridge.
 * BFS를 통해 섬의 영역을 찾아준 후, 다시 각 섬의 각 지점으로부터 BFS를 시행해 다른 섬을 찾는다
 */
public class Leetcode_Shortest_Bridge_q934 {

    static Solution1 s1 = new Solution1();

    static class Solution1 {
        int[][] grid;
        int size;
        int min = Integer.MAX_VALUE;

        int[][] island;
        int[] xs = {0, -1, 0, 1};
        int[] ys = {-1, 0, 1, 0};

        public int shortestBridge(int[][] grid) {
            this.grid = grid;
            this.size = grid.length;
            getStartingPoint();
            for (int i = 0; i < island.length; i++) {
                for (int j = 0; j < island[i].length; j++) {
                    if (island[i][j] == 1) {
                        findShortestPathBFS(i, j);
                    }
                }
            }
            return min;
        }

        private void getStartingPoint() {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 1) {
                        paintIsland(i, j);
                        return;
                    }
                }
            }
        }

        // TODO: border인 경우만 따로 표시해서 조금더 최적화 가능할수도
        private void paintIsland(int i, int j) {
            island = new int[size][size];
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(i, j));
            island[i][j] = 1;

            while (!queue.isEmpty()) {
                Point point = queue.poll();

                for (int k = 0; k < 4; k++) {
                    int nx = point.x + xs[k];
                    int ny = point.y + ys[k];

                    if (nx < 0 || nx >= size || ny < 0 || ny >= size) continue;

                    if (island[nx][ny] == 0 && grid[nx][ny] == 1) {
                        island[nx][ny] = 1;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }

        private void findShortestPathBFS(int i, int j) {
            int[][] visited = new int[size][size];
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(i, j, 0));
            visited[i][j] = 1;

            while (!queue.isEmpty()) {
                Point point = queue.poll();

                for (int k = 0; k < 4; k++) {
                    int nx = point.x + xs[k];
                    int ny = point.y + ys[k];

                    if (nx < 0 || nx >= size || ny < 0 || ny >= size) continue;

                    if (island[nx][ny] == 0 && visited[nx][ny] == 0) {
                        if (grid[nx][ny] == 0) {
                            visited[nx][ny] = 1;
                            queue.add(new Point(nx, ny, point.cnt + 1));
                        } else {
                            min = Math.min(min, point.cnt);
                        }
                    }
                }
            }
        }

        class Point {
            int x, y, cnt;

            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            Point(int x, int y, int cnt) {
                this(x, y);
                this.cnt = cnt;
            }
        }
    }

    static class Solution2 {
        /**
         * Use DFS + BFS to solve this WONDERFUL problem!
         * Step 1: use DFS to mark the first island to another number
         * Step 2: start from the first island, doing BFS level order traversal to find number of bridges (levels)
         * until reach the second island
         * */
        public int shortestBridge(int[][] grid) {
            if (grid.length == 0) {
                return 0;
            }

            int n = grid.length;
            int m = grid[0].length;
            Queue<int[]> queue = new LinkedList<>();
            boolean marked = false;

            // DFS to mark the all positions of first island to 2
            for (int i = 0; i < n; i++) {
                // WARNING: MUST use a boolean variable to check if we already marked the first island to 2. Otherwise,
                // we will only break from the inner loop, but will not jump out the outer loop
                if (marked) {
                    break;
                }
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        // WARNING: MUST add all position of first island into queue as first level, they all can be the
                        // starting points of BFS level order traversal
                        dfs(grid, i, j, queue);
                        marked = true;
                        break;
                    }
                }
            }

            // BFS level order traversal: to count number of levels before finding the second island
            int bridge = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] curPoint = queue.poll();
                    int x = curPoint[0];
                    int y = curPoint[1];

                    // WARNING: CANNOT use if else statement, must use all if statement to check all four directions
                    if (x > 0 && grid[x - 1][y] == 0) {
                        queue.offer(new int[]{x - 1, y});
                        grid[x - 1][y] = 2; // border 확장
                    }
                    if (x < n - 1 && grid[x + 1][y] == 0) {
                        queue.offer(new int[]{x + 1, y});
                        grid[x + 1][y] = 2;
                    }
                    if (y > 0 && grid[x][y - 1] == 0) {
                        queue.offer(new int[]{x, y - 1});
                        grid[x][y - 1] = 2;
                    }
                    if (y < m - 1 && grid[x][y + 1] == 0) {
                        queue.offer(new int[]{x, y + 1});
                        grid[x][y + 1] = 2;
                    }

                    // once we find the second island, return current bridge value
                    if (x > 0 && grid[x - 1][y] == 1 || x < n - 1 && grid[x + 1][y] == 1
                            || y > 0 && grid[x][y - 1] == 1 || y < m - 1 && grid[x][y + 1] == 1) {
                        return bridge;
                    }
                }
                bridge++;
            }
            return bridge;
        }

        public void dfs(int[][] grid, int i, int j, Queue<int[]> queue) {
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
                return;
            }

            grid[i][j] = 2;
            queue.offer(new int[]{i, j});
            dfs(grid, i - 1, j, queue);
            dfs(grid, i + 1, j, queue);
            dfs(grid, i, j - 1, queue);
            dfs(grid, i, j + 1, queue);
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.shortestBridge(new int[][]{
//                {0, 1},
//                {1, 0},
        }));
    }
}
