# LearnToCode_Capstones

The parts of the code that I found particularly interesting to write were the parts that made the application more useful and user friendly. For example, I orginally prompted users to input the date and time of all transactions in a YYYY-MM-DD, HH-MM-SS format, however I quickly realized this was a bit silly and also subject to human error. So instead I used LocalDateTime.now() method to record the exact moment of the transaction, without having to ask the user to remember the exact date and time down to the second. This also saved me a few lines of code. 
Another example of increasing user friendliness and usability was ommiting the description and vendor prompts when a user makes a deposit. Instead, I used a default description (deposit) and a default vendor (user) when a user makes a deposit.

Financial Transaction Manager
A command-line Java application for managing financial transactions, including deposits, payments, and transaction history. Transactions are stored in a CSV file and can be viewed or filtered via a Ledger Screen and Reports Screen.
Features

Home Screen:
D: Add Deposit (prompts for amount; defaults description to "deposit", vendor to "user").
P: Make Payment (prompts for description, vendor, amount; saves as negative).
L: View Ledger (transaction history).
X: Exit.


Ledger Screen:
A: Display all transactions (newest first).
D: Display deposits (amount > 0).
P: Display payments (amount < 0).
R: Access Reports Screen.
H: Return to Home Screen.
After displaying transactions (A, D, P), pauses with "Press Enter to continue..." to return to Ledger menu.


Reports Screen:
1: Month To Date (current month).
2: Previous Month.
3: Year To Date (current year).
4: Previous Year.
5: Search by Vendor (partial match).
6: Custom Search (filter by date, time, description, vendor, amount; optional inputs).
0: Back to Ledger Screen.
Pauses after each report with "Press Enter to continue...".


Transaction Storage:
Stored in transactions.csv (format: date|time|description|vendor|amount).
Example: 2023-04-29|14:32:00|deposit|user|500.00.
Loaded at startup, appended with new transactions.


Sorting: Transactions displayed newest first.
Input Validation: Handles invalid inputs (e.g., non-positive amounts, invalid dates).

Prerequisites

Java 8 or higher.
IDE (e.g., IntelliJ IDEA) or terminal for compilation and execution.
Optional: Existing transactions.csv in project root.

Setup
Project Structure
your-project/
├── src/
│   └── com/
│       └── pluralsight/
│           ├── Main.java
│           ├── Ledger.java
│           ├── Transaction.java
├── transactions.csv  (created automatically if not present)
└── README.md

IntelliJ IDEA

Create a new Java project or use an existing one.
Create directory src/com/pluralsight.
Add the three source files:
src/com/pluralsight/Main.java
src/com/pluralsight/Ledger.java
src/com/pluralsight/Transaction.java


Set the working directory:
Go to Run > Edit Configurations.
Select Main (or create a new Application configuration).
Set Working directory to the project root (e.g., /path/to/your-project).


Run the program:
Right-click Main.java > Run 'Main.main()'.



Command Line

Navigate to the project root:cd /path/to/your-project


Create directory structure:mkdir -p src/com/pluralsight


Place Main.java, Ledger.java, Transaction.java in src/com/pluralsight.
Compile:javac src/com/pluralsight/*.java


Run:java -cp src com.pluralsight.Main



Usage

Start the Program:

Run via IntelliJ or command line.
The Home Screen appears:Home Screen:
D) Add Deposit
P) Make Payment
L) Ledger (Transaction History)
X) Exit
Enter your choice:




Add a Deposit:

Select D, enter amount:Enter your choice: D
Enter amount (positive): 500.00
Deposit added successfully.
Transaction saved to: /path/to/your-project/transactions.csv


Saved as: 2023-04-29|14:32:00|deposit|user|500.00.


Make a Payment:

Select P, enter details:Enter your choice: P
Enter description: Utility Bill
Enter vendor: Electric Co
Enter amount (positive, will be saved as negative): 75.00
Payment added successfully.
Transaction saved to: /path/to/your-project/transactions.csv


Saved as: 2023-04-29|14:31:00|Utility Bill|Electric Co|-75.00.


View Ledger:

Select L, then A:Enter your choice: L
Ledger Screen (Transaction History):
A) All Transactions
D) Deposits
P) Payments
R) Reports
H) Home
Enter your choice: A

All Transactions:
Date         Time       Description          Vendor          Amount    
2023-04-29   14:32:00   deposit              user            $500.00    
2023-04-29   14:31:00   Utility Bill         Electric Co     $-75.00    
2023-04-29   14:30:45   Groceries            Walmart         $-45.75    
2023-04-29   14:30:00   Salary               Employer        $2000.00   
2023-04-15   11:15:00   Invoice 1001 paid    Joe             $1500.00   
2023-04-15   10:13:25   ergonomic keyboard   Amazon          $-89.50    

Press Enter to continue...


Press Enter to return to Ledger Screen.


Run Reports:

Select R, then 5 (Search by Vendor):Enter your choice: R
Reports Screen:
1) Month To Date
2) Previous Month
3) Year To Date
4) Previous Year
5) Search by Vendor
6) Custom Search
0) Back
Enter your choice: 5
Enter vendor name (partial match): user
Search by Vendor: user:
Date         Time       Description          Vendor          Amount    
2023-04-29   14:32:00   deposit              user            $500.00    

Press Enter to continue...


Press Enter to return to Reports Screen, 0 to return to Ledger Screen.


Exit:

Select X from Home Screen:Enter your choice: X
Exiting...





Troubleshooting

CSV File Not Saving:

Symptom: No transactions.csv or no updates.
Check: Console output for “Transaction saved to: [path]” or “Error saving transaction to [path]: [message]”.
Fix:
Ensure write permissions in project root:ls -l /path/to/your-project/transactions.csv

Should show rw for user.
If path is incorrect (e.g., out/), update Main.java:private static final File CSV_FILE_PATH = new File("/path/to/your-project/transactions.csv");


Replace /path/to/your-project with project root (e.g., C:/Users/YourUser/IdeaProjects/YourProject).




Input Skipping:

Symptom: Home Screen skips prompts after Ledger/Reports.
Check: Test sequence: L → A → Enter → H → D.
Fix: Should be resolved by Scanner.nextLine(). If not, report console output.


Reports Show Incorrect Data:

Symptom: Month/Year reports show unexpected transactions.
Check: Verify transactions.csv dates match current date (e.g., 2023-04-29).
Fix: Test with transactions across months/years.


Program Hangs:

Symptom: No response after Enter in Ledger/Reports.
Check: Console errors or terminal vs. IDE behavior.
Fix: Run in terminal (e.g., Command Prompt, Git Bash) to isolate IDE issues.



If issues persist, share:

Console output.
transactions.csv sample.
Path from “Transaction saved to: [path]”.

License
MIT License (update as needed).

Built with Java 8+. Contributions welcome!

