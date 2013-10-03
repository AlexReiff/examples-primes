public class PrintPrimes {
  int numberOfPrimes;
  int ROWS;
  int COLUMNS;
  int ORDMAX;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int ROWS, int COLUMNS, int ORDMAX) {
    this.numberOfPrimes   = numberOfPrimes;
    this.ROWS  = ROWS;
    this.COLUMNS  = COLUMNS;
    this.ORDMAX = ORDMAX;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }

  public static void main(String[] args) {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 30);
      printPrimes.calculatePrimes();
      printPrimes.printPrimes();
  }

  public void calculatePrimes() {
      /* Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[1] = 2;
      calculateOddPrimes();
  }

  private void calculateOddPrimes() {
      boolean loopAgain;
      int N;
      int MULT[] = new int[ORDMAX + 1];

      int currNum = 1;
      int indexOfRootOfSquare = 2;
      int nextSquareNum = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          currNum += 2;
          /* checks if the current number is a square of a previous prime number
             adds square to list of invalid numbers, prepares to check next square
           */
          if (currNum == nextSquareNum) {
        	MULT[indexOfRootOfSquare] = currNum;
            indexOfRootOfSquare++;
            nextSquareNum = listOfPrimes[indexOfRootOfSquare] * listOfPrimes[indexOfRootOfSquare];
          } 
          N = 2;
          loopAgain = false;
          while (N < indexOfRootOfSquare && !loopAgain) {
            while (MULT[N] < currNum) {
              MULT[N] = MULT[N] + listOfPrimes[N] + listOfPrimes[N];
            }
            if (MULT[N] == currNum) {
              loopAgain = true;
            }
            N++;
          }
        } while (loopAgain);
        listOfPrimes[primesFoundSoFar] = currNum;
      }
    }

    //prints out the prime number in a table with dimensions depending on input
    //creates multiple tables if a table is smaller than the number of primes
    public void printPrimes() {
        int pageNumber = 1;
        int firstNonprintedPrime = 1;
        while (firstNonprintedPrime <= numberOfPrimes) {
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber + "\n");
          //starts where the last page left off
          for (int currRow = firstNonprintedPrime; currRow < firstNonprintedPrime + ROWS; currRow++){
            for (int currCol = 0; currCol < COLUMNS; currCol++) {
              int nextNum = currRow + currCol * ROWS;
              if (nextNum <= numberOfPrimes) {
                System.out.format("%10d", listOfPrimes[nextNum]);
              }
            }
            System.out.println("");
          }
          System.out.println("\f");
          //the next page starts at the first prime that couldn't fit in the last page 
          pageNumber++;
          firstNonprintedPrime += ROWS * COLUMNS;
        }
    }
}
