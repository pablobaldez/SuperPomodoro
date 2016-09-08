package pablobaldez.github.superpomodoro.presentation.utils;

import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class BackgroundTransformer<T> implements Observable.Transformer<T,T>{

    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single.Transformer<T, T> toSingle() {
        return single -> single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Completable.CompletableTransformer toCompletable() {
        return completable -> completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
