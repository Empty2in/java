package atomicity_visibility;

public class Incrementor {
    private volatile int result = 0; // другие потоки будут видеть всегда последнее значение

    public void showResult() {
        System.out.println(result);
    }

    public void increment() {
        synchronized (this) { // инкремент будет выполнен атомарно
            result++;
        }
    }
}
