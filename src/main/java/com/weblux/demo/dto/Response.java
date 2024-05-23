package com.weblux.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Date date;
    private int output;

    public Response (final int output) {
        this.date = new Date ();
        this.output = output;
    }

}
