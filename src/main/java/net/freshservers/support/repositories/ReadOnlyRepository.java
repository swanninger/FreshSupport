package net.freshservers.support.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID extends Serializable> extends Repository<T, ID> {

    Optional<T> findById(ID id);
}
