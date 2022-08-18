package csproblem.injava.chapter8.ttt;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface UnitTest {
    String name() default "";
}
