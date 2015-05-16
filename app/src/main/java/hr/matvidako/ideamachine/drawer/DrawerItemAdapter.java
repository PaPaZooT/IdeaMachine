package hr.matvidako.ideamachine.drawer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.ButterKnife;
import hr.matvidako.ideamachine.R;

public class DrawerItemAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;

    public DrawerItemAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return DrawerMenuItem.getCount();
    }

    @Override
    public DrawerMenuItem getItem(int pos) {
        return DrawerMenuItem.getByPosition(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        DrawerMenuItem currentItem = getItem(pos);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_drawer, null);
        }
        TextView title = ButterKnife.findById(convertView, R.id.title);
        title.setText(context.getString(currentItem.title));
        if(currentItem.drawable != 0) {
            title.setCompoundDrawablesWithIntrinsicBounds(currentItem.drawable, 0, 0, 0);
        }
        return convertView;
    }

}
