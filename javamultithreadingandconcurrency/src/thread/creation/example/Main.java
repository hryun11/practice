package thread.creation.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 새 스레드 객체를 생성해야 함.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 새 스레드에서 코드가 실행된다.
                System.out.println("We are now in thread "+Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
            }
        });

        thread.setName("New Worker Thread"); // 스레드명 지정

        thread.setPriority(Thread.MAX_PRIORITY); // 스레드 우선순위 설정. 복잡한 프로그램의 스레드가 보다 많은 응답성을 필요로 하면 중요한 역할을 함.

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start(); // JVM이 새 스레드를 생성해 운영 체제에 전달한다.
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");

        Thread.sleep(10000); // sleep 메서드는 이 시간이 지날때까지 현재 스레드를 스케줄링 하지 말라는 요청. 이 시간 동안 cpu를 사용하지 않음.

    }
}