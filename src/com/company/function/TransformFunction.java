package com.company.function;

import java.util.List;

interface TransformFunction {
    String getIdentifier();
    List<String> transform(List<String> tokens);
}
