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
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;

public class IdeaAdapter extends BaseAdapter {

    private List<Idea> ideas = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private IdeaStorage ideaStorage;

    public IdeaAdapter(Context context, IdeaStorage ideaStorage) {
        this.ideaStorage = ideaStorage;
        layoutInflater = LayoutInflater.from(context);
        this.ideas.addAll(ideaStorage.loadAll());
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
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_idea, null, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.idea_title);
        title.setText(getItem(position).getContent());
        return convertView;
    }

    public void addIdea(Idea idea) {
        ideaStorage.store(idea);
        ideas.add(0, idea);
        notifyDataSetChanged();
    }

    public void deleteIdea(Idea idea) {
        ideaStorage.remove(idea);
        ideas.remove(idea);
        notifyDataSetChanged();
    }
}
