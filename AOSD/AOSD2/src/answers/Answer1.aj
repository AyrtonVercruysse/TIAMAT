package answers;
 
aspect Answer1 {
  declare error:
    get(java.io.PrintStream System.out) && within(figures..*):
      "Illegal access to System.out";
}
