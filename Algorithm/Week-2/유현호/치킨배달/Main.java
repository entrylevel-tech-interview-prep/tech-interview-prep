package beakjoon.b15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이시간 : 2시간
 *
 * 삽질한 부분 : 치킨집으로 도착하는 거리가 합이 같은 경우가 있는 부분을 체크하지 못 하고 우선순위큐로 문제를 풀이했다.
 *
 * 풀이방법 설명 : 1. bfs로 시작점을 집으로 잡고 집에서 치킨집까지 가는 거리를 모두 더한다. 그럼 각 치킨집에는 모든 집에서 온 거리의 합이 담기게됨
 *              2. bfs로 시작점을 치킨집으로 잡고 치킨집에서 집까지 가는 최소 거리를 구한다.
 *              -> 치킨집이 5개인데 2개만 구해야되는 경우가 있다.
 *                 이점을 해결하기 위해 조합을 이용하여 2개의 치킨집에서 집으로 가는 최소거리를 비교해가며 출력
 *
 * 말로 설명하기 어려우니 이해가 안되면 연락주세요 그림으로 설명해드릴게요.
 */
public class Main {

    static int[] X = new int[]{-1, 1, 0, 0};
    static int[] Y = new int[]{0, 0, -1, 1};
    static int N;
    static int M;
    static int[][] street;
    static int[][] count;
    static int[][] chickenHouseCount;
    static int[][] chickenDistanceCount;
    static boolean[][] visit;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        street = new int[N][N];
        chickenHouseCount = new int[N][N];

        List<int[]> goToChickenHouse = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                street[i][j] = Integer.parseInt(st.nextToken());

                if (street[i][j] == 1) {
                    goToChickenHouse.add(new int[]{i, j});
                }
            }
        }

        for (int[] house : goToChickenHouse) {
            bfsChickenHouse(house);
        }

        List<int[]> backToHome = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (chickenHouseCount[i][j] != 0) {
                    backToHome.add(new int[]{i, j});
                }
            }
        }

        boolean[] visit = new boolean[backToHome.size()];

        combination(backToHome, visit, 0, backToHome.size(), M);

        System.out.println(answer);

    }

    static void combination(List<int[]> list, boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            chickenDistanceCount = new int[N][N];
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    bfsHouse(list.get(i));
                }
            }

            int result = 0;
            for (int[] line : chickenDistanceCount) {
                for (int count : line) {
                    result += count;
                }
            }
            answer = Math.min(answer, result);
            return;
        }

        for (int i = depth; i < n; i++) {
            visited[i] = true;
            combination(list, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }


    private static void bfsChickenHouse(int[] house) {
        count = new int[N][N];
        visit = new boolean[N][N];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(house);

        visit[house[0]][house[1]] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            for (int i = 0; i < 4; i++) {
                int tmpX = x + X[i];
                int tmpY = y + Y[i];

                if (tmpX >= 0 && tmpX < N && tmpY >= 0 && tmpY < N) {
                    if (!visit[tmpX][tmpY]) {
                        visit[tmpX][tmpY] = true;

                        count[tmpX][tmpY] = count[x][y] + 1;

                        if (street[tmpX][tmpY] == 2) {
                            chickenHouseCount[tmpX][tmpY] += count[tmpX][tmpY];
                        }

                        queue.offer(new int[]{tmpX, tmpY});
                    }
                }

            }
        }

    }

    private static void bfsHouse(int[] xy) {
        count = new int[N][N];
        visit = new boolean[N][N];
        visit[xy[0]][xy[1]] = true;
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(xy);

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            for (int i = 0; i < 4; i++) {
                int tmpX = x + X[i];
                int tmpY = y + Y[i];

                if (tmpX >= 0 && tmpX < N && tmpY >= 0 && tmpY < N) {
                    if (!visit[tmpX][tmpY]) {
                        visit[tmpX][tmpY] = true;
                        count[tmpX][tmpY] = count[x][y] + 1;

                        if (street[tmpX][tmpY] == 1) {
                            if (chickenDistanceCount[tmpX][tmpY] == 0) {
                                chickenDistanceCount[tmpX][tmpY] = count[tmpX][tmpY];
                            } else {
                                chickenDistanceCount[tmpX][tmpY] = Math
                                        .min(chickenDistanceCount[tmpX][tmpY], count[tmpX][tmpY]);
                            }

                        }

                        queue.offer(new int[]{tmpX, tmpY});

                    }
                }
            }
        }

    }

}