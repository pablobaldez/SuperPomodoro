package pablobaldez.github.superpomodoro.domain.workers;

import java.util.List;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public interface DataSource<T> {

    List<T> findAll();

    void save(T pomodoro);

}
