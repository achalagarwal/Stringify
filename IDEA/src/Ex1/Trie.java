package Ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

 class Pair{
    int source;
    int sink;
    Pair(int a, int b){
        this.source = a;
        this.sink = b;
    }
}

public class Trie {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
    int maxLength(String[] patterns){
        int max=0;
        for(int i =0;i<patterns.length;i++){
            if(max<patterns[i].length())
                max = patterns[i].length();
        }
        return max;
    }
    int getMap(char ch) {
        if(ch=='A')
            return 0;
        if(ch == 'C')
            return 1;
        if(ch == 'G')
            return 2;
        if(ch == 'T')
            return 3;
        else
            return -1;
    }
    public Node buildTrie(String[] patterns) {
        Node root = new Node();
        root.id = 0;
        int id = 1;
        for(int i =0 ; i<patterns.length ; i++) {
            Node t = root;
            for (int j = 0 ; j < patterns[i].length() ; j++) {
                char ch = patterns[i].charAt(j);
                int p = getMap(ch);
                if(t.children[p] == null) {
                    t.children[p] = new Node();
                    t.children[p].id  = id++;
                    t.children[p].prev = t.id;
                    t.children[p].c = ch;
                }
                t = t.children[p];
            }
            t.stop = true;
        }
        return root;
    }

    static public void main(String[] args) throws IOException {
        new Trie().run();
    }

    public void print(Node trie) {
        int source = trie.id;
        if(trie.children[0]!=null)
            print(trie.children[0]);
        if(trie.children[1]!=null)
            print(trie.children[1]);
        if(trie.children[2]!=null)
            print(trie.children[2]);
        if(trie.children[3]!=null)
            print(trie.children[3]);
        if(source!=0)
        System.out.println(trie.prev+"->"+trie.id+":"+trie.c);

        Node c = trie;
        /*for (int i = 0; i < trie.size(); ++i) {
                System.out.println(trie.get(i).source + "->" + trie.get(i).sink + ":" + entry.getKey());
            }
        }
        */
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        Node trie = buildTrie(patterns);
        print(trie);
    }
}
