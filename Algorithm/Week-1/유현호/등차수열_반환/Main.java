import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 등차수열 반환
 */
public class Main {

    static int[][] nums;
    static int ANSWER = Integer.MAX_VALUE;
    static int DISTANCE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = new int[N][3];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < nums.length; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i][0] = num - 1;
            nums[i][1] = num;
            nums[i][2] = num + 1;
        }

        int tmp = nums[0][1];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (tmp == nums[i][1]) {
                count++;
            }
        }

        if (count == N) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < 3; i++) {
            int num = nums[0][i];

            if (i != 1) {
                dfs(num, 1, num - nums[1][0], 1);
                dfs(num, 1, num - nums[1][1], 1);
                dfs(num, 1, num - nums[1][2], 1);
            } else {
                dfs(num, 1, num - nums[1][0], 0);
                dfs(num, 1, num - nums[1][1], 0);
                dfs(num, 1, num - nums[1][2], 0);
            }
        }

        if (DISTANCE == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(ANSWER);

    }

    private static void dfs(int num, int depth, int distance, int answer) {
        if (depth == nums.length) {
            DISTANCE = Math.min(DISTANCE, distance);
            ANSWER = Math.min(ANSWER, answer);
            return;
        }

        for (int i = 0; i < 3; i++) {

            if (depth < nums.length) {
                int tmp = num - nums[depth][i];

                if (tmp == distance) {
                    if (i != 1) {
                        dfs(nums[depth][i], depth + 1, distance, answer + 1);
                    } else {
                        dfs(nums[depth][i], depth + 1, distance, answer);
                    }
                }
            }

        }

    }
}

