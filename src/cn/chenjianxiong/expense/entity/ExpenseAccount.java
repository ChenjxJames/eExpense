package cn.chenjianxiong.expense.entity;

import java.util.Date;

public class ExpenseAccount {
    private int id;
    private String reason;
    private String creator;
    private Date createTime;
    private String conductor;
    private double price;
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ExpenseAccount{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", conductor='" + conductor + '\'' +
                ", price=" + price +
                ", state='" + state + '\'' +
                '}';
    }
}