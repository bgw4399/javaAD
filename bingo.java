package AD_Project;
import java.util.Scanner;

public class bingo {
    public static void main(String[] args) {
        final int SIZE = 5;
        int x = 0, y = 0, num = 0;
        int totalCnt = 0;
        int cnt = 0;

        int[][] bingo = new int[SIZE][SIZE];
        Scanner scanner = new Scanner(System.in);

// 배열의 모든 요소를 1부터 SIZE*SIZE까지의 숫자로 초기화
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                bingo[i][j] = i * SIZE + j + 1;
            }
        }

// 배열에 저장된 값을 뒤섞는다.
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                x = (int) (Math.random() * SIZE);
                y = (int) (Math.random() * SIZE);

                // bingo[i][j]와 임의로 선택된 값(bingo[x][y])을 바꾼다.
                int tmp = bingo[i][j];
                bingo[i][j] = bingo[x][y];
                bingo[x][y] = tmp;
            }
        }
        // 빙고판 출력
        System.out.println("    # Bingo Board #\n");
        do {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (bingo[i][j] == 0) {
                        System.out.printf("%-5s", "*");
                    } else
                        System.out.printf("%-5d", bingo[i][j]);
                }
                System.out.println();
            } // 빙고 카운트
            System.out.println("--------------------------");
            System.out.printf("1~%d의 숫자를 입력하세요. > ", SIZE * SIZE);
            String tmp = scanner.nextLine(); // 화면에서 입력받은 내용을 tmp에 저장
            num = Integer.parseInt(tmp); // 입력받은 문자열(tmp)를 숫자로 변환

            // 입력받은 숫자와 같은 숫자가 저장된 요소를 찾아서 0을 저장
            outer: for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (bingo[i][j] == num) {
                        bingo[i][j] = 0;
                        break outer;
                        // 2중 반복문 나가기
                    }
                }
            }
            if (totalCnt == 1) {
                System.out.println("    Total: 1 빙고");
                totalCnt = 0;
            } else if (totalCnt == 2) {
                System.out.println("    Total: 2 빙고");
                totalCnt = 0;
            } else if (totalCnt == 3) {
                System.out.println("    Total: 3 빙고");
                totalCnt = 0;
            } else if (totalCnt == 4) {
                System.out.println("    Total: 4 빙고");
                totalCnt = 0;
            } else if (totalCnt == 5) {
                totalCnt = 0;
                System.out.println("       빙고 완성!");
                System.out.println("    *게임을 종료합니다*\n");
            }
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (bingo[i][j] == 0)
                        cnt++;
                }
                if (cnt == 5)
                    totalCnt++;
                cnt = 0;

                for (int j = 0; j < SIZE; j++) {
                    if (bingo[j][i] == 0)
                        cnt++;
                }
                if (cnt == 5)
                    totalCnt++;
                cnt = 0;
            } // 빙고 대각선체크
            if ((bingo[0][0] == 0) && (bingo[1][1] == 0) && (bingo[2][2] == 0) && (bingo[3][3] == 0)
                    && (bingo[4][4] == 0))
                totalCnt++;
            if ((bingo[0][4] == 0) && (bingo[1][3] == 0) && (bingo[2][2] == 0) && (bingo[3][1] == 0)
                    && (bingo[4][0] == 0))
                totalCnt++;

        } while (totalCnt <= 5);
        scanner.close();
    }

}
