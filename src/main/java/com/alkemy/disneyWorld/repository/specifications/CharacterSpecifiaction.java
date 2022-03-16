package com.alkemy.disneyWorld.repository.specifications;

import com.alkemy.disneyWorld.dto.CharacterFilterDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

@Component
public class CharacterSpecifiaction {

    public Specification<CharacterSpecifiaction> getCharacterByFilters (CharacterFilterDTO characterFilterDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            return null;
        };
    }
}
