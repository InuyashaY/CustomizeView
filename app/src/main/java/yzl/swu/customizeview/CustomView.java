package yzl.swu.customizeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {
    //代码创建
    public CustomView(Context context) {
        super(context);
    }

    //xml创建  AttributeSet：xml中配置的属性
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //带样式
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
