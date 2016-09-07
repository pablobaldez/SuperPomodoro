package pablobaldez.github.superpomodoro.data;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.Sort;
import pablobaldez.github.superpomodoro.domain.Pomodoro;
import pablobaldez.github.superpomodoro.domain.workers.DataSource;
import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.exceptions.Exceptions;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class RealmPomodoroDataSource implements DataSource<Pomodoro> {

    @Inject
    public RealmPomodoroDataSource(){}

    @Override
    public Observable<Pomodoro> findAll() {
        return Observable.defer(() -> Observable.from(findAllFunc()));
    }

    private List<Pomodoro> findAllFunc() {
        try {
            Realm realm = Realm.getDefaultInstance();
            List<Pomodoro> list = realm.where(Pomodoro.class)
                    .findAll().sort(Pomodoro.TOOK, Sort.DESCENDING);
            return realm.copyFromRealm(list);
        } catch (Exception e) {
            throw Exceptions.propagate(e);
        }
    }

    @Override
    public Completable save(Single<Pomodoro> pomodoroSingle) {
        return pomodoroSingle.flatMapCompletable(pomodoro ->
                Completable.fromAction(() -> saveAction(pomodoro))
        );
    }

    private void saveAction(Pomodoro pomodoro) {
        Realm realm = Realm.getDefaultInstance();
        try {
            long pk = generatePK(realm);
            realm.beginTransaction();
            pomodoro.setPrimaryKey(pk);
            realm.copyToRealm(pomodoro);
            realm.commitTransaction();
            Log.d("pablo", "saving");
        } catch (Exception e) {
            realm.cancelTransaction();
            throw Exceptions.propagate(e);
        }
    }

    private long generatePK(Realm realm) {
        Number pk = realm.where(Pomodoro.class).max(Pomodoro.PRIMARY_KEY);
        if (pk == null) {
            return 1;
        } else {
            return pk.longValue() + 1;
        }
    }

}
