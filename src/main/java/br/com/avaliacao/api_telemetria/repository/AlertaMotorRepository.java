package br.com.avaliacao.api_telemetria.repository;

import br.com.avaliacao.api_telemetria.entity.AlertaMotor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe de alertas responsável por se conectar com o banco de dados
 */
@Repository
public interface AlertaMotorRepository extends JpaRepository<AlertaMotor, Integer> {

    /**
     * Faz a busca por motores ordenando em ordem decrescente da data de alerta
     *
     * @param motorId  Identificador do motor
     * @param pageable Serve para fazer a limitação da lista
     * @return Uma lista ordenada de alertas
     */
    Page<AlertaMotor> findByMotorIdOrderByDataAlertaDesc(Integer motorId, Pageable pageable);

    /**
     * Faz a busca por resolvido ordenando em ordem decrescente da data de alerta
     *
     * @param resolvido Verificador de resolução
     * @param pageable  Serve para fazer a limitação da lista
     * @return Uma lista ordenada de alertas resolvidas
     */
    Page<AlertaMotor> findByResolvidoOrderByDataAlertaDesc(Boolean resolvido, Pageable pageable);
}
