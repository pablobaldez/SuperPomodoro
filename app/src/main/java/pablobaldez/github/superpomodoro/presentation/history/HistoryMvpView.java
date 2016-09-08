package pablobaldez.github.superpomodoro.presentation.history;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public interface HistoryMvpView {

    void notifyDataLoaded(int count);

    void showLoading();

    void hideLoading();

    void showDefaultError();



}
