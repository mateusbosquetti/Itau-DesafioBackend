package io.github.mateusbosquetti.itauapi.service;

import io.github.mateusbosquetti.itauapi.model.dto.request.TransacaoRequestDTO;
import io.github.mateusbosquetti.itauapi.model.dto.response.EstatisticaResponseDTO;
import io.github.mateusbosquetti.itauapi.model.dto.response.TransacaoResponseDTO;
import io.github.mateusbosquetti.itauapi.model.entity.Transacao;
import io.github.mateusbosquetti.itauapi.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransacaoService {

    private TransacaoRepository repository;

    public TransacaoResponseDTO criarTransacao(TransacaoRequestDTO transacaoRequestDTO) {
        Transacao transacao = transacaoRequestDTO.toEntity();
        repository.save(transacao);
        return transacao.toDto();
    }

    public void deletarTransacao() {
        repository.deleteAll();
    }

    public EstatisticaResponseDTO gerarEstatistica() {
        return null;
    }

}
