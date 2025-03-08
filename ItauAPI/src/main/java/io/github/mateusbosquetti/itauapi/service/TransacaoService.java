package io.github.mateusbosquetti.itauapi.service;

import io.github.mateusbosquetti.itauapi.model.dto.request.TransacaoRequestDTO;
import io.github.mateusbosquetti.itauapi.model.dto.response.EstatisticaResponseDTO;
import io.github.mateusbosquetti.itauapi.model.dto.response.TransacaoResponseDTO;
import io.github.mateusbosquetti.itauapi.model.entity.Transacao;
import io.github.mateusbosquetti.itauapi.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

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

    public List<Transacao> ajustarFusoHorarioParaBrasil(List<Transacao> transacoes) {
        ZoneOffset fusoBrasil = ZoneOffset.of("-03:00");

        for (Transacao transacao : transacoes) {
            OffsetDateTime dataHoraAtual = transacao.getDataHora();

            OffsetDateTime dataHoraBrasil = dataHoraAtual.withOffsetSameInstant(fusoBrasil);

            transacao.setDataHora(dataHoraBrasil);
        }

        return transacoes;
    }

    public EstatisticaResponseDTO gerarEstatistica() {
        OffsetDateTime horarioAtual = OffsetDateTime.now();
        OffsetDateTime horario1minutosAtras = horarioAtual.minusSeconds(60);
        List<Transacao> transacoesValidas = ajustarFusoHorarioParaBrasil(repository.findAll())
                .stream().filter(transacao -> !transacao.getDataHora().isBefore(horario1minutosAtras)).toList();

        if (transacoesValidas.isEmpty()) {
            return new EstatisticaResponseDTO(
                    0,
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0)
            );
        }

        Integer count = transacoesValidas.size();
        BigDecimal sum = BigDecimal.valueOf(0);
        BigDecimal min = transacoesValidas.getFirst().getValor();
        BigDecimal max = BigDecimal.valueOf(0);

        for (Transacao transacao : transacoesValidas) {
            sum = sum.add(transacao.getValor());

            if (transacao.getValor().compareTo(max) > 0) {
                max = transacao.getValor();
            }
            if (transacao.getValor().compareTo(min) < 0) {
                min = transacao.getValor();
            }
        }

        BigDecimal avg = sum.divide(BigDecimal.valueOf(transacoesValidas.size()));

        return new EstatisticaResponseDTO(
                count,
                sum,
                avg,
                min,
                max
        );
    }

}
