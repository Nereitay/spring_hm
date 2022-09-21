package es.kiwi.test;

public class ThreadLocalDemo {

    private static Integer num = 0;

    private static ThreadLocal<Integer> numLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
//                num += 5;
                int x = numLocal.get();
                x += 5;
                numLocal.set(x);
//                System.out.println(Thread.currentThread().getName() + ":" + num);
                System.out.println(Thread.currentThread().getName() + ":" + numLocal.get());
            }, "Threas-" + i);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();;
        }
    }
}
