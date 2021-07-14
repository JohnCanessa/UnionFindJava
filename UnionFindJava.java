import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 
 */
public class UnionFindJava {


    // **** class members ****
    public int N;
    public int[] id;


    /**
     * Constructor.
     */
    public UnionFindJava(int N) {
        this.N = N;                 // number of nodes
        this.id = new int[N];

        // **** each array entry holds a reference to itself ****
        for (int i = 0; i < N; i++)
            this.id[i] = i;
    }


    /**
     * Find if there is a connection between nodes p & q.
     * This call will make a new connection between p & q if needed.
     * Worse case O(n);
     */
    public void connect(int p, int q) {

        // **** sanity check(s) ****
        if (p < 0 || p >= N || q < 0 || q >= N) return;

        // **** check if p & q are already connected ****
        if (this.id[p] == this.id[q]) return;

        // **** make a new connection between p & q - O(n) ****
        int t = this.id[p];
        for (int i = 0; i < this.N; i++) {
            if (this.id[i] == t)
                this.id[i] = this.id[q];
        }
    }


    /**
     * Determine if p and q are connected.
     */
    public boolean areConnected(int p, int q) {

       // **** sanity check(s) ****
       if (p < 0 || p >= N || q < 0 || q >= N) return false;

        // **** check if p & q ARE connected ****
        if (this.id[p] == this.id[q]) return true;

        // **** p & q are NOT connected ****
        return false;
    }


    /**
     * Test scaffold.
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // *** read N ****
        int N = Integer.parseInt(br.readLine().trim());

        // ???? ????
        System.out.println("main <<< N: " + N);


        // **** 1. initialize union find object ****
        UnionFindJava obj = new UnionFindJava(N);

        // ???? ????
        System.out.println("main <<< id: " + Arrays.toString(obj.id));
   

        // **** loop reading pairs (p, q) ****
        boolean nextLine = true;
        while (nextLine) {

            // **** read next input line ****
            int[] pq = Arrays.stream(br.readLine().trim().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

            // **** extract pair (p, q) from the int[] ****
            int p = pq[0];
            int q = pq[1];


            // **** 2. determine if p & q are connected ****
            boolean connected = obj.areConnected(p, q);

            // ???? ????
            System.out.println("main <<< " + p + " -> " + q +
                                (connected ? " connected" : " not connected"));


            // **** 3. connect p -> q (if needed) ****
            if (!connected) {

                // **** connect p & q ****
                obj.connect(p, q);

                // ???? ????
                System.out.println("main <<< id: " + Arrays.toString(obj.id));
            }


            // **** check if br has a next line to read ****
            nextLine = br.ready();
        }

        // **** close buffered reader ****
        br.close();
    }
}