package hr.matvidako.ideamachine.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hr.matvidako.ideamachine.R;

public class TagLayout extends LinearLayout {

    LayoutInflater layoutInflater;
    List<String> tags = new ArrayList<>();
    int tagLayoutResId;
    int itemMargin;
    private int availableWidth;

    LinearLayout.LayoutParams rowLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public TagLayout(Context context) {
        this(context, null, 0);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(VERTICAL);
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void loadTags(List<String> tags, int tagLayoutResId, int itemMargin) {
        this.tags = tags;
        this.tagLayoutResId = tagLayoutResId;
        this.itemMargin = itemMargin;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(availableWidth > 0) return;
        availableWidth = right - left - getPaddingLeft() - getPaddingRight();
        setupTagViews();
    }

    private void setupTagViews() {
        int totalRowWidth = 0;

        LinearLayout row = addNewRow(rowLayoutParams);
        for(String tag : tags) {
            TextView tagView = (TextView) layoutInflater.inflate(tagLayoutResId, row, false);
            tagView.setText(tag);
            tagView.measure(0, 0);
            if(totalRowWidth + tagView.getMeasuredWidth() >= availableWidth) {
                int remainingWidth = availableWidth - totalRowWidth;
                updateTagViewWidths(row, remainingWidth);
                row = addNewRow(rowLayoutParams);
                totalRowWidth = 0;
            } else {
                totalRowWidth += tagView.getMeasuredWidth();
            }
            row.addView(tagView);
        }
    }

    private void updateTagViewWidths(LinearLayout row, int remainingWidth) {
        int childCount = row.getChildCount();
        int widthIncrement = remainingWidth / childCount;
        for(int i = 0; i < childCount; i++)  {
            View child = row.getChildAt(i);
            ViewGroup.LayoutParams lp = child.getLayoutParams();
            lp.width = widthIncrement + child.getMeasuredWidth();
        }
    }

    protected LinearLayout addNewRow(LayoutParams rowLayoutParams) {
        LinearLayout row = new LinearLayout(getContext());
        row.setLayoutParams(rowLayoutParams);
        this.addView(row);
        return row;
    }

}
