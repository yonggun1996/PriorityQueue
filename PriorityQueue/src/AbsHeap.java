import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AbsHeap {
	
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			if(Math.abs(o1) > Math.abs(o2)) {
				//������ �ִ� ������ �켱������ ������� �״�� ����
				return 1;
			}else if(Math.abs(o1) < Math.abs(o2)) {
				//���� ���� ������ �켱������ ������� ��ȯ�� �̷�����
				return -1;
			}else {
				//������ ���� ��� ū ���� �켱������ �ȴ�
				if(o1 > o2) {//������ �ִ� ������ �켱������ ������� �״�� ����
					return 1;
				}else if(o1 < o2) {//���� ���� ������ �켱������ ������� ��ȯ�� �̷�����
					return -1;
				}else {//���� �Ȱ����� �״�� ����
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
