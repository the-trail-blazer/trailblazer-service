/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.model.dao;

import io.trailblazer.trailblazerservice.model.entity.Grant;
import org.springframework.data.repository.CrudRepository;

public interface GrantRepository extends CrudRepository<Grant, Long> {

}
