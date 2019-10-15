package io.trailblazer.trailblazerservice.model.dao;


import io.trailblazer.trailblazerservice.model.entity.Trail;
import org.springframework.data.repository.CrudRepository;

public interface TrailRepository extends CrudRepository<Trail, Long> {


}
