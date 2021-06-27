import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MaxHeap {
	
	static int[] heap = new int[100001];
	static int pt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			if(num == 0) {
				if(heap[1] == 0) {
					//System.out.println(0);
					bw.write(String.valueOf(0));
					bw.newLine();
				}else {
					//System.out.println(heap[1]);
					bw.write(String.valueOf(heap[1]));
					bw.newLine();
					//삭제
					remove();
					pt--;
				}
			}else {
				//삽입
				pt++;
				insert(num);
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	public static void insert(int num) {
		heap[pt] = num;
		
		if(pt / 2 > 0) {
			int childidx = pt;
			int parentidx = pt / 2;
			
			while(heap[childidx] > heap[parentidx] && childidx > 1) {
				int swap = heap[parentidx];
				heap[parentidx] = heap[childidx];
				heap[childidx] = swap;
				childidx = parentidx;
				parentidx /= 2;
			}
		}
	}
	
	public static void remove() {
		heap[1] = heap[pt];
		heap[pt] = 0;
		
		int point = 1;
		int node1 = 2;
		int node2 = 3;
		
		while(heap[point] < heap[node1] || heap[point] < heap[node2]) {//두 자식노드중 하나라도 크면 실행
			if(heap[node1] >= heap[node2]) {
				int swap = heap[point];
				heap[point] = heap[node1];
				heap[node1] = swap;
				point = node1;
			}else {
				int swap = heap[point];
				heap[point] = heap[node2];
				heap[node2] = swap;
				point = node2;
			}
			
			node1 = point * 2;
			node2 = point * 2 + 1;
		}
	}

}
