package io.github.mateusbosquetti.itauapi.model.entity;

import io.github.mateusbosquetti.itauapi.model.dto.response.TransacaoResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@Entity
@Table(name = "transacao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
    private OffsetDateTime dataHora;

    public TransacaoResponseDTO toDto() {
        return new TransacaoResponseDTO(
                this.id,
                this.valor,
                this.dataHora
        );
    }
}
