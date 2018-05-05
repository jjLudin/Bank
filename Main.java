import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        // Create and add accounts to a list treated like a database
        List<Bank> accounts = new ArrayList<>();
        Bank John = new Bank();
        Bank Laurel = new Bank();
        John.Account(1234, 1234, "John", 100.00);
        Laurel.Account(5678, 5678, "Laurel", 200.00);
        accounts.add(John);
        accounts.add(Laurel);

        // Verify account exists
        int accountIndex = verifyAccount(accounts);

        // Require account to enter pin to log in
        verifyPin(accounts, accountIndex);

        // While loop: Prompt user to bank options or main menu
        mainMenu(accounts, accountIndex);
    } // main()


    private static int verifyAccount(List<Bank> accounts)
    {
        Scanner s = new Scanner(System.in);
        int accountIndex;

        System.out.println();
        System.out.println("- Welcome to the Bank -");
        System.out.println();

        Verification: while(true)
        {
            System.out.print("Enter your account number to get started: ");
            int accountNumber = s.nextInt();

            for(int i = 0; i < accounts.size(); i++)
            {
                if(accounts.get(i).getAccountNumber() == accountNumber)
                {
                    accountIndex = i;
                    break Verification;
                }
            }
            System.out.println("*** Error: That account does not exist. Please try again. ***");
        }
        return accountIndex;
    } // verifyAccount()


    private static void verifyPin(List<Bank> accounts, int accountIndex)
    {
        Scanner s = new Scanner(System.in);
        int attempts = 3;

        while(true)
        {
            System.out.print("Please enter your pin to log in: ");

            int pin = s.nextInt();
            if(pin == accounts.get(accountIndex).getPin())
            {
                System.out.println();
                System.out.println("Welcome " + accounts.get(accountIndex).getName() + "!");
                break;
            }

            else
            {
                if(attempts == 1)
                {
                    System.out.println();
                    System.out.println("*- Number of attempts exhausted. Logging out now. -*");
                    System.out.println();
                    verifyAccount(accounts);
                }

                else
                {
                    attempts -= 1;
                    System.out.println("*** Error: Pin was incorrect. Attempts left: " + attempts + ". Please try again. ***");
                }
            }
        }
    } // verifyPin()


    private static void mainMenu(List<Bank> accounts, int accountIndex)
    {
        Scanner s = new Scanner(System.in);
        while(true)
        {
            System.out.println();
            System.out.println("Your current balance: " + accounts.get(accountIndex).getAmount());
            System.out.println();
            System.out.println("- Main Menu -");
            System.out.println("Options:");
            System.out.println("1. Make a deposit");
            System.out.println("2. Make a withdrawal");
            System.out.println("3. Exit");

            System.out.println();
            System.out.print("Enter option (number): ");
            int option = s.nextInt();

            // 1. Make a deposit
            if(option == 1)
            {
                deposit(accounts, accountIndex);
            }

            // 2. Make a withdrawal
            else if(option == 2)
            {
                withdraw(accounts, accountIndex);
            }

            // 3. Exit
            else if(option == 3)
            {
                System.out.println("Goodbye!");
                verifyAccount(accounts);
                System.exit(0);
            }

            else
            {
                System.out.println("*** Error: Invalid option. Please try again. ***");
                System.out.println();
            }
        }
    } // mainMenu()


    private static void deposit(List<Bank> accounts, int accountIndex)
    {
        Scanner s = new Scanner(System.in);
        System.out.println();
        System.out.println("- Deposit -");
        System.out.print("Enter the amount to deposit: ");
        double depositAmount = s.nextDouble();
        double balance = accounts.get(accountIndex).getAmount() + depositAmount;
        accounts.get(accountIndex).setAmount(balance);
        System.out.println("Your balance: " + accounts.get(accountIndex).getAmount());
        promptIfAnotherTransaction(accounts, accountIndex);
    } // deposit()


    private static void withdraw(List<Bank> accounts, int accountIndex)
    {
        Scanner s = new Scanner(System.in);
        System.out.println();
        System.out.println("- Withdraw -");
        System.out.print("Enter the amount to withdraw: ");
        double withdrawAmount = s.nextDouble();

        if(withdrawAmount > accounts.get(accountIndex).getAmount())
        {
            System.out.println("*** Error: Withdraw amount exceeds account balance. Please try again.");
            withdraw(accounts, accountIndex);
        }

        double balance = accounts.get(accountIndex).getAmount() - withdrawAmount;
        accounts.get(accountIndex).setAmount(balance);
        System.out.println("Your balance: " + accounts.get(accountIndex).getAmount());
        promptIfAnotherTransaction(accounts, accountIndex);
    }


    private static void promptIfAnotherTransaction(List<Bank> accounts, int accountIndex)
    {
        Scanner s = new Scanner(System.in);
        while(true)
        {
            System.out.print("Would you like to make another transaction? Yes (Y) or No (N): ");
            String option = s.next();

            if(option.equals("Y"))
            {
                mainMenu(accounts, accountIndex);
            }

            else if(option.equals("N"))
            {
                System.out.println("Goodbye!");
                verifyAccount(accounts);
            }

            else
            {
                System.out.println("*** Error: Invalid option. Please try again. ***");
            }
        }
    } // promptIfAnotherTransaction()
}
