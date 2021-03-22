package thesis.stocker.DTO;

public class StockPriceDTO {

    //Open price of the day
    private double o;
    //High price of the day
    private double h;
    //Low price of the day
    private double l;
    //Current price
    private double c;
    //Previous close price
    private double pc;

    public StockPriceDTO(double o, double h, double l, double c, double pc) {
        this.o = o;
        this.h = h;
        this.l = l;
        this.c = c;
        this.pc = pc;
    }

    public double getO() {
        return o;
    }

    public void setO(double o) {
        this.o = o;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getPc() {
        return pc;
    }

    public void setPc(double pc) {
        this.pc = pc;
    }
}
