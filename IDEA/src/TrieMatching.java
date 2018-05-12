import java.io.*;
import java.util.*;
import Ex1.Trie;
import Ex1.Node;


class N
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];

	N ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
	}
}

public class TrieMatching implements Runnable {
	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return N.NA;
		}
	}



	int getMax(List<String> patterns) {
        int max = 0;
        for (int i = 0; i < patterns.size(); i++)
            if (max < patterns.get(i).length())
                max = patterns.get(i).length();
        return max;
    }

	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();
        int window = getMax(patterns);
        Trie t = new Trie();
        String[] pat = patterns.toArray(new String[patterns.size()]);
        Node node = t.buildTrie(pat);
        int index = 0;
        while(index<text.length()){
            Node temp = node;
            for(int i = index;i<window+index;i++){
                if(!(i<text.length())) {
                    index = i;
                    break;
                }
                if(temp.stop){
                    System.out.println(index);
                    index = i+1;
                    break;
                }
                int p = letterToIndex(text.charAt(i));
                if(p==-1){
                    index = index+1;
                    break;
                }
                if(temp.children[p]==null){
                    index = index+1;
                    break;
                }
                temp = temp.children[p];
                if(temp.stop){
                    System.out.println(index);
                    index = index+1;
                    break;
                }
            }
        }
		// write your code here

		return result;
	}

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List <Integer> ans = solve (text, n, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatching ()).start ();
	}
}
