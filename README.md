# PriorityQueue

### 우선순위 큐 : 우선순위가 가장 높은 데이터가 먼저 삭제가 되는 자료구조

### 특징 : 완전 이진트리의 일종으로 도식화를 할 때도 이진트리로 도식화 합니다.

최소 힙 : 루트노드가 자식노드보다 작은 값이 오는 힙입니다.
예를 들면 이런 경우가 최소 힙에 해당합니다.

![우선순위큐1](https://user-images.githubusercontent.com/68115246/123584456-5adc4100-d81c-11eb-9466-30a017722ef0.png)

최상단에 있는 루트노드는 아래 자식들의 값보다 작은 값이 옵니다. 또한 더 아래 계층을 확인하더라도 부모노드의 값이 더 작다는걸 알 수 있고, 이는 최소 힙 조건에 만족하는 경우 입니다.

최대 힙 : 루트노드가 자식들 보다 큰 값이 오는 힙 입니다.
예를 들면 이런 경우가 최대 힙에 해당합니다.

![우선순위큐2](https://user-images.githubusercontent.com/68115246/123584464-5ca60480-d81c-11eb-9681-54f193ed2b2c.png)

최상단의 루트노드는 하위 자식들의 값보다 큽니다. 또한 아래 계층을 확인해봤을 때 자식 노드들의 값보다 부모노드의 값이 크다는걸 알 수 있고, 이는 최대 힙 조건에 만족합니다.

### 삽입

1. 특정 계층에 빈 노드가 있는 곳에 새로 들어온 데이터를 삽입합니다. 만약 그런 경우가 아니라면 다음 계층의 맨 왼쪽에 데이터를 삽입합니다.

![우선순위큐3](https://user-images.githubusercontent.com/68115246/123584467-5dd73180-d81c-11eb-9701-46de347a4d13.png)

2. 삽입된 노드와 부모노드를 비교합니다. 위의 그림은 최소 힙을 만족해야 하는데 새로들어온 7이 35보다 작기 때문에 위치를 바꿔줘야 합니다.

![우선순위큐4](https://user-images.githubusercontent.com/68115246/123584469-5fa0f500-d81c-11eb-95a3-2a0e3c025623.png)

3. 또한 7과 15를 비교했을 때도 7이 작기 때문에 한번 더 위치를 바꿔줍니다.

![우선순위큐5](https://user-images.githubusercontent.com/68115246/123584478-63347c00-d81c-11eb-9482-389610a9b625.png)

이러한 방식으로 삽입이 진행이 됩니다. 이 때 데이터는 8개가 있고, 노드를 바꾼 횟수는 2 입니다. 만약 5보다 작은 수가 삽입이 됐다면 3번 교환하는 작업이 이뤄졌을 겁니다. 삽입의 시간복잡도는 O(log n)이 됩니다.

### 삭제

1. 먼저 최상단 노드가 우선순위 큐에서 빠져 나옵니다.

![우선순위큐6](https://user-images.githubusercontent.com/68115246/123584481-662f6c80-d81c-11eb-80a6-4f0f9bdfa4e2.png)

2. 제일 하위레벨의 제일 오른쪽에 있는 데이터가 최상단 루트노드에 삽입됩니다.

![우선순위큐7](https://user-images.githubusercontent.com/68115246/123584484-67f93000-d81c-11eb-9db7-934e0b96b8f0.png)

3. 35와 자식노드 두 개를 비교합니다. 자식노드들 중 제일 작은 값과 위치를 바꿉니다. 위의 예에서는 7과 35를 바꿔줍니다.

![우선순위큐8](https://user-images.githubusercontent.com/68115246/123584489-69c2f380-d81c-11eb-9fef-4ef8104a202b.png)

4. 아직 최소 힙 요건에 맞지 않습니다. 35와 두 자식노드 중 제일 작은 값인 15와 바꿉니다.

![우선순위큐9](https://user-images.githubusercontent.com/68115246/123584500-6cbde400-d81c-11eb-8d78-d9c04bc4b415.png)

- 최대힙을 구현해본 소스코드

```java
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
```
