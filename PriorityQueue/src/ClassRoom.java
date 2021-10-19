package Stack;
import java.io.*;
import java.util.*;

/*
도움이 된 블로그 : https://velog.io/@jkh9615/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-11000-%EA%B0%95%EC%9D%98%EC%8B%A4-%EB%B0%B0%EC%A0%95-Java
우선순위 큐를 이용해 사용중인 강의실 중 빨리 끝나는 곳과 써야하는 강의 시작시간을 비교
강의 시작시간이 큐에 담긴 시간보다 같거나 크면 우선순위 큐에 데이터를 빼고 해당 시간의 끝 시간을 넣는다
그렇지 않으면 우선순위 큐에 해당 시간도 큐에 넣는다
 */

public class ClassRoom {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];//강의 시작시간과 끝 시간이 담긴 배열

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        //그리디 알고리즘도 사용하기 때문에 배열을 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        PriorityQueue<Integer> endtime = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            int start = arr[i][0];
            int end = arr[i][1];

            if(endtime.isEmpty()){
                endtime.offer(end);
            }else if(start >= endtime.peek()){
                endtime.poll();
                endtime.offer(end);
            }else{
                endtime.offer(end);
            }

        }

        System.out.println(endtime.size());//강의실을 쓸 수 있는 개수

    }
}