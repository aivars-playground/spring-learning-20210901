package com.example.speldemo;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

public class SpelParser1Basic {

    public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();

        {
            //string expression
            Expression expr = parser.parseExpression("'Hello World!'");
            var val = expr.getValue(String.class);
            System.out.println("val -->" + val);
        }

        {
            //method call
            Expression expr = parser.parseExpression("'Hello World!'.length()");
            var val = expr.getValue(Integer.class);
            System.out.println("val -->" + val);
        }

        {
            //arithmetics operation
            Expression expr = parser.parseExpression("'Hello World!'.length() * 3");
            var val = expr.getValue(Integer.class);
            System.out.println("val -->" + val);
        }

        {
            //relational operation
            Expression expr = parser.parseExpression("'Hello World!'.length() > 0");
            var val = expr.getValue(Boolean.class);
            System.out.println("val -->" + val);
        }

        {
            //logical operation
            Expression expr = parser.parseExpression("'Hello World!'.length() > 0 and 'Hello World!'.length() < 256 ");
            var val = expr.getValue(Boolean.class);
            System.out.println("val -->" + val);
        }

    }
}
