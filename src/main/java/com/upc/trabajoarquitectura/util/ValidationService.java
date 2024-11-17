package com.upc.trabajoarquitectura.util;

import com.upc.trabajoarquitectura.exceptions.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public void verifyNoNulls(Object... campos) {
        for (Object campo : campos) {
            if (campo == null) {
                throw new RequestException("E-001", HttpStatus.CONFLICT, "No se permiten campos nulos.");
            }
        }
    }

    public void verifyNoEmpty(String... campos) {
        for (String campo : campos) {
            if (campo.trim().isEmpty()) {
                throw new RequestException("E-002", HttpStatus.BAD_REQUEST, "No se permiten campos vacíos o con espacios invisibles.");
            }
        }
    }
    public void verifyNoEmptyWithInvisibleSpaces(String... campos) {
        for (String campo : campos) {
            if (campo.isEmpty()) {
                throw new RequestException("E-002", HttpStatus.BAD_REQUEST, "No se permiten campos vacíos.");
            }
        }
    }

    public void verifyOnlyLetters(String... campos) {
        String pattern = "[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+";
        for (String campo : campos) {
            if (!campo.matches(pattern)) {
                throw new RequestException("E-003", HttpStatus.BAD_REQUEST, "Solo se permiten letras.");
            }
        }
    }

    public void verifyLength(String campo, int min, int max) {
        if (campo.length() < min || campo.length() > max) {
            throw new RequestException("E-004", HttpStatus.BAD_REQUEST, "El tamaño de la cadena de texto debe ser mayor a " + min + " y menor a " + max + ".");
        }
    }

    public void verifyExistsID(boolean exists, String entityType) {
        if (!exists) {
            throw new RequestException("E-006", HttpStatus.NOT_FOUND, "La " + entityType + " no existe en la base de datos.");
        }
    }

    public void checkForDuplicate(boolean exists, String entityType) {
        if (exists) {
            throw new RequestException("E-007", HttpStatus.CONFLICT,
                    "El nombre de " + entityType + " ya existe en la base de datos");
        }
    }

    public void verifyRepeatedAssign(boolean exists) {
        if (exists) {
            throw new RequestException("E-008", HttpStatus.CONFLICT, "Esta asignación ya existe en la base de datos.");
        }
    }

}
