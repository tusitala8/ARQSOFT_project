package com.company.function;

import java.util.List;

class TransformMax implements TransformFunction {
    public String getIdentifier() {
        return "MAX";
    }

    public List<String> transform(List<String> tokens) {
        tokens.replaceAll( token -> {
            if(token.equals(";")) {
                return "max";
            }
            return token;
        });
        return tokens;
    }
}