package com.etg.usersservice.exceptions.errors;

import net.bytebuddy.implementation.bind.annotation.Super;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String msg){
        super(msg);
    }
}
