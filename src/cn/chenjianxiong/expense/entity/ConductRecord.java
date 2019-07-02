package cn.chenjianxiong.expense.entity;

import java.util.Date;

public class ConductRecord {
    private int id;
    private int expenseAccountId;
    private String conductor;
    private Date conductTime;
    private String conductClass;
    private String conductResult;
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

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public Date getConductTime() {
        return conductTime;
    }

    public void setConductTime(Date conductTime) {
        this.conductTime = conductTime;
    }

    public String getConductClass() {
        return conductClass;
    }

    public void setConductClass(String conductClass) {
        this.conductClass = conductClass;
    }

    public String getConductResult() {
        return conductResult;
    }

    public void setConductResult(String conductResult) {
        this.conductResult = conductResult;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ConductRecord{" +
                "id=" + id +
                ", expenseAccountId=" + expenseAccountId +
                ", conductor='" + conductor + '\'' +
                ", conductTime=" + conductTime +
                ", conductClass='" + conductClass + '\'' +
                ", conductResult='" + conductResult + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
