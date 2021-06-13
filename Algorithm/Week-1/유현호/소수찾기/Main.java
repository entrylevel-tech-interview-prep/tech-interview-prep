import java.util.*;

class Main {

    public int solution(String numbers) {
        int answer = 0;

        Set<Integer> set = new HashSet<>();

        String[] split = numbers.split("");

        for (String s : split) {
            set.add(Integer.parseInt(s));
        }

        int size = 2;
        boolean[] visit;
        for (int i = 0; i < split.length - 1; i++) {
            visit = new boolean[split.length];
            String number = "";
            recursion(size, split, set, visit, number);
            size++;
        }

        List<Integer> list = new ArrayList<>(set);
        for (int number : list) {
            if (!isPrime(number)) {
                answer++;
            }
        }

        return answer;
    }

    public void recursion(int size, String[] split, Set<Integer> set, boolean[] visit, String number) {
        if (number.length() == size) {
            set.add(Integer.parseInt(number));
            return;
        }

        for (int i = 0; i < split.length; i++) {
            if (visit[i] == false) {
                visit[i] = true;
                recursion(size, split, set, visit, number + split[i]);
                visit[i] = false;
            }
        }
    }

    public boolean isPrime(int num) {
        if (num == 1 || num == 0) {
            return true;
        }
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }
}