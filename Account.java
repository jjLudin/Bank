public class Account {
    private int accountNumber;
    private int pin;
    private String name;
    private double amount;

    // Constructor
    public Account(int accountNumber, int pin, String name, double amount)
    {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.name = name;
        this.amount = amount;
    }

    // Getters
    public int getAccountNumber() { return this.accountNumber; }

    public int getPin() { return this.pin; }

    public String getName() { return this.name; }

    public double getAmount() { return this.amount; }

    // Setters
    public void setAmount(double amount) { this.amount = amount; }
}
