import java.util.LinkedList;
import java.util.Queue;

/**
 * 다리를 지나는 트럭
 */
class Solution {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> bridge = new LinkedList<>();
        Queue<Integer> truck = new LinkedList<>();

        for (int truck_weight : truck_weights) {
            truck.offer(truck_weight);
        }

        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        while (!truck.isEmpty()) {
            answer++;
            bridge.poll();

            int total = getTotal(bridge) + truck.peek();

            if (weight >= total) {
                bridge.offer(truck.poll());
            } else {
                bridge.offer(0);
            }

        }

        return answer + bridge_length;

    }

    private int getTotal(Queue<Integer> bridge) {
        int total = 0;
        for (int b : bridge) {
            total += b;
        }
        return total;
    }

}
