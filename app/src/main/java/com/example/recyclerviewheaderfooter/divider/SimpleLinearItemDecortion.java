package com.example.recyclerviewheaderfooter.divider;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 简单的自定义分割线
 * 未定义方法更改分割线颜色
 */
public class SimpleLinearItemDecortion extends RecyclerView.ItemDecoration {

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int layoutOrientation = getOrientation(parent);

        if (layoutOrientation == LinearLayoutManager.VERTICAL) {
            /*outRect.left=0;
            outRect.top = 0;
            outRect.right=0;
            outRect.bottom=20;*/

            int postion=((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
            int childCount = parent.getAdapter().getItemCount();

            if (!isLastRaw(parent, postion, childCount)) {
                outRect.set(0, 0, 0, 20);
            }
        } else if(layoutOrientation == LinearLayoutManager.HORIZONTAL) {
            outRect.left = 5;
            outRect.top = 0;
            outRect.right=0;
            outRect.bottom=0;
        }
    }

    private int getOrientation(RecyclerView parent) {
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            return layoutManager.getOrientation();
        } else throw new IllegalStateException("DividerItemDecoration can only be used with a LinearLayoutManager.");
    }

    private boolean isLastRaw(RecyclerView parent, int pos,int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            /*
            * 第一行和倒数两行不绘制底部分割线，即与头布局、尾布局衔接的item无分割线
            * 若没有添加头、尾布局，则需要if条件为pos >= childCount-1
             */
            if (pos >= childCount-2 || pos==0)
                return true;
        }
        return false;
    }
}
