package list.arraylist.implementation;

public class ArrayList {

    private int size = 0;
    private Object[] elementData = new Object[100];
    // Object 데이터 타입의 배열을 생성하고 100개의 데이터만 수용할 수 있음
    // 실제로 ArrayList의 collection에서는 데이터가 추가되는 것만큼 배열의 크기도 늘어남.

    public boolean addLast(Object element) {
        elementData[size] = element;    // [size]는 index 번호
        size++; // 배열의 사이즈 하나씩 증가
        return true;
    }

    public boolean add(int index, Object element) {
        for (int i = size - 1; i >= index; i--) {
            elementData[i + 1] = elementData[i];

        }
        return true;

    }
}
