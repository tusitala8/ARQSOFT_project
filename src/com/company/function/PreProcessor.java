package com.company.function;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class PreProcessor {

    String baseTokens;
    Pattern tokenPattern;
    List<TransformFunction> loadFunctions;

    public PreProcessor() {
        this.loadFunctions = new ArrayList<TransformFunction>();
        this.loadFunctions.add(new TransformSum());
        this.loadFunctions.add(new TransformMax());
        this.loadFunctions.add(new TransformMin());
        this.loadFunctions.add(new TransformAverage());
        this.baseTokens = "\\d+(\\.\\d+)?|\\+|\\*|/|\\(|\\)|-|;";
        this.tokenPattern = Pattern.compile(this.getTokensRegex());
    }

    //Llama al método process. Recibe una fórmula en forma de string y
    // devuelve una expresión
    // "tokenized" más fácil de procesar

    public String toOperations(String formula) {
        return this.process(this.tokenize(formula));
    }

    //Te devuelve un string de la lista regex delimitado por |
    // SUM|MAX|MIN|AVERAGE|\d+(\.\d+)?|\+|\*|/|\(|\)|-|;
    public String getTokensRegex() {
        List<String> regex = this.loadFunctions.stream()
                .map(TransformFunction::getIdentifier)
                .collect(toList());
       // System.out.println(regex);
        regex.add(this.baseTokens);

        return String.join("|",regex);
    }
        //Primero entender GroupFinder
            String process(List<String> tokens) {
        GroupFinder finder = new GroupFinder();

        for(String token: tokens) {
            List<String> tokenGroup = finder.findGroup(token);
            if(!tokenGroup.isEmpty()) {
                for(String resultToken: this.transform(tokenGroup)) {
                    finder.findGroup(resultToken);
                }
            }
        }

        return String.join("", finder.getAll()).replace('[', '(').replace(']', ')');
    }

    List<String> transform(List<String> tokens) {
        List<String> result = new ArrayList<String>();
        if(tokens.size()<2) {
            return result;
        }
        String operation = tokens.get(0);

        //preguntar
        TransformFunction function = this.loadFunctions.stream()
                .filter(func -> func.getIdentifier().equals(operation))
                .findAny()
                .orElse(null);
        if(function == null) {
            return this.mapBrackets(tokens);
        } else {
            tokens.remove(0);
            return this.mapBrackets(function.transform(tokens));
        }
    }

    List<String> mapBrackets(List<String> tokens) {
        tokens.replaceAll( token -> {
            if(token.equals("(")) {
                return "[";
            }
            if(token.equals(")")) {
                return "]";
            }
            return token;
        });
        return tokens;
    }

    List<String> tokenize(String formula) {
        List<String> result = new ArrayList<String>();

        Matcher m = this.tokenPattern.matcher(formula);

        while(m.find()) {
         String hola=m.group(0);
            result.add(m.group(0));
        }
        return result;
    }
}
