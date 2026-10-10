package br.com.avaliacao.api_telemetria.repository;

import br.com.avaliacao.api_telemetria.entity.HistoricoTelemetria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe de historicos responsável por se conectar com o banco de dados
 */
@Repository
public interface HistoricoTelemetriaRepository extends JpaRepository<HistoricoTelemetria, Long> {

    /**
     * Busca o Id do motor ordenando pela data de forma decrescente
     *
     * @param motorId  Identificador do motor
     * @param pageable Serve para fazer a limitação da lista
     * @return Uma lista ordenada de historicos
     */
    Page<HistoricoTelemetria> findByMotorIdOrderByDataHoraDesc(Integer motorId, Pageable pageable);
}