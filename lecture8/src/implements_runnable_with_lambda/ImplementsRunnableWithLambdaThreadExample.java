package implements_runnable_with_lambda;

class ImplementsRunnableWithLambdaThreadExample {
    public static void main(String[] args) {

        // Runnable – функциональный интерфейс, можно использовать лямбда выражение

        Thread thread = new Thread(() -> {
            System.out.println("run method body");
        });

        thread.start();
    }
}