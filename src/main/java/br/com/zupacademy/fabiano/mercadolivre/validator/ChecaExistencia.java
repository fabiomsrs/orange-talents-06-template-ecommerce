package br.com.zupacademy.fabiano.mercadolivre.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ChecaExistenciaValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ChecaExistencia {
    String message() default "Instancia não encontrada";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String identityField();
    Class instanceClass();
}

