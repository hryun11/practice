package thread.creation.example;

public class Main2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Code that will run in a new thread
                throw new RuntimeException("Intentional Exception");
            }
        });

        thread.setName("Misbehaving thread");

        // 스레드 내에서 발생한 예외가 캐치되지 않으면 핸들러 호출
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                // 예외 출력
                System.out.println("A critical error happend in thread " + t.getName() + " the error is " + e.getMessage());
            }
        });
        thread.start();
    }

}
