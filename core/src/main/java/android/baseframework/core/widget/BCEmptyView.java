package android.baseframework.core.widget;

import android.baseframework.core.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jayfeng.lesscode.core.ViewLess;

import org.jetbrains.annotations.NotNull;

public class BCEmptyView extends RelativeLayout {

    private TextView textView;
    private ImageView iconView;

    public BCEmptyView(Context context) {
        super(context);
        init(null, 0);
    }

    public BCEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BCEmptyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        LayoutInflater.from(getContext()).inflate(R.layout.bc_view_empty, this, true);
        textView = ViewLess.$(this, R.id.empty_text);
        iconView = ViewLess.$(this, R.id.empty_icon);
    }

    public void setEmptyText(@NotNull String text) {
        textView.setText(text);
    }

    public void setEmptyText(int textResId) {
        textView.setText(textResId);
    }

    public void setEmptyIcon(int iconResId) {
        iconView.setImageResource(iconResId);
    }
}
