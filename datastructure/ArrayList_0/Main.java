import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        System.out.println("add(값)");
        System.out.println(numbers);

        // insert
        numbers.add(1, 50);
        // index 1의 자리에 element 50을 넣음
        // 기존의 index 1부터 뒤의 element들은 index 1씩 밀림.(덮어쓰기 X)
        System.out.println("\nadd(값)");
        System.out.println(numbers);

        // remove
        numbers.remove(2);
        // index 2 삭제 시 뒤의 element들이 index 1씩 앞으로 이동.
        System.out.println("\nadd(값)");
        System.out.println(numbers);

        // get 가져오기
        numbers.get(0);

        // size 데이터 개수 조회
        numbers.size();

        // 반복
        // Iterater : 자바 인터페이스
        Iterator<Integer> it = numbers.iterator();

        /*
        it에서 hasNext 메소드가 true이면 계속 반복
        (hasNext 메소드는 boolean 값을 return)
        */
        while (it.hasNext()) {
            int value = (int)it.next();  // next 메소드는 다음 element를 return
            if (value == 30) {
                it.remove();
            }
            System.out.println(value);
        }
        System.out.println(numbers);

        System.out.println("\nfor each");
        // numbers를 순회할 때마다 그 element가 value에 담기게 된다.
        for (int value : numbers) {
            System.out.println(value);
        }

        System.out.println("\nfor");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }
    }
}
