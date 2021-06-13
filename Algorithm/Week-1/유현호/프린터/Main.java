import java.util.*;

class Solution {

    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<File> queue = new LinkedList<>();
        Queue<Integer> prioty = new PriorityQueue<>(Collections.reverseOrder());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < priorities.length; i++) {
            File file = new File(i, priorities[i]);
            queue.offer(file);
            prioty.offer(priorities[i]);
        }


        int max = prioty.poll();
        while (!queue.isEmpty()) {
            File file = queue.poll();

            if (file.num == max) {
                answer++;
                if (!prioty.isEmpty()) {
                    max = prioty.poll();
                }
                list.add(file.index);
            } else {
                queue.offer(file);
            }

        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == location) {
                answer = i;
            }
        }

        return answer + 1;
    }
}

class File implements Comparator<File> {

    int index;
    int num;

    File(int index, int num) {
        this.index = index;
        this.num = num;
    }

    @Override
    public int compare(File f1, File f2) {
        return f1.num - f2.num;
    }

}

/**
 * 더 간결하고 쉬운 풀이
 */
import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] priorities, int location) {
        int count = 1;

        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for (int i : priorities) {
            pq.offer(i);
        }

        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priorities[i] == pq.peek()) {
                    if (i == location) {
                        return count;
                    }
                    pq.poll();
                    count++;
                }
            }

        }
        return count;
    }
}