package com.weblux.demo.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Response {

    private Date date;
    private int output;

    public Response (final int output) {
        this.date = new Date ();
        this.output = output;
    }

}
