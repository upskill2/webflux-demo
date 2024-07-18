package com.webflux.customerportfolio.dto;

import java.util.List;

public record CustomerInformation(Integer id,
                                  String name,
                                  double balance,
                                  List<Holding> holdings) {
}
