package com.yuesheng.cf.tool;

public class PackJson {
    private String status;
    private Object values;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setValues(Object data) {
        this.values = data;
    }

    public Object getValues() {
        return this.values;
    }
}
