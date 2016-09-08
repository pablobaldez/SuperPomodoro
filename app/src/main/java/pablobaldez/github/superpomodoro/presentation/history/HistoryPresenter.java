package pablobaldez.github.superpomodoro.presentation.history;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.domain.Pomodoro;
import pablobaldez.github.superpomodoro.domain.workers.DataSource;
import pablobaldez.github.superpomodoro.presentation.utils.BackgroundTransformer;
import pablobaldez.github.superpomodoro.presentation.utils.TimeFormatUtils;
import rx.observers.Subscribers;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class HistoryPresenter {

    private final DataSource<Pomodoro> dataSource;

    private List<PomodoroGroup> groups = new ArrayList<>();
    private Map<Integer, Date> days = new LinkedHashMap<>();
    private Map<Integer, Pomodoro> pomodoros = new LinkedHashMap<>();

    private HistoryMvpView view;

    @Inject
    public HistoryPresenter(DataSource<Pomodoro> dataSource) {
        this.dataSource = dataSource;
    }

    public void attachView(HistoryMvpView view) {
        this.view = view;
    }

    public Pomodoro pomodoro(int position) {
        return pomodoros.get(position);
    }

    public Date day(int position) {
        return days.get(position);
    }

    public boolean isPomodoroRow(int position) {
        return pomodoros.containsKey(position);
    }

    public void load() {
        onStart();
        dataSource.findAll()
                .toList()
                .map(this::groupPomodoros)
                .compose(new BackgroundTransformer<>())
                .subscribe(Subscribers.create(
                        groups -> {},
                        this::onError,
                        this::onCompleted
                ));
    }

    private void onStart() {
        groups.clear();
        pomodoros.clear();
        days.clear();
        view.showLoading();
    }

    private void onError(Throwable throwable) {
        view.hideLoading();
        view.showDefaultError();
    }

    private void onCompleted() {
        view.hideLoading();
        view.notifyDataLoaded(calcListSize());
    }

    private List<PomodoroGroup> groupPomodoros(List<Pomodoro> list) {
        PomodoroGroup previousGroup = null;
        int index = 0;
        for(Pomodoro pomodoro: list) {
            Date took = pomodoro.getTook();
            if(previousGroup == null || !TimeFormatUtils.isSameDay(took, previousGroup.getDay())) {
                previousGroup = new PomodoroGroup(pomodoro);
                days.put(index, pomodoro.getTook());
                groups.add(previousGroup);
                index++;
                pomodoros.put(index, pomodoro);
            }
            else {
                pomodoros.put(index, pomodoro);
                previousGroup.add(pomodoro);
            }
            index++;
        }
        return groups;
    }

    private int calcListSize() {
        int size = 0;
        for(PomodoroGroup group: groups) {
            size += group.size();
        }
        return size;
    }

}
