package ch.supsi.spbd.authSystem.repository;


import ch.supsi.spbd.authSystem.model.Justification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface JustificationRepository extends JpaRepository<Justification,Long> {
    @Query
    List<Justification> findAllByUserID(String user_id);
}
