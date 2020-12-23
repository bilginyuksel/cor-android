package com.cor.android.framework.basef.handler;

public class CorException extends RuntimeException {
    private final CorError corError;

    public CorException(CorError corError) {
        super();
        this.corError = corError;
    }

    public CorError getCorError() {
        return corError;
    }
}
