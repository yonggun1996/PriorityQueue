import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SpeakMiddle {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 < o2) {
					return 1;
				}else {
					return -1;
				}
			}
		});
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			if(maxHeap.size() == minHeap.size()) {
				if(!minHeap.isEmpty() && minHeap.peek() < num) {
					int swap = minHeap.poll();
					minHeap.offer(num);
					maxHeap.offer(swap);
				}else {
					maxHeap.offer(num);
				}
			}else {
				if(maxHeap.peek() > num) {
					int swap = maxHeap.poll();
					maxHeap.offer(num);
					minHeap.offer(swap);
				}else {
					minHeap.offer(num);
				}
			}
			
			bw.write(String.valueOf(maxHeap.peek()));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}
	
}
