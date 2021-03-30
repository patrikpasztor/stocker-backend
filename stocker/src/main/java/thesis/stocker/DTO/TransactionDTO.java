package thesis.stocker.DTO;

public class TransactionDTO {

    String user;
    String stock;
    double amount;
    double stockPrice;

    public TransactionDTO(String user, String stock, double amount, double stockPrice) {
        this.user = user;
        this.stock = stock;
        this.amount = amount;
        this.stockPrice = stockPrice;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }
}
