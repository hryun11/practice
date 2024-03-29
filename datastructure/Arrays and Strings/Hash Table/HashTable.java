import java.util.LinkedList;

class HashTable {
    static class Node {
        String key;
        String value;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        //get
        String value() {
            return value;
        }

        //set
        void value(String value) {
            this.value = value;
        }
    }
    LinkedList<Node>[] data;

    HashTable(int size) {
        this.data = new LinkedList[size];
    }

    //hash 알고리즘 함수
    int getHashCode(String key) {
        int hashcode = 0;
        for (char c : key.toCharArray()) {
            hashcode += c; // 각 문자의 ascii값 더하기
        }
        return hashcode;
    }

    int convertToIndex(int hashcode) {
        return hashcode % data.length; //hashcode를 배열 방의 길이로 나눈 나머지 값
    }

    //key로 해당 Node를 찾아오는 함수
    Node searchKey(LinkedList<Node> list, String key) {
        if (list == null) {
            return null;
        }
        for (Node node : list) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    //데이터를 받아서 저장
    void put(String key, String value) {
        int hashcode = getHashCode(key);
        int index = convertToIndex(hashcode);
        System.out.println(key +", hashcode(" + hashcode + "), index("+ index + ")");
        LinkedList<Node> list = data[index];
        if (list == null) {
            list = new LinkedList<Node>();
            data[index] = list;
        }
        Node node = searchKey(list, key);
        if (node == null) {
            list.addLast(new Node(key, value));
        } else {
            node.value(value);
        }
    }

    //key로 데이터를 가져오는 함수
    String get(String key) {
        int hashcode = getHashCode(key);
        int index = convertToIndex(hashcode);
        LinkedList<Node> list = data[index];
        Node node = searchKey(list, key);
        return node == null ? "Not Found" : node.value();
    }
}

class Test {
    public static void main(String[] args) {
        HashTable h = new HashTable(3);
        h.put("sung", "She is pretty");
        h.put("jin", "She is a model");
        h.put("hee", "She is an angel");
        h.put("min", "She is cute");
        h.put("sung", "She is beautiful");

        System.out.println(h.get("sung"));
        System.out.println(h.get("jin"));
        System.out.println(h.get("hee"));
        System.out.println(h.get("min"));
        System.out.println(h.get("jae"));
    }
}
