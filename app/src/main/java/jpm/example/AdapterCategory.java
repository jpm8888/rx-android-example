package jpm.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.MyViewHolder> {

    List<ModelCategory.Datum> modelCategoryList;

    public AdapterCategory(List<ModelCategory.Datum> modelCategoryList) {
        this.modelCategoryList = modelCategoryList;
    }

    @Override
    public AdapterCategory.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(modelCategoryList.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return modelCategoryList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public MyViewHolder(TextView v) {
            super(v);
            mTextView = v.findViewById(R.id.category_title);
        }
    }

}
