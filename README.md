# RecyclerViewHeaderFooter
RecyclerView添加头、尾布局，设置分割线

内含三种分割线样式：<br/>
1、UniversalRecycleViewDivider更改分割线颜色无效，有bug。<br/>
2、DividerGridItemDecoration用于GridLayoutManager和StaggeredGridLayoutManager布局中的分割线，可更改分割线颜色。<br/>
3、SimpleLinearItemDecortion简单的自定义分割线，未定义方法更改分割线颜色。

```java
/**
 * 为RecyclerView添加Header和Footer：
 * RecyclerView的使用和ListView的使用差不多，无非就是那三步：
 * 第一，初始化RecyclerView；
 * 第二，初始化数据，并且将数据通过Adapter装在到View中；
 * 第三，通过setAdapter方法，将Adapter绑定到RecyclerView中。
 */
```

```java
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
```

![img](https://github.com/ykmeory/Android_RecyclerViewHeaderFooter/blob/master/S70717-005845_meitu_1.jpg "截图")
