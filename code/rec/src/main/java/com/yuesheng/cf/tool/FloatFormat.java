package com.yuesheng.cf.tool;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;


/**
 * Price 设置为#0.00格式
 */
public class FloatFormat extends JsonSerializer<Float> {
    private DecimalFormat df = new DecimalFormat("#0.0");

    public FloatFormat() {
    }

    public void serialize(Float value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(this.df.format(value));
    }

}
