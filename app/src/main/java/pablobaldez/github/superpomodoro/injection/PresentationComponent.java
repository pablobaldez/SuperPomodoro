package pablobaldez.github.superpomodoro.injection;

import dagger.Subcomponent;
import pablobaldez.github.superpomodoro.presentation.history.HistoryFragment;
import pablobaldez.github.superpomodoro.presentation.newpomodoro.NewPomodoroFragment;
import pablobaldez.github.superpomodoro.presentation.settings.SettingsActivity;

/**
 * @author Pablo
 * @since 07/09/2016
 */
@ActivityScope
@Subcomponent
public interface PresentationComponent {

    void inject(NewPomodoroFragment fragment);

    void inject(HistoryFragment fragment);

    void inject(SettingsActivity activity);

}
