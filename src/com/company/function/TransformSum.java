package com.company.function;

import java.util.List;

public class TransformSum implements TransformFunction {
    public String getIdentifier() {
        return "SUM";
    }

    public List<String> transform(List<String> tokens) {
        tokens.replaceAll( token -> {
            if(token.equals(";")) {
                return "+";
            }
            return token;
        });
        return tokens;
    }
}