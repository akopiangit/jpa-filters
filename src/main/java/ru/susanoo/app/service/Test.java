package ru.susanoo.app.service;

import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.susanoo.app.db.entity.Product;
import ru.susanoo.app.db.entity.Product_;
import ru.susanoo.app.db.repository.ProductRepository;
import ru.susanoo.jpa_filters.model.ExpressionType;
import ru.susanoo.jpa_filters.model.FilterDTO;
import ru.susanoo.jpa_filters.model.Operator;
import ru.susanoo.jpa_filters.model.SearchSpecification;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class Test {

    private final ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(Test.class);

    public Test(@NonNull ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //    @PostConstruct
    public void initDb() {
        for (int i = 0; i < 1000; i++) {
            Product product = new Product();
            product.setPrice(BigDecimal.valueOf(i));
            product.setName(RandomStringUtils.randomAlphabetic(10));
            product.setDescription(RandomStringUtils.randomAlphabetic(1000));
            productRepository.save(product);
        }
    }

//    public SearchDTO getSearchDTO() {
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setPageSize(5);
//        searchDTO.setPageSize(1);
//
//        FilterDTO filterDTO = new FilterDTO();
//        filterDTO.setFieldName("name");
//        filterDTO.setFieldValue("phone");
//        filterDTO.setOperator(Operator.EQUAL);
//        FiltersDTO filtersDTO = new FiltersDTO();
//        filtersDTO.setFilters(Collections.singletonList(filterDTO));
//
//        searchDTO.setFilters(Collections.singletonList(filtersDTO));
//        return searchDTO;
//    }


    @PostConstruct
    public void test() {
        SearchSpecification<Product> searchSpecification = new SearchSpecification<Product>();
        FilterDTO filterEqual = new FilterDTO();
        filterEqual.setFieldPath(Product_.PRICE);
        filterEqual.setOperator(Operator.NOT_EQUAL);
        filterEqual.setValue("1");

        FilterDTO filterLess = new FilterDTO();
        filterLess.setFieldPath(Product_.PRICE);
        filterLess.setOperator(Operator.LESS);
        filterLess.setValue("3");


        Specification<Product> spec = searchSpecification.joinFilters(asList(filterLess, filterEqual), ExpressionType.OR);
        List<Product> products = productRepository.findAll(spec);
        log.info("{}", products.size());
        return;
    }

}
