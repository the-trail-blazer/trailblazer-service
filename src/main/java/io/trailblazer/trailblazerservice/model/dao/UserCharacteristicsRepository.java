package io.trailblazer.trailblazerservice.model.dao;

import io.trailblazer.trailblazerservice.model.entity.User;
import io.trailblazer.trailblazerservice.model.entity.UserCharacteristics;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserCharacteristicsRepository extends CrudRepository<UserCharacteristics, Long> {


  Optional<UserCharacteristics> getUserCharacteristicsByUser(User user);


}
