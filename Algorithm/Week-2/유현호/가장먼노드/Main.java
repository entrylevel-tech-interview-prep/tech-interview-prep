import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 가장 먼 노드
 *
 * 풀이시간 : 40분
 *
 * bfs 사이즈로 가장 먼 노드들을 구한다.
 *
 */

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.solution(6,
                new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;

        boolean[] visit = new boolean[n + 1];
        boolean[][] nodes = new boolean[n + 1][n + 1];

        for (int[] e : edge) {
            nodes[e[0]][e[1]] = true;
            nodes[e[1]][e[0]] = true;
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        visit[1] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();

                for (int j = 1; j <= n; j++) {
                    if (!visit[j] && nodes[j][node]) {
                        queue.add(j);
                        visit[j] = true;
                    }
                }
            }
            answer = size;
        }

        return answer;
    }
}
