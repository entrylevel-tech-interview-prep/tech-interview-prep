import java.util.PriorityQueue;

/**
 * 섬 연결하기
 *
 * 유형 : MST
 * 크루스칼 알고리즘
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        main.solution(6, new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}});
    }

    static int[] parent;

    public int solution(int n, int[][] costs) {
        int answer = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < costs.length; i++) {
            int v = costs[i][0];
            int next = costs[i][1];
            int cost = costs[i][2];
            queue.add(new Node(v, next, cost));
        }


        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (check(node.v, node.next)) {
                answer += node.cost;
                union(node.v, node.next);
            }
        }

        return answer;
    }

    // 부모가 같은지 다른지 판단
    public boolean check(int x, int y) {
        return find(x) != find(y);
    }

    public void union(int x, int y) {
        int a = find(x);
        int b = find(y);

        // 부모를 더 작은 수로 저장
        if (a > b) {
            b = a + b;              // 숫자 교환하는 부분
            a = b - a;              // temp 없이 교환하는 방법
            b = b - a;
        }

        parent[b] = a;
    }

    public int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}

class Node implements Comparable<Node> {
    int v;
    int next;
    int cost;

    Node(int v, int next, int cost) {
        this.v = v;
        this.next = next;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}