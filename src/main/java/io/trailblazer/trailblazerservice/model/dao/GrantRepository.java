package io.trailblazer.trailblazerservice.model.dao;

import io.trailblazer.trailblazerservice.model.entity.Grant;
import org.springframework.data.repository.CrudRepository;

public interface GrantRepository extends CrudRepository<Grant, Long> {

}
