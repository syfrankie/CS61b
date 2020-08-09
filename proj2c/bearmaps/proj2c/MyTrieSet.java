package bearmaps.proj2c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MyTrieSet implements TrieSet61B{

    private static final int R = 256;
    private Node root;

    private static class Node {
        private boolean isKey;
        private HashMap<Character, Node> links;
        private Node(boolean b) {
            isKey = b;
            links = new HashMap<>();
        }
    }

    public MyTrieSet() {
        clear();
    }

    @Override
    public void clear() {
        root = new Node(false);
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("Invalid Argument");
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char temp = key.charAt(i);
            if (curr.links.containsKey(temp)) {
                curr = curr.links.get(temp);
                continue;
            }
            return false;
        }
        return curr.isKey;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("Invalid Argument");
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char temp = key.charAt(i);
            if (!curr.links.containsKey(temp)) {
                curr.links.put(temp, new Node(false));
            }
            curr = curr.links.get(temp);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> out = new LinkedList<>();
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char temp = prefix.charAt(i);
            if (curr.links.containsKey(temp)) {
                curr = curr.links.get(temp);
                continue;
            }
            return out;
        }
        helper(curr, prefix, out);
        return out;
    }

    private void helper(Node node, String temp, List<String> out) {
        if (node == null) return;
        if (node.isKey) out.add(temp);
        for (char letter: node.links.keySet()) {
            helper(node.links.get(letter), temp + letter, out);
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException("Method Unavailable");
    }
}
