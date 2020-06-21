package com.company.function;

import java.util.List;

public class TransformSum implements TransformFunction {
    public String getIdentifier() {
        return "SUM";
    }

    public List<String> transform(List<String> tokens) {
        tokens.add(0,"(");
        tokens.replaceAll( token -> {
            if(token.equals(";")) {
                return ")+(";
            }
            return token;
        });
        tokens.add(")");
        return tokens;
    }
}