import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int hu = Integer.parseInt(st.nextToken());
        int yang = Integer.parseInt(st.nextToken());
        int ban = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int answer = 0;

        if (hu + yang < ban * 2) {
            answer = (x * hu) + (y * yang);
        } else {
            if (x > y) {
                int tmp = x - y;
                answer = (x - tmp) * ban * 2;
                answer += Math.min(tmp * hu, tmp * ban * 2);
            }

            if (x < y) {
                int tmp = y - x;
                answer = (y - tmp) * ban * 2;
                answer += Math.min(tmp * yang, tmp * ban * 2);
            }

            if (x == y) {
                answer = ban * x * 2;
            }
        }

        System.out.println(answer);

    }
}