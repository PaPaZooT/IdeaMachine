package hr.matvidako.ideamachine.idea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hr.matvidako.ideamachine.R;

public class IdeaAdapter extends BaseAdapter {

    private List<Idea> ideas = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;

    public IdeaAdapter(Context context, List<Idea> ideas) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.ideas.addAll(ideas);
    }

    @Override
    public int getCount() {
        return ideas.size();
    }

    @Override
    public Idea getItem(int position) {
        return ideas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_idea, null, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.idea_title);
        title.setText(getItem(position).content);
        return convertView;
    }

    public List<Idea> getItems() {
        return ideas;
    }

    public void addIdea(String content) {
        int ideaCount = getCount();
        ideas.add(new Idea(ideaCount + 1, content));
        notifyDataSetChanged();
    }

}
