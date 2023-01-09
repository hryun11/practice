package list.arraylist.implementation;

import java.util.ListIterator;

public class ArrayList {

    private int size = 0;
    private Object[] elementData = new Object[100];
    // Object 데이터 타입의 배열을 생성하고 100개의 데이터만 수용할 수 있음
    // 실제로 ArrayList의 collection에서는 데이터가 추가되는 것만큼 배열의 크기도 늘어남.


    public boolean addFirst(Object element) {
        return add(0, element);
    }

    public boolean addLast(Object element) {
        elementData[size] = element;    // [size]는 index 번호
        size++; // 배열의 사이즈 하나씩 증가
        return true;
    }

    public boolean add(int index, Object element) {
        for (int i = size - 1; i >= index; i--) {
            elementData[i + 1] = elementData[i];
        }
        elementData[index] = element;
        size++;

        return true;
    }

    //sout에서 arrayList 객체를 정리해서 보여준다.
    public String toString() {
        String str = "[";
        for (int i = 0; i < size; i++) {
            str += elementData[i];
            if (i < size - 1) {
                str += ",";
            }
        }
        return str + "]";
    }

    public Object remove(int index) {
        Object removed = elementData[index];
        for (int i = index + 1; i <= size - 1; i++) {
            elementData[i-1] = elementData[i];
        }
        size--;
        elementData[size] = null;   // 가장 뒤에 남아있는 데이터 삭제
        return removed; // 삭제한 값 리턴
    }

    public Object removeFirst() {
        return remove(0);
    }

    public Object removeLast() {
        return remove(size - 1);
    }

    public Object get(int index) {
        return elementData[index];  // **ArrayList는 index를 통해 데이터를 가져오기 때문에 어느위치에 있든간에 빠르게 가져올 수 있음.
    }

    public int size() {
        return size;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    public ListIterator listIterator() {
        return new ListIterator();
    }

    class ListIterator {
        private int nextIndex = 0;

        public boolean hasNext() {
            return nextIndex < size();
        }

        public Object next() {
//            Object returnData = elementData[nextIndex];
//            nextIndex++;
//            return returnData;

            return elementData[nextIndex++];
        }

        public Object previous() {
            return elementData[--nextIndex];
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public void add(Object element) {
            ArrayList.this.add(nextIndex++, element);
        }

        public void remove() {
            ArrayList.this.remove(nextIndex - 1);
            nextIndex--;
        }
    }
}
