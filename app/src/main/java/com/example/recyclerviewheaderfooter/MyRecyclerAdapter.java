package com.example.recyclerviewheaderfooter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 为RecyclerView添加Header和Footer：
 * RecyclerView的使用和ListView的使用差不多，无非就是那三步：
 * 第一，初始化RecyclerView；
 * 第二，初始化数据，并且将数据通过Adapter装在到View中；
 * 第三，通过setAdapter方法，将Adapter绑定到RecyclerView中。
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private static final int TYPE_HEADER = 0;  //说明是带有Header的
    private static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    private static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    //获取从Activity中传递过来每个item的数据集合
    private List<String> mDatas;

    //HeaderView, FooterView
    private View mHeaderView;
    private View mFooterView;

    //构造函数
    public MyRecyclerAdapter(List<String> list){
        this.mDatas = list;
    }

    /*
     * HeaderView的get和set函数
     */
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    /*
     * FooterView的get和set函数
     */
    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount()-1);
    }

    /*
     * 重写这个方法很重要，是加入Header和Footer的关键
     * 我们通过判断item的类型，从而绑定不同的view
     */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null){
            return TYPE_NORMAL;
        }

        if (position == 0){
            //第一个item应该加载Header
            return TYPE_HEADER;
        }

        if (position == getItemCount()-1){
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }

        return TYPE_NORMAL;
    }

    //创建View，如果是HeaderView或者是FooterView，直接在Holder中返回
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(mHeaderView);
        }

        if(mFooterView != null && viewType == TYPE_FOOTER){
            return new MyViewHolder(mFooterView);
        }

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(layout);
    }

    //绑定View，这里是根据返回的这个position的类型，从而进行绑定的，
    // HeaderView和FooterView, 就不同绑定了
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_NORMAL){
            if(holder != null) {
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                holder.tvOne.setText(mDatas.get(position-1));

                if (position%2 != 1){
                    holder.tvThree.setText("改名了");
                    holder.tvThree.setTextColor(Color.BLUE);
                }
            }
        }else if(getItemViewType(position) == TYPE_HEADER){
            Log.d("tag","请设置条件");
        }else{
            Log.d("tag","请设置默认条件");
        }
    }

    //在这里面加载ListView中的每个item的布局
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvOne;
        private TextView tvTwo;
        private TextView tvThree;
        private TextView tvFour;

        MyViewHolder(View itemView) {
            super(itemView);

            //如果是headerview或者是footerview,直接返回
            if (itemView == mHeaderView){
                return;
            }
            if (itemView == mFooterView){
                return;
            }

            tvOne = (TextView)itemView.findViewById(R.id.tv_one);
            tvThree= (TextView) itemView.findViewById(R.id.tv_three);
        }
    }

    //返回View中Item的个数，这个时候，总的个数应该是ListView中Item的个数加上HeaderView和FooterView
    @Override
    public int getItemCount() {
        if(mHeaderView == null && mFooterView == null){
            return mDatas!=null ? mDatas.size():0;
        }else if(mHeaderView == null && mFooterView != null){
            return mDatas.size() + 1;
        }else if (mHeaderView != null && mFooterView == null){
            return mDatas.size() + 1;
        }else {
            return mDatas.size() + 2;
        }
    }

}