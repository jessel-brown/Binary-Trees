import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Arrays;

// Test binary search trees (bst).
public class bst {

	private static int N = 20;
	
    // Test program
	public static void test(int [] keys, int [] ops, long [] times ) {
		int i;
		
		long start, finish;
		
		if (N <= 600000) {
			start = System.currentTimeMillis();
			treeNode bst = treeNode.nullnode;
			for(i = 0; i < N; i++) { 
				switch (ops[i]) {
				case 0:   // insert
				case 1: bst = bst.insert( keys[i] ); break;
				case 2: bst = bst.remove( keys[i] ); break;
				case 3: bst.find(keys[i]); break;
				}
			}		
			if (N < 100) {
				System.out.println( "Binary search tree:");
				bst.printTree( ); System.out.println();
			}
			bst = treeNode.nullnode;	
			finish = System.currentTimeMillis();
			times[0] = finish-start;
			System.out.println("Binary search tree: " + (finish-start) + " milliseconds.");
		} else
			System.out.println("Binary search tree skipped due to large size.");
		
		start = System.currentTimeMillis();
		avltree avl = new avltree();
		for(i = 0; i < N; i++) { 
			switch (ops[i]) {
			case 0:   // insert
			case 1: avl.insert( keys[i] ); break;
			case 2: avl.remove( keys[i] ); break;
			case 3: avl.find( keys[i] ); break;
			}
		}		
		if (N < 100) { 
			System.out.println( "\nAVL search tree:");
		    avl.getRoot().printTree( ); System.out.println();
		}
		avl.clear();
		finish = System.currentTimeMillis();
		times[1] = finish-start;
        System.out.println("AVL search tree: " + (finish-start) + " milliseconds.");
		
		start = System.currentTimeMillis();
		redblacktree rbt = new redblacktree();
		for(i = 0; i < N; i++) { 
			switch (ops[i]) {
			case 0:   // insert
			case 1: rbt.insert( keys[i] ); break;
			case 2: rbt.remove( keys[i] ); break;   // to be implemented
			case 3: rbt.find( keys[i] ); break;
			}
			//rbt.getRoot().printTree( ); System.out.println("RB");
		}
		if (N < 100) {
			System.out.println( "\nRed-Black search tree:");
		    rbt.getRoot().printTree( ); System.out.println();
		}
		finish = System.currentTimeMillis();
		times[2] = finish-start;
		rbt.checkRedBlack(); 
		rbt.clear();
        System.out.println("Red-Black search tree: " + (finish-start) + " milliseconds.");
        
		start = System.currentTimeMillis();
		splaytree spt = new splaytree();
		for(i = 0; i < N; i++) { 
			for(i = 0; i < N; i++) { 
				switch (ops[i]) {
				case 0:   // insert
				case 1: spt.insert( keys[i] ); break;
				case 2: spt.remove( keys[i] ); break;
				case 3: spt.find( keys[i] ); break;
				}
			}
		}
		if (N < 100) {
			System.out.println( "\nSplay search tree:");
		    spt.getRoot().printTree( ); System.out.println();
		}
		spt.clear();
		finish = System.currentTimeMillis();
		times[3] = finish-start;
        System.out.println("Splay search tree: " + (finish-start) + " milliseconds.");
        
        start = System.currentTimeMillis();
        Arrays.sort(keys);
		finish = System.currentTimeMillis();
        System.out.println("Sorting: " + (finish-start) + " milliseconds.");
       
	}
	
	public static void question0() {
		int NUMS; 
		final int GAP  = 37;
		int i, j;
		long[] times = new long[4];

		int[] keys = new int[N];
        int[] ops = new int[N];
        NUMS = ((N & 1) == 1)? N+1 : N;
        Random randomGenerator = new Random();
        
  		System.out.println( "Checking... " );
		j = 0;
		for(i = 0; i < N; i++) {
			j = ( j + GAP ) % NUMS;
			keys[i] = j;
			ops[i] = randomGenerator.nextInt(4);
			if (N < 100) { 
				switch (ops[i]) {
				case 0:   // insert
				case 1: System.out.print(" i" + keys[i] ); break;
				case 2: System.out.print(" r" + keys[i] ); break;
				case 3: System.out.print(" f" + keys[i] ); break;	
				}
			}
		}
		if (N<100) System.out.println("\nNote: i=insert; r=remove; f=find.\n");
		
		test( keys, ops, times );
		
	}

	public static void sort(int[] keys, int[] res, long[] times){

		int i;

		long start, finish;

		//AVL TREE
		start = System.currentTimeMillis();
		avltree avl = new avltree();
		for(i = 0; i < keys.length; i++) {
			avl.insert(keys[i]);
		}
		int next = avl.getRoot().inorder(0, res);

		avl.clear();
		finish = System.currentTimeMillis();
		times[1] = finish-start;
		System.out.println("AVL search tree: " + (finish-start) + " milliseconds.");

		//RED-BLACK TREE
		start = System.currentTimeMillis();
		redblacktree rbt = new redblacktree();
		for(i = 0; i < keys.length; i++) {
			rbt.insert(keys[i]);
		}

		int next2 = rbt.getRoot().inorder(0, res);

		rbt.clear();
		finish = System.currentTimeMillis();
		times[2] = finish-start;
		System.out.println("Red-Black search tree: " + (finish-start) + " milliseconds.");

		//SPLAY TREE
		start = System.currentTimeMillis();
		splaytree spt = new splaytree();
		for(i = 0; i < keys.length; i++) {
			spt.insert(keys[i]);
		}
		int next3 = spt.getRoot().inorder(0, res);

		spt.clear();
		finish = System.currentTimeMillis();
		times[3] = finish-start;
		System.out.println("Splay search tree: " + (finish-start) + " milliseconds.");

	}

	public static void question2() {
		int i, j;
		long[] times = new long[4];

		int[] res = new int[5000];

		Random randomGenerator = new Random();
		j = 0;

		for (i = 0; i < 101; i++){
			int[] a = new int[5000];
			for(j = 0; j < a.length; j++){
				a[j] = randomGenerator.nextInt(10);
			}
			System.out.println( "Checking... " + i );
			sort(a, res, times);
		}
	}
	
	public static void main( String [ ] args ) {

		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            System.out.print("Please give number of elements to be inserted : ");
            N = Integer.parseInt(read.readLine());
        } catch(Exception ex){
            ex.printStackTrace();
        }
        
		question0();

        System.out.println("AVL tree is the most efficient in running time averaging O(logN), with Red-Black tree in second with O(logN) average.");

        question2();

        System.out.println(" Red-Black tree is the fastest running algorithm for inserting n keys and \n placing them into an array inorder with O(logN) time. AVL tree is the second fastest.");
	}

}