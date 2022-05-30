package Entities;

public class Pay {
    private int id;
    private int amount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Pay(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}
