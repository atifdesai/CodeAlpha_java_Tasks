import java.util.ArrayList;
import java.util.Scanner;

class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    @Override
    public String toString() {
        return symbol + " - $" + price;
    }
}

class Portfolio {
    String stockSymbol;
    int quantity;
    double purchasePrice;

    Portfolio(String stockSymbol, int quantity, double purchasePrice) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    @Override
    public String toString() {
        return stockSymbol + " | Quantity: " + quantity + " | Purchase Price: $" + purchasePrice;
    }
}

public class StockTradingPlatform {
    static ArrayList<Stock> market = new ArrayList<>();
    static ArrayList<Portfolio> portfolio = new ArrayList<>();
    static double balance = 10000.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Predefined stocks
        market.add(new Stock("AAPL", 150.00));
        market.add(new Stock("GOOGL", 2800.00));
        market.add(new Stock("AMZN", 3400.00));
        market.add(new Stock("TSLA", 700.00));

        while (true) {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. View Portfolio");
            System.out.println("4. Sell Stock");
            System.out.println("5. View Balance");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewMarket();
                    break;
                case 2:
                    buyStock(scanner);
                    break;
                case 3:
                    viewPortfolio();
                    break;
                case 4:
                    sellStock(scanner);
                    break;
                case 5:
                    System.out.printf("Current Balance: $%.2f%n", balance);
                    break;
                case 6:
                    System.out.println("Thank you for using the Stock Trading Platform!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void viewMarket() {
        System.out.println("\n--- Stock Market ---");
        for (Stock stock : market) {
            System.out.println(stock);
        }
    }

    public static void buyStock(Scanner scanner) {
        viewMarket();
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.nextLine().toUpperCase();

        Stock selectedStock = null;
        for (Stock stock : market) {
            if (stock.symbol.equals(symbol)) {
                selectedStock = stock;
                break;
            }
        }

        if (selectedStock == null) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        double totalCost = quantity * selectedStock.price;

        if (totalCost > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= totalCost;
            portfolio.add(new Portfolio(symbol, quantity, selectedStock.price));
            System.out.printf("Successfully bought %d shares of %s for $%.2f%n", quantity, symbol, totalCost);
        }
    }

    public static void viewPortfolio() {
        if (portfolio.isEmpty()) {
            System.out.println("Your portfolio is empty.");
        } else {
            System.out.println("\n--- Your Portfolio ---");
            for (Portfolio p : portfolio) {
                System.out.println(p);
            }
        }
    }

    public static void sellStock(Scanner scanner) {
        viewPortfolio();
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.nextLine().toUpperCase();

        Portfolio toSell = null;
        for (Portfolio p : portfolio) {
            if (p.stockSymbol.equals(symbol)) {
                toSell = p;
                break;
            }
        }

        if (toSell == null) {
            System.out.println("Stock not found in portfolio.");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int quantity = scanner.nextInt();

        if (quantity > toSell.quantity) {
            System.out.println("You don't have enough shares.");
        } else {
            double currentPrice = 0;
            for (Stock stock : market) {
                if (stock.symbol.equals(symbol)) {
                    currentPrice = stock.price;
                    break;
                }
            }

            double earnings = quantity * currentPrice;
            balance += earnings;

            toSell.quantity -= quantity;
            if (toSell.quantity == 0) {
                portfolio.remove(toSell);
            }

            System.out.printf("Successfully sold %d shares of %s for $%.2f%n", quantity, symbol, earnings);
        }
    }
}
