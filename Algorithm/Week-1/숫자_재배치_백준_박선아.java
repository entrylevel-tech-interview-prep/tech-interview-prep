package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *  문제 유형 : DFS
 *  시간 복잡도 : O(V + E)
 *  풀이 방법 :
 *  int[] output 을 통해 한자리 씩 스와핑하여 순열 찾기
 */

public class 숫자_재배치_백준_박선아 {

  public static int answer;
  public static int numB;

  public static int findPermutation(String a, String b) {
    int[] arr = arrA(a);
    Arrays.sort(arr);
    int[] output = new int[a.length()];
    boolean[] visited = new boolean[a.length()];
    numB = Integer.parseInt(b);
    answer = -1;

    permute(arr, output, visited, 0, a.length());
    return answer;
  }

  /**
   * DFS 이용하여 순서를 지키며
   * 순열시킬 숫자에서 n번째 위치의 각 위치 방문 했는지 확인을 하며
   *
   * 789 798 879 897 987
   */
  private static void permute(int[] arr, int[] output, boolean[] visited, int n, int length) {
    if (n == length) {
      int currVal = currVal(output);
      if(output[0] != 0 && currVal < numB) {
        answer = currVal;
      }
       return;
    }

    for (int i = 0; i < length; i++) {
      if (! visited[i]) {
        visited[i] = true;
        output[n] = arr[i];
        permute(arr, output, visited, n+1, length);
        visited[i] = false;
      }
    }
  }

  private static int currVal (int[] output) {
    StringBuilder result = new StringBuilder();
    for(int curr : output) {
      result.append(curr);
    }
    return Integer.parseInt(result.toString());
  }


  private static int[] arrA(String a) {
    int[] result = new int[a.length()];
    for(int i = 0; i < a.length(); i++) {
      result[i] = Character.getNumericValue(a.charAt(i));
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");
    String a = input[0];
    String b = input[1];

    System.out.println(findPermutation(a, b));
  }
}
