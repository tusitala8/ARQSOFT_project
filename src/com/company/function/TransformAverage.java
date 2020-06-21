package com.company.function;

import java.util.Collections;
import java.util.List;

class TransformAverage implements TransformFunction {
    public String getIdentifier() {
        return "AVERAGE";
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
        tokens.add("/");
        tokens.add(String.valueOf(Collections.frequency(tokens, ")+(")+1));
        return tokens;
    }
}