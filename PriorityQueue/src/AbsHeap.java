import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AbsHeap {
	
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			if(Math.abs(o1) > Math.abs(o2)) {
				//기존에 있던 정수의 우선순위가 먼저라면 그대로 삽입
				return 1;
			}else if(Math.abs(o1) < Math.abs(o2)) {
				//새로 들어온 정수의 우선순위가 먼저라면 교환이 이뤄진다
				return -1;
			}else {
				//절댓값이 같은 경우 큰 수가 우선순위가 된다
				if(o1 > o2) {//기존에 있던 정수의 우선순위가 먼저라면 그대로 삽입
					return 1;
				}else if(o1 < o2) {//새로 들어온 정수의 우선순위가 먼저라면 교환이 이뤄진다
					return -1;
				}else {//수가 똑같으면 그대로 삽입
					return 0;
				}
			}
		}
	});

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		for(int i = 0; i < n; i++) {
			int num = in.nextInt();
			
			if(num == 0) {
				if(pq.isEmpty()) {
					System.out.println(0);
				}else {
					System.out.println(pq.poll());
				}
			}else {
				pq.offer(num);
			}
		}
	}
}
