package week2;

import java.util.LinkedList;
import java.util.Queue;
/*
 * 시간 복잡도 : O(N+E)
 * 풀이 방법 :
 *   BFS - 인접행렬 + Queue
 *     1 2 3 4 5 6
 * 1     t t
 * 2   t     t t
 * 3   t t t t   t
 * 4     t t t
 * 5     t     t
 * 6       t     t
 *
 *
 * update int[]
 * => position[i] == 0  && visited
 *
 * Queue : 1: 2 > 3
 * int[] 0 1 2 3 4 5 6
 *       0 0 1 1 0 0 0
 * Queue : 2: 3>4>5
 * int[] 0 1 2 3 4 5 6
 *       0 0 1 1 2 0 0
 * Queue : 3: 4>5>6
 * int[] 0 1 2 3 4 5 6
 *       0 0 1 1 2 2 2
 */

public class 가장먼노드_프로그래머스_박선아 {

    public static int solution(int n, int[][] edge) {
      int answer = 0;
      int position[] = new int[n+1]; // 1과의 거리 위치 position
      boolean visit[][] = new boolean[n+1][n+1];
      for(int i=0; i<edge.length; i++){
        visit[edge[i][0]][edge[i][1]] = true;
        visit[edge[i][1]][edge[i][0]] = true;
      }

      Queue<Integer> queue = new LinkedList<>();
      queue.add(1);
      int max = 0;

      while(!queue.isEmpty()){
        int idx = queue.poll();
        for(int j=2; j<=n; j++){
          if(position[j] == 0 && visit[idx][j]){
            position[j] = position[idx] + 1;
            queue.add(j);
          }
          max = Math.max(max, position[j]);
        }
      }

      for(int i=0; i<=n; i++){
        if(max == position[i]) answer++;
      }

      return answer;
    }

    public static void main(String[] args) {
      int[][] computers = {{3,6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
      System.out.println(solution(6, computers));
    }

}
