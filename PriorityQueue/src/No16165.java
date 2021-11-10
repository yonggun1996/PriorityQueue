package com.company;

/*
백준 알고리즘 16165번 문제
걸그룹 이름이 나온다면 그룹 이름을 정렬해 출력 -> PriorityQueue를 선언해 각 멤버들을 정렬이 된 채로 HashMap에 담는다
멤버 이름이 나온다면 해당 멤버의 그룹을 출력 -> 각 멤버들을 <이름 - 그룹> 형식으로 HashMap에 담는다
다른 사람의 코드에서는 TreeSet을 사용했다. 이는 Red-Black트리를 사용하는데 이게 더 시잔적으로 효율적이었다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No16165 {

    static HashMap<String, String> member_Map = new HashMap<>();//각 멤버가 어떤 그룹에 속해있는지 정하는 해시맵
    static HashMap<String, PriorityQueue<String>> group_Map = new HashMap<>();//그룹 내 멤버들을 정렬해기 위해 PriorityQueue로 설정

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            String group = br.readLine();//그룹이름
            int member_count = Integer.parseInt(br.readLine());//그룹의 인원수

            PriorityQueue<String> pq = new PriorityQueue<>();
            for(int j = 0; j < member_count; j++){
                String member = br.readLine();//멤버명
                pq.offer(member);//PriorityQueue에 삽입
                member_Map.put(member, group);//각 멤버의 그룹 지정
            }
            group_Map.put(group, pq);//그룹 내 멤버들의 우선순위 큐 삽입
        }

        for(int i = 0; i < m; i++){
            String str = br.readLine();
            int idx = Integer.parseInt(br.readLine());
            if(idx == 0){//그룹 내 인원을 알고 싶을 경우
                PriorityQueue<String> pq = new PriorityQueue<>(group_Map.get(str));//새로운 우선순위 큐 생성
                while(!pq.isEmpty()){
                    System.out.println(pq.poll());
                }
            }else{//해당 멤버의 그룹을 알고 싶은 경우
                System.out.println(member_Map.get(str));
            }
        }
    }
}
