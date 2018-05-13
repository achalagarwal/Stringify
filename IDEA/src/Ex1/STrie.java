package Ex1;
import java.util.*;
import java.io.*;



class SNode {
    public int id;
    public int prev;
    public char c;
    public SNode[] children = new SNode[5];
    public boolean stop = false;
    boolean visited;
    String suffix;
    SNode() {
       // super();
        visited = false;
    }
    boolean isNonBranchingParentOfLeaf() {
        int count = 0;
        boolean flag = true;
        for(SNode n:this.children) {
            if(n!=null) {
                count++;
                if (!n.isLeaf())
                    return false;
            }
        }
        if(count==1)
            return true;
        else
            return false;
    }
    boolean isLeaf() {
        for(SNode s:this.children){
            if(s!=null)
                return false;
        }
        return true;
    }
}
public class STrie {
    int getMap(char ch) {
        if(ch=='A')
            return 0;
        if(ch == 'C')
            return 1;
        if(ch == 'G')
            return 2;
        if(ch == 'T')
            return 3;
        if(ch == '$')
            return 4;
        else
            return -1;
    }
    public SNode buildTrie(String[] patterns) {
        SNode root = new SNode();
        root.id = 0;
        int id = 1;
        for(int i =0 ; i<patterns.length ; i++) {
            SNode t = root;
            for (int j = 0 ; j < patterns[i].length() ; j++) {
                char ch = patterns[i].charAt(j);
                int p = getMap(ch);
                if(t.children[p] == null) {
                    t.children[p] = new SNode();
                    t.children[p].id  = id++;
                    t.children[p].prev = t.id;
                    t.children[p].c = ch;
                }
                t = (SNode) t.children[p];
            }
            t.stop = true;
        }
        return root;
    }
    public void printArray(char[] arr, int top){
        for(int i = 0;i<=top;i++)
            System.out.print(arr[i]);
        System.out.println();
    }
    public void printer(SNode s, char[] arr, int i) {
        if(s.c == '$') {
              printArray(arr,i);
              return;
        }
        else {
            arr[++i] = s.c;
        }
        for(SNode n:s.children) {
            if (n != null)
                printer(n, arr, i);
        }
        i--;
        }

        public static void main(String[] args) throws IOException {
            new STrie().run();
        }
        public void dfs(SNode n, int level, int branch) {
            System.out.println("Entered "+level+", "+branch);
            branch = 0;
            if(n.isLeaf()) {
                n.suffix = Character.toString(n.c);
                return;
            }
            else {
                for(SNode s:n.children) {
                    if(s!=null) {
                        branch++;
                        dfs(s, level + 1, branch);
                    }
                }
            }
            if(n.isNonBranchingParentOfLeaf()) {
                for(int i = 0;i<n.children.length;i++) {
                    SNode s = n.children[i];
                    if(s!=null) {
                        n.suffix = n.c+s.suffix;
                        n.children[i] = null;
                        break;
                    }
                }
            }
        }
        public void print(List<String> x) {
            for (String a : x) {
                System.out.println(a);
            }
        }
        public String[] generateSuffixes(String text) {
            String[] s = new String[text.length()];
            for(int i =0;i<s.length;i++) {
                s[i] = text.substring(i);
            }
            return s;
        }

        public void compressTrie(SNode n) {
            dfs(n,0,0);
        }

        public void printTrie(SNode n){
           if(!n.visited) {
               if (n.suffix != null)
                   System.out.println(n.suffix);
               else
                   System.out.println(n.c);
           }
            n.visited = true;
            for(SNode s:n.children) {
                if(s!=null)
                    printTrie(s);
            }

        }

        public void run() throws IOException {
            FastScanner scanner = new FastScanner();
            String text = scanner.next();
            String[] suffixes = generateSuffixes(text);
           // Trie t = new Trie();
            SNode n = buildTrie(suffixes);

            //System.out.println("done");
            char[] arr = new char[100];
           // printer(n,arr,-1);
            compressTrie(n);
            char[] arr2 = new char[100];
            printTrie(n);
           // printer(n,arr2,-1);
            //List<String> edges = computeSuffixTreeEdges(n);
          //  print(edges);
        }
    }



