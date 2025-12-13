import java.util.Arrays;
import java.util.Scanner;

public class FibonacciArray {
    // Returns an array of the first n Fibonacci numbers (starting 0, 1, ...)
    public static long[] fibArray(int n) {
        if (n <= 0) {
            return new long[0];
        }
        long[] arr = new long[n];
        arr[0] = 0;
        if (n > 1) {
            arr[1] = 1;
        }
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }

    // Prints array elements space-separated
    public static void printArray(long[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("(empty)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(arr[i]);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        int n;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        } else {
            // Fallback to interactive input; default to 10 if no input available
            n = 10;
            try {
                Scanner sc = new Scanner(System.in);
                if (System.console() != null) {
                    System.out.print("Enter n (number of terms): ");
                    if (sc.hasNextInt()) {
                        n = sc.nextInt();
                    }
                }
                sc.close();
            } catch (Exception ignored) { }
        }
        long[] result = fibArray(n);
        printArray(result);
    }
}
