import java.util.ArrayList;
import java.util.List;

/**
 * 소요시간 : 15분
 *
 * 풀이 방법 : 처리하는 요일을 리스트에 담고 배포가 같이 가능한지 파악한다.
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.solution(new int[]{93, 30, 55}, new int[]{1, 30, 5});

    }
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        int[] success = new int[progresses.length];

        for (int i = 0; i < progresses.length; i++) {
            int progress = progresses[i];
            int speed = speeds[i];

            if ((100 - progress) % speed == 0) {
                success[i] = (100 - progress) / speed;
            } else {
                success[i] = ((100 - progress) / speed) + 1;
            }
        }

        int tmp = success[0];
        int clear = 1;

        // 3 11
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < success.length; i++) {
            if (tmp < success[i]) {
                tmp = success[i];
                result.add(clear);
                clear = 1;
            } else {
                clear++;
            }
        }
        result.add(clear);

        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }



        return answer;
    }

}
