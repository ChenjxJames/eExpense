package cn.chenjianxiong.expense.po;

/**
 * @author ：James Chen
 * @date ：Created in 2019/5/24 17:21
 * @description：返回信息模型类（后端返回信息的包装类）
 * @modified By：
 * @version: 1.0$
 */
public class Result {
    private Integer state;
    private String info;

    public Result() {

    }

    public Result(Integer state, String info) {
        this.state = state;
        this.info = info;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Result{" +
                "state=" + state +
                ", info='" + info + '\'' +
                '}';
    }
}
