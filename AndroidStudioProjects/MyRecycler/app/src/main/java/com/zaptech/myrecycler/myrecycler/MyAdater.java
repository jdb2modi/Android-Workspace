package com.zaptech.myrecycler.myrecycler;


        import java.util.ArrayList;



        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.widget.TextView;

public class MyAdater extends RecyclerView.Adapter<MyAdater.ViewHolder> {

    private ArrayList<String> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView header;

        public TextView footer;

        public ViewHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.firstLine);
            footer = (TextView) itemView.findViewById(R.id.secondLine);
        }

    }

    public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdater(ArrayList<String> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String name = mDataset.get(position);
        holder.header.setText(mDataset.get(position));
        holder.header.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                remove(name);
            }
        });
        holder.footer.setText("Footer:: " + mDataset.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

}