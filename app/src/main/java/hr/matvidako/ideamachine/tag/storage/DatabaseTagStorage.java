package hr.matvidako.ideamachine.tag.storage;

import android.content.Context;

import java.util.List;

import hr.matvidako.ideamachine.db.Repository;
import hr.matvidako.ideamachine.tag.Tag;

public class DatabaseTagStorage extends Repository<Tag> implements TagStorage {

    public DatabaseTagStorage(Context ctx) {
        super(ctx, Tag.class);
    }

}
