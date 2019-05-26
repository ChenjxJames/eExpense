package cn.chenjianxiong.expense.entity;

import java.util.Date;

public class ConductRecord {
    private int id;
    private int expense_account_id;
    private String conductor;
    private Date conduct_time;
    private String conduct_class;
    private String conduct_result;
    private String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExpense_account_id() {
        return expense_account_id;
    }

    public void setExpense_account_id(int expense_account_id) {
        this.expense_account_id = expense_account_id;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public Date getConduct_time() {
        return conduct_time;
    }

    public void setConduct_time(Date conduct_time) {
        this.conduct_time = conduct_time;
    }

    public String getConduct_class() {
        return conduct_class;
    }

    public void setConduct_class(String conduct_class) {
        this.conduct_class = conduct_class;
    }

    public String getConduct_result() {
        return conduct_result;
    }

    public void setConduct_result(String conduct_result) {
        this.conduct_result = conduct_result;
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
                ", expense_account_id=" + expense_account_id +
                ", conductor='" + conductor + '\'' +
                ", conduct_time=" + conduct_time +
                ", conduct_class='" + conduct_class + '\'' +
                ", conduct_result='" + conduct_result + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
