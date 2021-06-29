import java.util.PriorityQueue;
import java.util.Scanner;

public class MinHeap {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		int n = in.nextInt();
		for(int i = 0; i < n; i++) {
			int num = in.nextInt();
			
			if(num == 0 && pq.isEmpty()) {
				System.out.println(0);
			}else if(num == 0 && !pq.isEmpty()) {
				System.out.println(pq.poll());
			}else {
				pq.offer(num);
			}
		}
	}

}
