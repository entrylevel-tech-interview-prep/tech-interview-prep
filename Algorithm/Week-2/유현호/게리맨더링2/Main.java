package beakjoon.b17779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by yhh1056
 * Date: 2021/06/14 Time: 12:21 오전
 *
 * 소요시간 약 2시간 30분
 *
 * 만들 수 있는 구역 모양을 전부 만들어 탐색
 * 꼭지점 4개를 만들고 5구역부터 채우고 나머지 구역을 채운다.
 */
public class Main {

    static int[][] area;
    static int[][] people;

    static int answer = Integer.MAX_VALUE;

    //서
    static int leftX;
    static int leftY;

    //북
    static int topX;
    static int topY;

    //동
    static int rightX;
    static int rightY;

    //남
    static int downX;
    static int downY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        area = new int[N][N];
        people = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 1; k <= N; k++) {
                    for (int l = 1; l <= N; l++) {
                        int d1 = k;
                        int d2 = l;

                        leftX = i;
                        leftY = j;

                        topX = leftX - d1;
                        topY = leftY + d1;

                        rightX = topX + d2;
                        rightY = topY + d2;

                        downX = leftX + d2;
                        downY = leftY + d2;

                        // 꼭지점을 만든다.
                        if (topX >= 0 && topY >= 0 && topX < N && topY < N
                                && rightX < N && rightY < N
                                && downX < N && downY < N) {
                            area[leftX][leftY] = 5;
                            area[topX][topY] = 5;
                            area[rightX][rightY] = 5;
                            area[downX][downY] = 5;

                            dfsTop(leftX, leftY, topX, topY);
                            dfsRight(topX, topY, rightX, rightY);
                            dfsDown(rightX, rightY, downX, downY);
                            dfsLeft(downX, downY, leftX, leftY);


                            // 5채우기
                            int count = 0;
                            boolean tag = false;
                            for (int m = topX + 1; m < downX; m++) {
                                for (int n = 0; n < N; n++) {
                                    if (area[m][n] == 5) {
                                        tag = true;
                                        count += 1;
                                    }

                                    if (tag) {
                                        area[m][n] = 5;
                                    }

                                    if (count == 2) {
                                        break;
                                    }
                                }
                                count = 0;
                                tag = false;
                            }

                            // 1구역
                            for (int m = 0; m < leftX; m++) {
                                for (int n = 0; n <= topY; n++) {
                                    if (area[m][n] != 5) {
                                        area[m][n] = 1;
                                    }
                                }
                            }

                            // 2구역
                            for (int m = 0; m <= rightX; m++) {
                                for (int n = topY + 1; n < N; n++) {
                                    if (area[m][n] != 5) {
                                        area[m][n] = 2;
                                    }
                                }
                            }

                            // 3구역
                            for (int m = leftX; m < N; m++) {
                                for (int n = 0; n < downY; n++) {
                                    if (area[m][n] != 5) {
                                        area[m][n] = 3;
                                    }

                                }
                            }

                            // 4구역
                            for (int m = rightX + 1; m < N; m++) {
                                for (int n = downY; n < N; n++) {
                                    if (area[m][n] != 5) {
                                        area[m][n] = 4;
                                    }

                                }
                            }

                            int a1 = 0;
                            int a2 = 0;
                            int a3 = 0;
                            int a4 = 0;
                            int a5 = 0;

                            for (int m = 0; m < N; m++) {
                                for (int n = 0; n < N; n++) {
                                    if (area[m][n] == 1) {
                                        a1 += people[m][n];
                                    }

                                    if (area[m][n] == 2) {
                                        a2 += people[m][n];
                                    }

                                    if (area[m][n] == 3) {
                                        a3 += people[m][n];
                                    }

                                    if (area[m][n] == 4) {
                                        a4 += people[m][n];
                                    }

                                    if (area[m][n] == 5) {
                                        a5 += people[m][n];
                                    }
                                }
                            }

                            int max = Math.max(a1, Math.max(a2, Math.max(a3, Math.max(a4, a5))));
                            int min = Math.min(a1, Math.min(a2, Math.min(a3, Math.min(a4, a5))));

                            answer = Math.min(answer, max - min);
                        }

                        for (int[] x : area) {
                            Arrays.fill(x, 0);
                        }
                    }
                }


            }
        }
        System.out.println(answer);
    }

    private static void dfsTop(int leftX, int leftY, int topX, int topY) {
        if (leftX == topX && leftY == topY) {
            return;
        }
        leftX -= 1;
        leftY += 1;

        area[leftX][leftY] = 5;
        dfsTop(leftX, leftY, topX, topY);
    }

    private static void dfsRight(int topX, int topY, int rightX, int rightY) {
        if (topX == rightX && topY == rightY) {
            return;
        }
        topX += 1;
        topY += 1;

        area[topX][topY] = 5;
        dfsRight(topX, topY, rightX, rightY);
    }

    private static void dfsDown(int rightX, int rightY, int downX, int downY) {
        if (rightX == downX && rightY == downY) {
            return;
        }
        rightX += 1;
        rightY -= 1;

        area[rightX][rightY] = 5;
        dfsDown(rightX, rightY, downX, downY);
    }

    private static void dfsLeft(int downX, int downY, int leftX, int leftY) {
        if (downX == leftX && downY == leftY) {
            return;
        }
        downX -= 1;
        downY -= 1;

        area[downX][downY] = 5;
        dfsLeft(downX, downY, leftX, leftY);
    }
}
