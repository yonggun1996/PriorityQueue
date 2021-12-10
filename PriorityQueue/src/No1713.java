package Avatar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No1713 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        //추천 횟수가 낮은 유저가 먼저 나가는데, 추천 횟수가 같으면 나중에 들어온 후보가 나가도록 설정
        PriorityQueue<Suggestion> pq = new PriorityQueue<>(new Comparator<Suggestion>() {
            @Override
            public int compare(Suggestion o1, Suggestion o2) {
                if(o1.sug_Count == o2.sug_Count){
                    return o1.time - o2.time;
                }
                return o1.sug_Count - o2.sug_Count;
            }
        });

        int[] count_Arr = new int[101];//학생수는 최대 100명이고 편하게 계산하기 위해 101개의 int배열 생성
        Queue<Suggestion> queue = new LinkedList<>();//우선순위큐에서 데이터를 찾는 동안 빼낸 데이터를 담는 큐
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < c; i++){
            int s = Integer.parseInt(st.nextToken());

            if(count_Arr[s] > 0){//후보 중에서 추천을 받은 경우
                while(!pq.isEmpty()){
                    //우선순위 큐에 데이터를 빼내면서
                    // 해당 학생이 발견되면 추천수를 늘리고 그렇지 않으면 큐에 넣는다
                    Suggestion su = pq.poll();
                    if(su.student != s){
                        queue.offer(su);
                    }else{
                        pq.offer(new Suggestion(su.time, su.sug_Count + 1, su.student));
                        count_Arr[s]++;
                        break;
                    }
                }

                while(!queue.isEmpty()){//큐에 담긴 데이터를 다시 우선순위 큐에 담는 과정
                    pq.offer(queue.poll());
                }

            }else if(pq.size() < n){//후보가 n명 아래인 경우 그냥 우선순위 큐에 넣는다
                pq.offer(new Suggestion(i, 1, s));
                count_Arr[s]++;
            }else{//후보가 n명이 됐는데 다른 후보가 추천되면 우선순위에 따른 후보는 제외되고 새 후보가 들어간다
                Suggestion su = pq.poll();
                count_Arr[su.student] = 0;

                pq.offer(new Suggestion(i, 1, s));
                count_Arr[s]++;
            }
        }

        //리스트에 삽입 후 정렬
        ArrayList<Integer> list = new ArrayList<>();
        while(!pq.isEmpty()){
            Suggestion su = pq.poll();
            list.add(su.student);
        }

        Collections.sort(list);
        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + " ");
        }
    }
}

class Suggestion{
    int time;
    int sug_Count;
    int student;

    public Suggestion(int time, int suggestion, int student){
        this.time = time;
        this.sug_Count = suggestion;
        this.student = student;
    }
}
