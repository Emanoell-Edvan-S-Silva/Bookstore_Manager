package wdabookstore.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wdabookstore.bookstoremanager.entities.RentEntity;

import java.util.List;

public interface RentRepository extends JpaRepository<RentEntity, Long> {

    @Query(value = "SELECT * FROM tb_rents WHERE status IN ('Atrasado', 'No prazo')", nativeQuery = true)
    List<RentEntity> findLateAndOnTimeRents();

    @Query(value = "SELECT * FROM tb_rents WHERE status = 'Pendente'", nativeQuery = true)
    List<RentEntity> findOutstandingRents();
}
