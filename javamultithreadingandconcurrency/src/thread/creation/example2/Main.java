package thread.creation.example2;

/*
 * Runnable의 또 다른 객체를 만드는 대신 스레드를 확장하는 새 클래스를  만든다.
 * 기존 방법으로는 할 수 없었던 현재 스레드와 직접적으로 관련된 많은 데이터와 메서드에 액세스 할 수 있다.
 * */
public class Main {
    public static void main(String[] args) {
        Thread thread = new NewThread();

        thread.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from " + this.getName());
        }
    }
}
