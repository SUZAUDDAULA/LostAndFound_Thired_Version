package com.opus_bd.lostandfound.Adapter.Extra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.opus_bd.lostandfound.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DocumentIDListAdapter extends RecyclerView.Adapter<DocumentIDListAdapter.TransactionViewHolder> {
    private final Context context;
    private List<String> items;

    public DocumentIDListAdapter(List<String> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_speech_list, parent, false);
        return new TransactionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        String item = items.get(position);
        holder.set(item);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvString)
        TextView tvString;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void set(final String item) {
            //UI setting code

            tvString.setText(item);


        }


    }


}
