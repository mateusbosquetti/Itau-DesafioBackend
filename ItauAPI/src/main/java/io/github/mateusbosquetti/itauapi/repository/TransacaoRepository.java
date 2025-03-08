package io.github.mateusbosquetti.itauapi.repository;

import io.github.mateusbosquetti.itauapi.model.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
}
