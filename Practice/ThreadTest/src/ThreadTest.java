public class ThreadTest {
    public static void main(String[] args) {        
        int start = 1;
        int end = 1000;
        int numberOfThreads = 3;

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new PrimeFinderThread(start, end);
            thread.start();

            start = end + 1;
            end += 1000;
        }
    }
    static class PrimeFinderThread extends Thread {
        private final int start;
        private final int end;

        public PrimeFinderThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            for (int i = start; i <= end; i++) {
                if (isPrime(i)) {
                    System.out.println(Thread.currentThread().getName() + " found prime number: " + i);
                }
            }
        }

        private boolean isPrime(int number) {
            if (number <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
