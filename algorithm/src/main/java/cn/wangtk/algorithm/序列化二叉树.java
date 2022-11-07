package cn.wangtk.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class 序列化二叉树 {
    public static void main(String[] args) {

        String data = "1,2,#,4,#,#,3,#,#,";
        String[] nodes = data.split(",");
        Node deserialize = new 序列化二叉树()
                .deserialize(Arrays.asList(nodes).stream().collect(Collectors.toCollection(LinkedList::new)));
        System.out.println(deserialize);

    }

    Node deserialize(LinkedList<String> attr) {
        if (attr.size() == 0) {
            return null;
        }
        String value = attr.removeFirst();
        if (value == null || "#".equals(value)) {
            return null;
        }
        Node root = new Node(value);
        root.left = deserialize(attr);
        root.right = deserialize(attr);

        return root;
    }

}

class Node {
    String value;
    Node left;
    Node right;

    public Node(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

}
