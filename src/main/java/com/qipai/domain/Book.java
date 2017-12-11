package com.qipai.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private Integer id;
    private String name;
    private double price;
    private String type;
    private LocalDate publishDate;

}
