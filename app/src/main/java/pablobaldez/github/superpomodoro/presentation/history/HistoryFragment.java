package pablobaldez.github.superpomodoro.presentation.history;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.R;
import pablobaldez.github.superpomodoro.domain.Pomodoro;
import pablobaldez.github.superpomodoro.presentation.widgets.DelegatedAdapter;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public class HistoryFragment extends Fragment
        implements HistoryMvpView, DelegatedAdapter.Delegate<RecyclerView.ViewHolder>{

    private static final int POMODORO_VIEW_TYPE = 0;
    private static final int DAY_VIEW_TYPE = 1;

    private int itemCount;
    private boolean loading;

    private HistoryPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView emptyTextView;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        emptyTextView = (TextView) view.findViewById(R.id.empty_text_view);
        emptyTextView.setText(R.string.message_empty_pomodoro);
        setupRecyclerView(view);
        setupSwipe(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadContent();
    }

    public void onSelect(){
        loadContent();
    }

    private void setupRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        adapter = new DelegatedAdapter<>(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(false);
    }

    private void setupSwipe(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.running);
        swipeRefreshLayout.setOnRefreshListener(this::loadContent);
    }

    private void loadContent(){
        if(isAdded() && !loading) {
            presenter.load();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        if(viewType == DAY_VIEW_TYPE) {
            return DayViewHolder.newInstance(inflater, parent);
        }
        else {
            return PomodoroViewHolder.newInstance(inflater, parent);
        }
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DayViewHolder) {
            Date day = presenter.day(position);
            ((DayViewHolder) holder).bind(day);

        }
        else if(holder instanceof PomodoroViewHolder){
            Pomodoro pomodoro = presenter.pomodoro(position);
            ((PomodoroViewHolder) holder).bind(pomodoro);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return presenter.isPomodoroRow(position) ? POMODORO_VIEW_TYPE : DAY_VIEW_TYPE;
    }

    @Override
    public void notifyDataLoaded(int count) {
        itemCount = count;
        if(itemCount == 0) {
            emptyTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else {
            emptyTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        loading = true;
        emptyTextView.setVisibility(View.GONE);
        if(!swipeRefreshLayout.isRefreshing()) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        loading = false;
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showDefaultError() {
        View view = getView();
        if(view != null) {
            Snackbar.make(view, R.string.default_error_message, Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    @Inject
    public void setPresenter(HistoryPresenter presenter) {
        this.presenter = presenter;
    }
}
