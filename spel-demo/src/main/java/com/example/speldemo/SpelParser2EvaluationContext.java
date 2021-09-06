package com.example.speldemo;

import com.example.speldemo.model.User;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.time.LocalDate;
import java.util.TimeZone;

public class SpelParser2EvaluationContext {

    public static void main(String[] args) {

        SpelExpressionParser parser = new SpelExpressionParser();

        {
            StandardEvaluationContext ec = new StandardEvaluationContext();
            ec.setVariable("greeting", "Hello Aivars");
            var val = parser.parseExpression("#greeting.substring(6)").getValue(ec, String.class);
            System.out.println("val -->" + val);
        }

        {
            User user = new User();
            System.out.println("user start -->" + user);
            StandardEvaluationContext userContext = new StandardEvaluationContext(user);

            //change by referencing context
            parser.parseExpression("country").setValue(userContext, "USA");
            parser.parseExpression("dob").setValue(userContext, LocalDate.of(2000,1,1));

            //change by referencing root object
            parser.parseExpression("defaultTimezone").setValue(user, TimeZone.getTimeZone("UTC"));

            //get system properties
            StandardEvaluationContext sysProps = new StandardEvaluationContext();
            sysProps.setVariable("systemProperties", System.getProperties());
            Expression extractOs = parser.parseExpression("#systemProperties['os.name']");
            parser.parseExpression("os").setValue(userContext, extractOs.getValue(sysProps));


            System.out.println("user finish -->" + user);
        }
    }
}
