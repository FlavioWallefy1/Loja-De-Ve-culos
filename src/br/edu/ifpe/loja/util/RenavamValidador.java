package br.edu.ifpe.loja.util;

import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.RenavamValidator;

public class RenavamValidador implements IValidator {

    private RenavamValidator renavamValidator;

    public RenavamValidador() {
        this.renavamValidator = new RenavamValidator();
    }

    @Override
    public boolean validar(String renavam) {
        try {
            renavamValidator.assertValid(renavam); 
            return true; 
        } catch (InvalidStateException e) {
            
            return false;
        }
    }
}
