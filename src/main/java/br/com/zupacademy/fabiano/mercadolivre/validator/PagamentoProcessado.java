package br.com.zupacademy.fabiano.mercadolivre.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PagamentoProcessadoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PagamentoProcessado {
    String message() default "Pagamento ja foi processado com sucesso";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
