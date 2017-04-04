package part2.pattern.command;

/**
 * Created by Polylanger on 2017/4/4.
 */
public interface Command {
    void execute();

    void undo();
}
