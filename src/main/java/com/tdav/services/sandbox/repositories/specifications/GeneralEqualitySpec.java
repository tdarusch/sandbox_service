package com.tdav.services.sandbox.repositories.specifications;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

public class GeneralEqualitySpec {

  public static <T> Specification<T> byCriteria(T entity) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      for (Field field : entity.getClass().getDeclaredFields()) {
        try {
          field.setAccessible(true);
          Object value = field.get(entity);
          if (value != null) {
            if (field.getType().equals(String.class)) {
              predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get(field.getName())), "%" + value.toString().toUpperCase() + "%"));
            } else if (field.getType().equals(LocalDate.class)) {
              predicates.add(criteriaBuilder.equal(root.get(field.getName()), value));
            }
          }
        } catch (IllegalAccessException e) {
          throw new InternalError("Error accessing field: " + field.getName(), e);
        }
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  } 
  
}
