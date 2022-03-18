package com.alkemy.disneyWorld.repository.specifications;

import com.alkemy.disneyWorld.dto.MovieFilterDTO;
import com.alkemy.disneyWorld.entity.GenreEntity;
import com.alkemy.disneyWorld.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getMovieByFilters(MovieFilterDTO movieFilterDTO) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(movieFilterDTO.getTitle())) {            //hasLenght checks if name is not an empty or null field
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + movieFilterDTO.getTitle().toLowerCase() + "%")
                );
            }


            if (movieFilterDTO.getGenreID() != null) {
                Join<GenreEntity, MovieEntity> join = root.join("genre", JoinType.INNER);
                Expression<Long> genreId = join.get("id");
                predicates.add(genreId.in(movieFilterDTO.getGenreID()));
            }


            //Remove duplicates
            query.distinct(true);

            //Order by filtering

            String orderByField = "title";
            query.orderBy(
                    movieFilterDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField))
                            :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));   //Returns a list of the predicates we build
        };
    }
}
