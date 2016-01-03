package app.woops.repositories;

import app.woops.domain.Transfer;
import app.woops.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TransferRepository extends PagingAndSortingRepository<Transfer, Long> {

    @Query("SELECT t FROM Transfer t WHERE t.to = ?1 or t.from = ?1 order by id desc")
    Iterable<Transfer> findAllToAndFromUser(User user);
}
