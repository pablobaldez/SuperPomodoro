package pablobaldez.github.superpomodoro.injection;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Pablo
 * @since 07/09/2016
 */
@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    PresentationComponent plus();

}
