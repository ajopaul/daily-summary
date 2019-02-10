# daily-summary

Assumptions:
  1. Definition of client is combination of 'CLIENT TYPE, CLIENT NUMBER, ACCOUNT NUMBER, SUBACCOUNT NUMBER'
  2. Definition of product is combination of 'EXCHANGE CODE, PRODUCT GROUP CODE, SYMBOL, EXPIRATION DATE'

How to Run:

  1. Checkout source onto a *nix machine with Java8 and mvn 3.x
  2. From the root directory run the file with the below command:
  
      `mvn clean install exec:java -Dexec.args="<Path to Input.txt file>"`
      
      or to provide the `Input.txt` path into the terminal. Run the below command. The program will prompt for the full path to be entered.
      
      `mvn clean install exec:java`
  3.  By default the `Output.csv` will be created in the current directory.
