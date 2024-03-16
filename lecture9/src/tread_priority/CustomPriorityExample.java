package tread_priority;

public class CustomPriorityExample {
    public static void main(String s[]) {
        // Подготовка потоков
        Thread t[] = new Thread[3];
        for (int i=0; i<t.length; i++) {
            t[i]=new Thread(new ThreadTest(),
                    "Thread "+i);
            t[i].setPriority(Thread.MIN_PRIORITY +
                    (Thread.MAX_PRIORITY -
                            Thread.MIN_PRIORITY)/t.length*i);// 1,4,5
        }

        // Запуск потоков
        for (int i=0; i<t.length; i++) {
            t[i].start();
            System.out.println(t[i].getName()+
                    " started");
        }
    }
}
