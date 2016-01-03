package app.woops.repositories;

import app.woops.domain.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Long> {

    Session findBySessionKey(String sessionKey);
}
