package br.com.paloma.robotsenai.projeto.repository;

import br.com.paloma.robotsenai.projeto.model.Obstaculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObstaculoRepository extends JpaRepository<Obstaculo, Long> {
}
