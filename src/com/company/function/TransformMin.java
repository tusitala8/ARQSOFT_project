package com.company.function;

import java.util.List;

class TransformMin implements TransformFunction {
    public String getIdentifier() {
        return "MIN";
    }

    public List<String> transform(List<String> tokens) {
        tokens.replaceAll( token -> {
            if(token.equals(";")) {
                return "min";
            }
            return token;
        });
        return tokens;
    }
}