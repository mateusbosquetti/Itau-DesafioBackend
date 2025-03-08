package io.github.mateusbosquetti.itauapi.controller;

import io.github.mateusbosquetti.itauapi.model.dto.request.TransacaoRequestDTO;
import io.github.mateusbosquetti.itauapi.model.dto.response.EstatisticaResponseDTO;
import io.github.mateusbosquetti.itauapi.model.dto.response.TransacaoResponseDTO;
import io.github.mateusbosquetti.itauapi.service.TransacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TransacaoController {

    private TransacaoService service;

    @PostMapping("/transacao")
    public ResponseEntity<TransacaoResponseDTO> postTransacao(@RequestBody @Validated TransacaoRequestDTO transacaoRequestDTO) {
        return new ResponseEntity<>(service.criarTransacao(transacaoRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deleteTransacao() {
        service.deletarTransacao();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/estatistica")
    public ResponseEntity<EstatisticaResponseDTO> getEstatistica() {
        return new ResponseEntity<>(service.gerarEstatistica(), HttpStatus.OK);
    }
}