package br.com.zupacademy.fabiano.mercadolivre.validator;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChecaExistenciaValidator implements ConstraintValidator<ChecaExistencia, Object> {
    private EntityManager em;

    private Class<?> object;
    private String identityField;

    public ChecaExistenciaValidator(EntityManager em) {
        this.em = em;
    }

    @Override
    public void initialize(ChecaExistencia constraintAnnotation) {
        this.object = constraintAnnotation.instanceClass();
        this.identityField = constraintAnnotation.identityField();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null){
            return true;
        }
        return !this.em.createQuery("select 1 from "+this.object.getName()+" where "+ this.identityField + "=:field")
                .setParameter("field",value)
                .getResultList()
                .isEmpty();
    }
}
