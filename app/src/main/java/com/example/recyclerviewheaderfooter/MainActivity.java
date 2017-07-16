package com.example.recyclerviewheaderfooter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.recyclerviewheaderfooter.divider.SimpleLinearItemDecortion;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView分割线、添加头尾布局
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mMyAdapter;//为RecyclerView添加头布局、尾布局

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView= (RecyclerView) this.findViewById(R.id.recycler_view);
        mMyAdapter = new MyRecyclerAdapter(getList());
        mRecyclerView.setAdapter(mMyAdapter);

        //mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        //mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));//分割线高度、颜色都生效


        //一定要设置LayoutManager，否则RecyclerView不知道是什么方向，显示不了数据
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SimpleLinearItemDecortion());

        /*
         * 使用UniversalRecycleViewDivider时，分割线高度可以更改，但颜色无法更改
         * 可通过设置RecyclerView背景改变颜色
         */
        //一、添加默认分割线：高度为2px，颜色为灰色
        //mRecyclerView.addItemDecoration(new UniversalRecycleViewDivider(this, LinearLayoutManager.VERTICAL));

        //二、添加自定义分割线：可自定义分割线drawable
        //mRecyclerView.addItemDecoration(new UniversalRecycleViewDivider(this, LinearLayoutManager.VERTICAL, R.drawable.shape_divider_mileage));

        //三、添加自定义分割线：可自定义分割线高度和颜色
        /*mRecyclerView.addItemDecoration(new UniversalRecycleViewDivider(
                this,
                LinearLayoutManager.VERTICAL,
                10,
                ContextCompat.getColor(this,R.color.divide_gray_color)
        ));*/


        //为RecyclerView添加HeaderView和FooterView
        setHeaderView(mRecyclerView);
        setFooterView(mRecyclerView);
    }

    public List<String> getList(){
        List<String> list=new ArrayList<>();
        for (int i=1;i<9;i++){
            list.add(i+"");
        }
        return list;
    }

    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(this).inflate(R.layout.header_view, view, false);
        mMyAdapter.setHeaderView(header);
    }

    private void setFooterView(RecyclerView view){
        View footer = LayoutInflater.from(this).inflate(R.layout.footer_view, view, false);
        mMyAdapter.setFooterView(footer);
    }
}

