package com.weblux.demo.commons.dto.customer;

import java.util.List;

public record CustomerInformation(Integer id,
                                  String name,
                                  double balance,
                                  List<Holding> holdings) {
}
