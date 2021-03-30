package thesis.stocker.DTO;

public class StockDTO {

   private String symbol;
   private double amount;

   public StockDTO(String symbol, double amount) {
       this.symbol = symbol;
       this.amount = amount;
   }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
