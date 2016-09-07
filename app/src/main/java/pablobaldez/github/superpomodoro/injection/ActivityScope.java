package pablobaldez.github.superpomodoro.injection;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Pablo
 * @since 07/09/2016
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {
}
