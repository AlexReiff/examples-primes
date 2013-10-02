public class PrintPrimes {
  int numberOfPrimes;
  int RR;
  int CC;
  int WW;
  int ORDMAX;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int RR, int CC, int WW, int ORDMAX) {
    this.numberOfPrimes   = numberOfPrimes;
    this.RR  = RR;
    this.CC  = CC;
    this.WW  = WW;
    this.ORDMAX = ORDMAX;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }


  public static void main(String[] args) {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 10, 30);
      printPrimes.calculatePrimes();
      printPrimes.printPrimes();
  }

  public void calculatePrimes() {
      /* Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[0] = 2;
      calculateOddPrimes();
  }

  private void calculateOddPrimes() {
      boolean loopAgain = true;
      int N;
      int MULT[] = new int[ORDMAX + 1];

      int J = 1;
      int ORD = 2;
      int square = 9;

      for(int primesFoundSoFar = 1; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        while(loopAgain) {
          J = J + 2;
          if (J == square) {
        	MULT[ORD] = J;
            ORD++;
            square = listOfPrimes[ORD] * listOfPrimes[ORD];
          }
          N = 2;
          loopAgain = false;
          while (N < ORD && !loopAgain) {
            while (MULT[N] < J) {
              MULT[N] = MULT[N] + listOfPrimes[N] + listOfPrimes[N];
            }
            if (MULT[N] == J) {
              loopAgain = true;
            }
            N++;
          }
        }
        listOfPrimes[primesFoundSoFar] = J;
      }
    }

    public void printPrimes() {
        int PAGENUMBER = 1;
        int PAGEOFFSET = 1;
        while (PAGEOFFSET <= numberOfPrimes) {
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + PAGENUMBER);
          System.out.println("");
          for (int ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + RR; ROWOFFSET++){
            for (int C = 0; C < CC;C++) {
              if (ROWOFFSET + C * RR <= numberOfPrimes) {
                System.out.format("%10d", listOfPrimes[ROWOFFSET + C * RR]);
              }
            }
            System.out.println("");
          }
          System.out.println("\f");
          PAGENUMBER = PAGENUMBER + 1;
          PAGEOFFSET = PAGEOFFSET + RR * CC;
        }
    }
}

					 
