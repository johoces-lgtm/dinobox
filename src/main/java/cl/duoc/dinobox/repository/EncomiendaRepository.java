package cl.duoc.dinobox.repository;

import cl.duoc.dinobox.model.Encomienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EncomiendaRepository extends JpaRepository<Encomienda, Long> {
    // Método mágico de JPA para buscar por código de seguimiento
    Optional<Encomienda> findByCodigo(String codigo);
}