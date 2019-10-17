package io.trailblazer.trailblazerservice.model.dao;

import io.trailblazer.trailblazerservice.model.entity.Content;
import org.springframework.data.repository.CrudRepository;

public interface ContentRepository extends CrudRepository<Content,Long> {


}
