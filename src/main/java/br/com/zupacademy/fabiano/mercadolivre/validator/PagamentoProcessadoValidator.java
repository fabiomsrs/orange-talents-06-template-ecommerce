package br.com.zupacademy.fabiano.mercadolivre.validator;

import br.com.zupacademy.fabiano.mercadolivre.dto.PayPalTentativaPagamentoDto;
import br.com.zupacademy.fabiano.mercadolivre.modelo.StatusTransacao;
import br.com.zupacademy.fabiano.mercadolivre.repository.TentativaPagamentoRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PagamentoProcessadoValidator implements ConstraintValidator<PagamentoProcessado, PayPalTentativaPagamentoDto> {

    private TentativaPagamentoRepository repository;

    public PagamentoProcessadoValidator(TentativaPagamentoRepository repository){
        this.repository = repository;
    }

    @Override
    public void initialize(PagamentoProcessado constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PayPalTentativaPagamentoDto value, ConstraintValidatorContext context) {
        return this.repository.findByTransacaoIdAndStatus(value.getTransacaoId(), StatusTransacao.SUCESSO)
        .isEmpty();
    }
}
