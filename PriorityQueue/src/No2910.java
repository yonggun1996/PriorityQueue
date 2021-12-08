package Avatar;
import java.io.*;
import java.util.*;

public class No2910 {

    /*
    빈도수에 따른 정렬 문제
    n이 주어지는데 n만큼 수를 입력 받고 숫자, 첫 등장 인덱스, 등장횟수를 담은 클래스를 선언
    map에 넣은 후 map을 꺼내 우선순위큐에 삽입 후 다시 꺼낸다.
     */

    static HashMap<Integer, CountData> map = new HashMap<>();//각 번호당 첫 등장 인덱스, 등장횟수를 담은 해시맵

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(!map.containsKey(num)){//맵에 숫자가 없다면 숫자, 첫 등장 인덱스, 등장횟수 1을 추가한다.
                map.put(num, new CountData(num, i, 1));
            }else{
                CountData cd = map.get(num);
                cd.count += 1;
                map.put(num, cd);//맵에 숫자가 있다면 등장횟수를 하나 더해 다시 맵에 넣는다
            }
        }

        //우선순위큐
        //등장횟수가 많은 순서가 첫 번째
        //등장횟수가 같으면 먼저 입력받은 숫자가 나오도록 설정
        PriorityQueue<CountData> pq = new PriorityQueue<>(new Comparator<CountData>(){
            @Override
            public int compare(CountData cd1, CountData cd2){
                if(cd1.count == cd2.count){
                    return cd1.index - cd2.index;
                }
                return cd2.count - cd1.count;
            }
        });

        //맵에 키값을 for문으로 돌려 우선순위 큐에 담는다.
        for(int i : map.keySet()){
            pq.offer(map.get(i));
        }

        StringBuilder answer = new StringBuilder();
        while(!pq.isEmpty()){//우선순위 큐에 있는 데이터를 순차적으로 꺼내 입력한 숫자를 붙인다.
            CountData cd = pq.poll();
            for(int i = 0; i < cd.count; i++){
                answer.append(cd.num).append(" ");
            }
        }

        System.out.println(answer);
    }
}

class CountData{
    int num;
    int index;
    int count;

    public CountData(int num, int index, int count){
        this.num = num;
        this.index = index;
        this.count = count;
    }
}
