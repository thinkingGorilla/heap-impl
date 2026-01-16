import java.util.ArrayList;
import java.util.List;

public class MinHeap {

    private final List<Integer> heap = new ArrayList<>();

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return (2 * index) + 1;
    }

    private int rightChild(int index) {
        return (2 * index) + 2;
    }

    private void swap(int from, int to) {
        int temp = heap.get(from);
        heap.set(from, heap.get(to));
        heap.set(to, temp);
    }

    public void insert(int value) {
        heap.add(value);
        // 마지막 인덱스 가져오기
        int currentIndex = heap.size() - 1;
        // 마지막 인덱스부터 루트 인덱스까지 버블 업하며 부모 노드에 최소값이 올라가도록 한다.
        while (currentIndex > 0) {
            // 순회할때마다 루트 노드의 인덱스를 재계산 한다.
            int parentIndex = parent(currentIndex);
            if (heap.get(currentIndex) < heap.get(parentIndex)) {
                // 부모 노드의 값이 자식보다 더 크다면 교환한다.
                System.out.println(heap);
                swap(currentIndex, parentIndex);
                System.out.println(heap);
                // 교환한 뒤에는 부모 노드 인덱스를 자식 노드 인덱스가 되도록 한다.
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    public int extractMin() {
        if (heap.isEmpty()) throw new RuntimeException("Heap is empty");

        int min = heap.get(0);
        int lastElement = heap.remove(heap.size() - 1);

        // 요소가 하나만 남았다면 힙이 비어있으므로 최소값을 반환하고 종료한다.
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);

            // 루트 노드의 값이 최소값이 되는 부모 노드가 될때까지 버블 다운을 수행한다.
            int currentIndex = 0;
            while (true) {
                int left = leftChild(currentIndex);
                int right = rightChild(currentIndex);
                int smallest = currentIndex;

                // 완전 이진 트리는 맨 왼쪽부터 채워지므로,
                // 자식노드가 있는지 없는지 확인하기 위해 경계 검사를 수행한다.
                if (left < heap.size() && heap.get(left) < heap.get(smallest)) {
                    smallest = left;
                }

                // smallest가 왼쪽이 되었다면 오른쪽과 비교한다.
                // smallest가 왼쪽이 아니라면 부모 노드와 비교한다.
                if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
                    smallest = right;
                }

                // smallest, 즉 현재 부모 노드의 인덱스가 자식 노드의 인덱스로 변하지 않았다면
                // 자식 노드 중 부모 노드의 값보다 작은 값을 갖는 노드가 없는 것이므로 종료한다.
                if (smallest == currentIndex) {
                    break;
                }

                System.out.println(heap);
                swap(currentIndex, smallest);
                System.out.println(heap);

                currentIndex = smallest;
            }
        }

        return min;
    }

    public int peek() {
        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public static class HeapExample {

        public static void main(String[] args) {
            MinHeap minHeap = new MinHeap();
            minHeap.insert(25);
            minHeap.insert(10);
            minHeap.insert(15);
            minHeap.insert(20);
            minHeap.insert(5);
            System.out.println(minHeap.peek());

            while (!minHeap.isEmpty()) {
                System.out.println(minHeap.extractMin());
            }
        }
    }
}
