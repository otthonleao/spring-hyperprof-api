package dev.otthon.hyperprof.core.exceptions;

public class ProfessorNotFoundException extends ModelNotFoundException{

    public ProfessorNotFoundException() {
        super("ERROR: Professor não encontrado");
    }

    public ProfessorNotFoundException(String message) {
        super(message);
    }
}
