package face;

/**
 * Created by Yc on 2016/4/9 for students_system.
 */
public abstract class AcessAdapter implements canDelete,canInsert,canSelect{
    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean insert() {
        return false;
    }

    @Override
    public boolean select() {
        return false;
    }
}
