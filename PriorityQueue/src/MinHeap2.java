import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MinHeap2 {
	
	static int[] heap = new int[100001];
	static int pt = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Arrays.fill(heap, Integer.MAX_VALUE);
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			if(num == 0) {
				if(pt == 0) {
					//System.out.println(0);
					bw.write("0");
					bw.newLine();
				}else {
					//System.out.println(heap[1]);
					bw.write(String.valueOf(heap[1]));
					bw.newLine();
					delete();
				}
			}else {
				pt++;
				insert(num);
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	public static void insert(int num) {
		heap[pt] = num;
		int parent = pt / 2;
		int now = pt;
		
		while(parent > 0) {
			if(heap[parent] > heap[now]) {
				int swap = heap[now];
				heap[now] = heap[parent];
				heap[parent] = swap;
			}else {
				break;
			}
			
			parent /= 2;
			now /= 2;
		}
	}
	
	public static void delete() {
		heap[1] = heap[pt];
		heap[pt] = Integer.MAX_VALUE;
		pt--;
		
		int root = 1;
		int left = 2;
		int right = 3;
		
		while(heap[root] > heap[left] || heap[root] > heap[right]) {
			if(heap[left] >= heap[right]) {
				int swap = heap[right];
				heap[right] = heap[root];
				heap[root] = swap;
				root = right;
			}else {
				int swap = heap[left];
				heap[left] = heap[root];
				heap[root] = swap;
				root = left;
			}
			
			left = root * 2;
			right = root * 2 + 1;
		}
	}

}
