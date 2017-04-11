package ggx.com.libzilla.util;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * @author jerry.Guan
 *         created by 2017/4/9
 */

public abstract class BaseViewHolder<M extends ItemModel> extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private int viewType;
    public BaseViewHolder(View itemView) {
        super(itemView);
        views=new SparseArray<>();
    }
    public <T extends View>T getView(int resID) {
        View view = views.get(resID);

        if (view == null) {
            view = super.itemView.findViewById(resID);
            views.put(resID,view);
        }

        return (T) view;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public void setOnItemClickListener(final MutilRecycleAdapter.OnItemClickListener listener, final int position){
        super.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClick(position);
                }
            }
        });
    }
    public void setOnItemLongClickListener(final MutilRecycleAdapter.OnItemLongClickListener listener, final int position){
        if(!super.itemView.isLongClickable()){
            super.itemView.setLongClickable(true);
        }
        super.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(listener!=null){
                    listener.onItemLongClick(position);
                }
                return false;
            }
        });
    }

    public abstract void setLogic(BaseViewHolder holder,M model, int position,int itemType);
}
