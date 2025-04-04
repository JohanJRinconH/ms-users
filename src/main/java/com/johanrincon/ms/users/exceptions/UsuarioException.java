package com.johanrincon.ms.users.exceptions;

import com.johanrincon.ms.users.enums.InternalCodes;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

import java.io.Serial;

/**
* Exceptions control class
*/
@RequiredArgsConstructor
public class UsuarioException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3387516993124229948L;

    private final InternalCodes internalCodes;

    public UsuarioException(final String message, final InternalCodes internalCodes) {
        super(message);
        this.internalCodes = internalCodes;
    }

    public UsuarioException(final String message, final Throwable cause, final InternalCodes internalCodes) {
        super(message,cause);
        this.internalCodes = internalCodes;
    }

    @Generated
    public InternalCodes getInternalCodes(){return this.internalCodes;}


}
