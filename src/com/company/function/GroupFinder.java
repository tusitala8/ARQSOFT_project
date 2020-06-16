package com.company.function;

import java.util.*;
import java.util.regex.Pattern;

class GroupFinder {
    public GroupFinder() {
        this.queue = new ArrayDeque<String>();
        this.functionPattern = Pattern.compile("[A-Z]+");
    }

    Deque<String> queue;
    Pattern functionPattern;

    public List<String> findGroup(String token) {
        List<String> result = this.push(token);
        if(result.isEmpty()) {
            return result;
        }

        Collections.reverse(result);

        result.add(0, "(");
        result.add(")");

        if(!this.queue.isEmpty() && this.isFunction(this.queue.getFirst())) {
            result.add(0, this.queue.pop());
        }

        return result;
    }

    boolean isFunction(String token) {
        if (token == null) {
            return false;
        }
        return this.functionPattern.matcher(token).matches();
    }

    List<String> push(String token) {
        List<String> result = new ArrayList<String>();

        if(this.queue.isEmpty()) {
            this.queue.push(token);
            return result;
        }

        String lastToken = this.queue.getFirst();

        if(token.equals(")") && lastToken.equals("(")) {
            this.queue.pop();
            return result;
        }

        if(token.equals(")")) {
            result.add(this.queue.pop());
            result.addAll(this.push(token));
            return result;
        }

        this.queue.push(token);
        return result;
    }

    public List<String> getAll() {
        List<String> result = new ArrayList<String>();

        while(!queue.isEmpty()) {
            result.add(queue.pop());
        }

        Collections.reverse(result);

        return result;
    }
}