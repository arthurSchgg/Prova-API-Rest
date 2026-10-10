package br.com.avaliacao.api_telemetria.repository;

import br.com.avaliacao.api_telemetria.entity.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Classe de motores responsável por se conectar com o banco de dados
 */
@Repository
public interface MotorRepository extends JpaRepository<Motor, Integer> {

    /**
     * Busca pelo código ativo do motor
     *
     * @param codigoAtivo Código do motor
     * @return O motor cujo é o mesmo que o buscado
     */
    Optional<Motor> findByCodigoAtivo(String codigoAtivo);

    /**
     * Faz a verificação se existe o código
     *
     * @param codigoAtivo Código do motor
     * @return True se existir e False senão existir
     */
    boolean existsByCodigoAtivo(String codigoAtivo);

    /**
     * Faz a verificação se existe pelo código e não pelo ID
     *
     * @param codigoAtivo Código do motor
     * @param id          Identificador único do motor
     * @return True se existir e False senão existir
     */
    boolean existsByCodigoAtivoAndIdNot(String codigoAtivo, Integer id);
}
