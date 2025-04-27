package com.guarani.teste_backend.domain.entity;

import com.guarani.teste_backend.domain.enums.FormaPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private boolean confirmado;

    public static PagamentoBuilder builder() {
        return new PagamentoBuilder();
    }

    public static class PagamentoBuilder {
        private FormaPagamento formaPagamento;
        private boolean confirmado;

        public PagamentoBuilder formaPagamento(FormaPagamento formaPagamento) {
            this.formaPagamento = formaPagamento;
            return this;
        }

        public PagamentoBuilder confirmado(boolean confirmado) {
            this.confirmado = confirmado;
            return this;
        }

        public Pagamento build() {
            Pagamento pagamento = new Pagamento();
            pagamento.setFormaPagamento(this.formaPagamento);
            pagamento.setConfirmado(this.confirmado);
            return pagamento;
        }
    }
}
