import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 16985 : Maaaaaaaaaze
 *
 * 소요 시간 : 7시간
 *
 * 삽질했던 이유 : 시계방향으로 돌리는 것과 층을 만드는 순열을 같이 하려고해서 복잡해짐
 *
 * 문제 해결 방법 : 1. 순열로 각 층별 만들 수 있는 경우의 수를 모두 구한다.
 *               2. 5층이 완성되는 순간 층별로 회전하는 경우를 bfs로 탐색한다.
 *                                  -> bfs 못 만들어서 5중 for문 돌림
 *               3. 회전되는 경우의 수가 만들어졌을 때 bfs로 최단 거리를 구한다.
 *
 */
public class Main {

    // 상,하,좌,우, 아랫칸, 윗칸
    static int[] X = new int[]{-1, 1, 0, 0, 0, 0};
    static int[] Y = new int[]{0, 0, -1, 1, 0, 0};
    static int[] Z = new int[]{0, 0, 0, 0, -1, 1};

    static boolean[] visit = new boolean[5];
    static int[][][] inputMaze = new int[5][5][5];              // 입력받은 3차원 배열
    static int[][][] floorMaze = new int[5][5][5];              // 층별 순열을 담아줄 3차원 배열
    static boolean[][][] check;                                 // bfs 탐색시 방문 여부를 확인
    static int[][][] count;                                     // bfs 탐색시 소요시간을 카운트하는 배열

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // 입력받는 메소드
        inputArray();

        // 순열을 통해 만들 수 있는 모든 미로를 만든다.
        permutation(0);

        // 도착하지 못 하는 경우라면
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(answer);

    }

    private static void inputArray() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    inputMaze[i][j][k] = Integer.parseInt(st.nextToken());

                }
            }
        }
    }

    private static void permutation(int deep) {
        if (deep == 5) {
            //미로가 만들어지면 회전시킨다.
            bruteforce();
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (!visit[i]) {
                visit[i] = true;
                floorMaze[i] = inputMaze[deep];
                permutation(deep + 1);
                visit[i] = false;
            }
        }
    }

    private static void bruteforce() {
        int[][][] tmp = new int[5][5][5];
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 1; k < 5; k++) {
                    for (int l = 1; l < 5; l++) {
                        for (int m = 1; m < 5; m++) {
                            tmp[0] = rotation(i, 0);
                            tmp[1] = rotation(j, 1);
                            tmp[2] = rotation(k, 2);
                            tmp[3] = rotation(l, 3);
                            tmp[4] = rotation(m, 4);

                            if (tmp[0][0][0] == 1 && tmp[4][4][4] == 1) {
                                int count = bfs(tmp);
                                if (count != 0) {
                                    answer = Math.min(answer, count);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static int[][] rotation(int count, int floor) {
        int[][] tmp = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                switch (count) {
                    case 1:     // 1회전
                        tmp[i][j] = floorMaze[floor][4-j][i];
                        break;
                    case 2:     // 2회전
                        tmp[i][j] = floorMaze[floor][4-i][4-j];
                        break;
                    case 3:     // 3회전
                        tmp[i][j] = floorMaze[floor][j][4-i];
                        break;
                    case 4:     // 4회전 == 제자리
                        tmp[i][j] = floorMaze[floor][i][j];
                        break;
                }
            }
        }
        return tmp;
    }

    private static int bfs(int[][][] array) {
        //시작점인 0,0,0 집어넣는다.
        int[] xyz = new int[3];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(xyz);
        check = new boolean[5][5][5];
        count = new int[5][5][5];

        check[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int z = poll[2];

            //상하좌우이동
            for (int i = 0; i < 6; i++) {
                int tmpX = x + X[i];
                int tmpY = y + Y[i];
                int tmpZ = z + Z[i];

                //미로 범위 안인지 확인
                if (isBound(tmpX, tmpY, tmpZ)) {
                    //이동 가능한지, 방문한적이 있는지 확인
                    if (array[tmpZ][tmpX][tmpY] == 1 && !check[tmpZ][tmpX][tmpY]) {

                        // 방문 표시
                        check[tmpZ][tmpX][tmpY] = true;

                        // 큐에 다음 좌표값
                        queue.offer(new int[]{tmpX, tmpY , tmpZ});

                        // 이동값
                        count[tmpZ][tmpX][tmpY] = count[z][x][y] + 1;
                    }
                }
            }
        }
        return count[4][4][4];
    }

    private static boolean isBound(int tmpX, int tmpY, int tmpZ) {
        return tmpX >= 0 && tmpY >= 0 && tmpX < 5 && tmpY < 5 && tmpZ >= 0 && tmpZ < 5;
    }

}
