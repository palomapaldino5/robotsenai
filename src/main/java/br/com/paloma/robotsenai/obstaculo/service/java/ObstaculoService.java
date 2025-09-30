package br.com.paloma.robotsenai.obstaculo.service.java;

import br.com.paloma.robotsenai.projeto.model.Obstaculo;
import br.com.paloma.robotsenai.projeto.repository.ObstaculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObstaculoService {

    private final ObstaculoRepository repository;

    public ObstaculoService(ObstaculoRepository repository) {
        this.repository = repository;
    }

    /**
     * Cria um novo obstáculo.
     *
     * @param obstaculo dados do obstáculo
     * @return obstáculo salvo
     */
    public Obstaculo criar(Obstaculo obstaculo) {
        return repository.save(obstaculo);
    }

    /**
     * Atualiza um obstáculo existente.
     *
     * @param id    identificador do obstáculo
     * @param dados dados novos
     * @return obstáculo atualizado (ou vazio, caso não exista)
     */
    public Optional<Obstaculo> atualizar(Long id, Obstaculo dados) {
        return repository.findById(id).map(o -> {
            if (dados.getNome() != null) o.setNome(dados.getNome());
            if (dados.getDescricao() != null) o.setDescricao(dados.getDescricao());
            return repository.save(o);
        });
    }

    /**
     * Deleta um obstáculo pelo ID.
     *
     * @param id identificador do obstáculo
     */
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Obstáculo não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }

    /**
     * Lista todos os obstáculos cadastrados.
     *
     * @return lista de obstáculos
     */
    public List<Obstaculo> listarTodos() {
        return repository.findAll();
    }

    /**
     * Busca um obstáculo pelo ID.
     *
     * @param id identificador do obstáculo
     * @return obstáculo encontrado (ou vazio, caso não exista)
     */
    public Optional<Obstaculo> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
