package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  문제 유형 : Brute Force
 *  시간 복잡도 : O(N)
 *  문제 풀이 :
 *  양념 + 후라이드 가격이 2 * 반반 했을떼로 비교를 해야합니다.
 *  방법 1. 양녑 + 후라디으 < 2 * 반반
 *    = 굳이 반반으로 계산할 필요 없이 양념과 후라이드 각각 계산하는게 최소 비용
 *  방법 2. 2 * 반반 < 양념 + 후라이드
 *  방법 2-1. 무조건 반반으로 사는게 저렴,
 *          양념과 후라이드 겹치지 않아도 반반으로 사는게 더 최소비용인 경우
 *  방법 2-2. 공통 개수만 반반으로 구매하고
 *          양념과 후라이드 중 더 개수가 많은 경우는 낮개로 계산하는것이 최소 비용인 경우
 */

public class 양념반_후라이드반_백준_박선아 {

  public static int totalMinPrice(int spicy, int original,int half, int spicyCnt, int originalCnt) {
    int minPrice = 0;

    if (spicy + original > 2 * half) {
      int minCnt = Math.min(spicyCnt, originalCnt);
      minPrice = (2 * minCnt) * half +
          Math.min(spicy * (spicyCnt - minCnt), half * 2 * (spicyCnt - minCnt)) +
          Math.min(original * (originalCnt - minCnt), half * 2 * (originalCnt - minCnt));
    } else {
      minPrice = spicyCnt * spicy + originalCnt * original;
    }
    return minPrice;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int spicy = Integer.parseInt(st.nextToken());
    int original = Integer.parseInt(st.nextToken());
    int half = Integer.parseInt(st.nextToken());
    int spicyCnt = Integer.parseInt(st.nextToken());
    int originalCnt = Integer.parseInt(st.nextToken());

    int result = totalMinPrice(spicy, original, half, spicyCnt, originalCnt);
    System.out.println(result);
  }
}