package com.company;
import java.io.*;
import java.util.*;

/*
우선순위 큐를 이용한 문제
두개의 숫자 카드를 골라 작은 수를 더해야 하기 때문에 우선순위큐 사용
값이 커질수 있기 때문에 Long 타입으로 데이터를 받아야 한다.
우선순위 큐에 두개의 값을 꺼낸 후 더한다
더한 값을 두번 담는다.
이 과정을 반복한다.
 */
public class No15903 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            pq.offer(Long.parseLong(st.nextToken()));
        }

        //작은 수 2개를 꺼내 더한 후 그 수들을 다시 담는 과정
        for(int i = 0; i < m; i++){
            long num1 = pq.poll();
            long num2 = pq.poll();
            long sum = num1 + num2;

            pq.offer(sum);
            pq.offer(sum);
        }

        //우선순위 큐에 담긴 모든 값을 더해 출력한다.
        long answer = 0;
        while(!pq.isEmpty()){
            answer += pq.poll();
        }

        System.out.println(answer);
    }
}
