package com.alkemy.disneyWorld.repository.specifications;

import com.alkemy.disneyWorld.dto.CharacterFilterDTO;
import com.alkemy.disneyWorld.entity.CharacterEntity;
import com.alkemy.disneyWorld.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    //Receives a filterDTO with params of search as attributes.
    public Specification<CharacterEntity> getCharacterByFilters(CharacterFilterDTO characterFilterDTO) {

        //lambda function. Create a criteria to get information from DB through repository.
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            //
            if (StringUtils.hasLength(characterFilterDTO.getName())) {            //hasLength checks if name is not an empty or null field
                predicates.add(
                        criteriaBuilder.like(                                     //Criteria that checks if any characters contains on its name the received string
                                criteriaBuilder.lower(root.get("name")),
                                "%" + characterFilterDTO.getName().toLowerCase() + "%")
                );
            }

            if (characterFilterDTO.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), characterFilterDTO.getAge())   //Build criteria to find an entity that equals age params
                );
            }

            if (!CollectionUtils.isEmpty(characterFilterDTO.getMoviesIdSet())) {
                Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);   //Establishes a join relationship between movie and character entities.
                Expression<Long> moviesId = join.get("id");                                         //Build criteria that compares id provided with movie id.
                predicates.add(moviesId.in(characterFilterDTO.getMoviesIdSet()));
            }

            //Remove duplicates
            query.distinct(true);

            //Order by filtering
            String orderByField = "name";
            query.orderBy(
                    characterFilterDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField))
                            :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));   //Returns a list of the predicates we build
        };
    }
}
