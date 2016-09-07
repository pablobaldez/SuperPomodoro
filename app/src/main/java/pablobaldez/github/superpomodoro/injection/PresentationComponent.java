package pablobaldez.github.superpomodoro.injection;

import dagger.Component;
import dagger.Subcomponent;
import pablobaldez.github.superpomodoro.presentation.newpomodoro.NewPomodoroFragment;

/**
 * @author Pablo
 * @since 07/09/2016
 */
@ActivityScope
@Subcomponent
public interface PresentationComponent {

    void inject(NewPomodoroFragment fragment);

}
