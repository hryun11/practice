class StringBuilder {
    private char[] value;
    private int size;
    private int index;

    StringBuilder() {
        size = 1;
        value = new char[size];
        index = 0;
    }

    public void append(String str) {
        if (str == null) str = "null";
        int len = str.length();
        ensureCapacity(len);
        for (int i = 0; i < str.length(); i++) {
            value[index] = str.charAt(i);
            index++;
        }
        System.out.println(size + ", "+index);
    }

    private void ensureCapacity(int len) {
        if (index + len > size) {
            size = (size + len) * 2;
            char[] newValue = new char[size];
            for (int i = 0; i < value.length; i++) {
                newValue[i] = value[i];
            }
            value = newValue;
            System.out.println("*** " + size + ", " + index);
        }
    }

    //append한 문자열을 모두 붙여서 반환
    public String toString() {
        return new String(value, 0, index);
    }
}

class Test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("sung");
        sb.append(" is");
        sb.append(" pretty");
        System.out.println(sb.toString());
    }
}
