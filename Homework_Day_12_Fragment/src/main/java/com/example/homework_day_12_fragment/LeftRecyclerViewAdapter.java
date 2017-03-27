package com.example.homework_day_12_fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homework_day_12_fragment.Content.ContentItem;
import com.example.homework_day_12_fragment.Left_Fragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ContentItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class LeftRecyclerViewAdapter extends RecyclerView.Adapter<LeftRecyclerViewAdapter.ViewHolder> {

    private final List<ContentItem> items;
    private final OnListFragmentInteractionListener mListener;

    public LeftRecyclerViewAdapter(List<ContentItem> items, OnListFragmentInteractionListener listener) {
        this.items = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_left_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = items.get(position);
        holder.mIdView.setText(items.get(position).getId());
        holder.mContentView.setText(items.get(position).getContent());

        //final int p = position;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("onBindViewHolder", "click an item");
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.

                    Log.i("onBindViewHolder", "mListener not null");
                    mListener.onListFragmentInteraction(holder.mItem);
                } else {
                    Log.i("onBindViewHolder", "mListener is null");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public ContentItem mItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_id);
            mContentView = (TextView) view.findViewById(R.id.item_content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
