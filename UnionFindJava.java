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
        this.N = N;
        this.id = new int[N];
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
        if (p < 0 || p >= N || q < 0 || q >= N) {
            return;
        }

        // **** check if p & q are already connected ****
        if (this.id[p] == this.id[q]) {
            return;
        }

        // **** make a new connection between p & q ****
        int i = 0;
        for (int t = this.id[p]; i < this.N; i++) {
            if (this.id[i] == t)
                this.id[i] = this.id[q];
        }
    }


    /**
     * Determine if p and q are or are not connected.
     */
    public boolean areConnected(int p, int q) {

       // **** sanity check(s) ****
       if (p < 0 || p >= N || q < 0 || q >= N) {
            return false;
        }

        // **** check if p & q are connected ****
        if (this.id[p] == this.id[q])
            return true;

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

        // **** initialize object ****
        UnionFindJava obj = new UnionFindJava(N);
   
        // **** loop reading p and q ****
        boolean nextLine = true;
        while (nextLine) {

            // **** read next input line ****
            int[] pq = Arrays.stream(br.readLine().trim().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

            // **** extract p and q from the int[] ****
            int p = pq[0];
            int q = pq[1];

            // **** determine if p & q are connected ****
            boolean connected = obj.areConnected(p, q);

            // ???? ????
            System.out.println("main <<< " + p + " -> " + q +
                                (connected ? " connected" : " not connected"));

            // **** connect p -> q (if needed) ****
            if (!connected) {
                obj.connect(p, q);
            }

            // **** check if br has a next line to read ****
            nextLine = br.ready();
        }

        // **** close buffered reader ****
        br.close();
    }
}