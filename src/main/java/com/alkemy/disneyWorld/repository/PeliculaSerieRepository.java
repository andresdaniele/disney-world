package com.alkemy.disneyWorld.repository;

import com.alkemy.disneyWorld.entity.PeliculaSerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerieEntity, Long> {
}
