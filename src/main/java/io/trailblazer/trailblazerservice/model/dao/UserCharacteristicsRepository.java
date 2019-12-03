/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.model.dao;

import io.trailblazer.trailblazerservice.model.entity.User;
import io.trailblazer.trailblazerservice.model.entity.UserCharacteristics;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserCharacteristicsRepository extends CrudRepository<UserCharacteristics, Long> {


  Optional<UserCharacteristics> getUserCharacteristicsByUser(User user);


}
