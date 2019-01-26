import java.util.Arrays;

public class ArrayQueue {
    int size;
    int startIndex; // to avoid copying of array while dequeue (POP)
    Object[] queue;

    ArrayQueue() {
        this(3);
    }

    ArrayQueue(int initialCapacity) {
        queue = new Object[initialCapacity];
    }

    void enqueue(Object value) {
        if (size + startIndex >= queue.length) {
            Object[] newQueue = new Object[(int) ((size + startIndex) * 1.5) + 1];
            for (int i = 0; i < size; i++) {
                newQueue[i] = queue[i];
            }
            queue = newQueue;
        }
        queue[size] = value;
        size++;
    }

    // get and remove
    Object dequeue() {
        Object result = queue[startIndex];
        queue[startIndex] = null;
        startIndex++; // to avoid copying of array
        size--;
        return result;
    }

    // get
    Object peek() {
        return queue[startIndex];
    }

    int size() {
        return size;
    }

    void removeAll(Object value) {
        for (int i = startIndex; i < startIndex + size; i++) {
            if (queue[i].equals(value)) {
                for (int j = i; j < startIndex + size - 1; j++) {
                    queue[j] = queue[j + 1];
                }
                queue[startIndex + size - 1] = null; // the last element
                size--;
            }
        }

    }
}

class QueueTest {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("A");
        queue.enqueue("C");

        System.out.println(queue.size()); // 4
        System.out.println(Arrays.toString(queue.queue)); // A, B, A, C, null
        queue.removeAll("A");
        System.out.println(queue.size()); // 2
        System.out.println(Arrays.toString(queue.queue)); // B, C, null, null, null
        System.out.println(queue.peek()); // B
        System.out.println(queue.size()); // 2
        System.out.println(Arrays.toString(queue.queue)); // B, C, null, null, null
        System.out.println(queue.dequeue()); // B
        System.out.println(queue.size()); // 1
        System.out.println(Arrays.toString(queue.queue)); // null, C, null, null, null
        System.out.print(queue.startIndex); // 1
    }
}