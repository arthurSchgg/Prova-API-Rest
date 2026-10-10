package br.com.avaliacao.api_telemetria.repository;

import br.com.avaliacao.api_telemetria.entity.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe de setores responsável por se conectar com o banco de dados
 */
@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {

    /**
     * Verifica se existe o nome
     *
     * @param nome Nome do setor
     * @return True se existir e false senão
     */
    boolean existsByNomeIgnoreCase(String nome);

    /**
     * Verifica se existe o nome pelo código e não pelo ID
     *
     * @param nome Nome do setor
     * @param id   Identificador único do Setor
     * @return True se existir e false senão
     */
    boolean existsByNomeIgnoreCaseAndIdNot(String nome, Integer id);
}
