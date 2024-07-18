package com.webflux.customerportfolio.service;

import com.webflux.customerportfolio.dto.CustomerInformation;
import com.webflux.customerportfolio.exceptions.ApplicationExceptions;
import com.webflux.customerportfolio.mapper.DtoMapper;
import com.webflux.customerportfolio.repository.CustomerRepository;
import com.webflux.customerportfolio.repository.PortfolioItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    public CustomerService (CustomerRepository customerRepository, PortfolioItemRepository portfolioItemRepository, final DtoMapper dtoMapper) {
        this.customerRepository = customerRepository;
        this.portfolioItemRepository = portfolioItemRepository;
        this.dtoMapper = dtoMapper;
    }

    private final CustomerRepository customerRepository;
    private final PortfolioItemRepository portfolioItemRepository;
    private final DtoMapper dtoMapper;


    public Mono<CustomerInformation> getCustomerInfo (int customerId) {
        return customerRepository.findById (customerId)
                .switchIfEmpty (ApplicationExceptions.customerNotFound (customerId))
                .flatMap (customer -> portfolioItemRepository.findAllByCustomerId (customerId)
                        .collectList ()
                        .map (portfolioItems -> dtoMapper.mapToCustomerInformation (customer, portfolioItems)));
    }

}
