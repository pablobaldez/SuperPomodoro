package pablobaldez.github.superpomodoro.presentation.widgets;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class DelegatedAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    private final Delegate<VH> delegate;

    public DelegatedAdapter(Delegate<VH> delegate) {
        this.delegate = delegate;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegate.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        delegate.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return delegate.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return delegate.getItemViewType(position);
    }

    public interface Delegate<VH> {
        VH onCreateViewHolder(ViewGroup parent, int viewType);

        int getItemCount();

        void onBindViewHolder(VH holder, int position);

        int getItemViewType(int position);
    }
}
