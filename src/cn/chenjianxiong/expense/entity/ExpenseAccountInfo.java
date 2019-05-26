package cn.chenjianxiong.expense.entity;

public class ExpenseAccountInfo {
    private int id;
    private int expenseAccountId;
    private String expenseClass;
    private double price;
    private String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExpenseAccountId() {
        return expenseAccountId;
    }

    public void setExpenseAccountId(int expenseAccountId) {
        this.expenseAccountId = expenseAccountId;
    }

    public String getExpenseClass() {
        return expenseClass;
    }

    public void setExpenseClass(String expenseClass) {
        this.expenseClass = expenseClass;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ExpenseAccountInfo{" +
                "id=" + id +
                ", expenseAccountId=" + expenseAccountId +
                ", expenseClass='" + expenseClass + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                '}';
    }
}
