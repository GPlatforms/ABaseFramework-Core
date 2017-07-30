package android.baseframework.core.widget;

import android.baseframework.core.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jayfeng.lesscode.core.ViewLess;

public class BCErrorView extends RelativeLayout {

    public Button reLoadView;

    public BCErrorView(Context context) {
        super(context);
        init(null, 0);
    }

    public BCErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BCErrorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        LayoutInflater.from(getContext()).inflate(R.layout.bc_view_error, this, true);
        reLoadView = ViewLess.$(this, R.id.btn_network_reload);
    }

}
