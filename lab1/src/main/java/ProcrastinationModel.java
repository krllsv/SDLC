package lab1;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProcrastinationModel {
    private int age = 0;
    private long laterCount = 0;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setAge(int age) {
        int oldAge = this.age;
        long oldLaterCount = this.laterCount;

        this.age = age;
        // Усредненная статистика: считаем с 7 лет, в среднем 10 раз за день человек говорит "потом"
        int activeYears = Math.max(0, age - 7);
        this.laterCount = (long) activeYears * 365 * 10;

        support.firePropertyChange("age", oldAge, this.age);
        support.firePropertyChange("laterCount", oldLaterCount, this.laterCount);
    }

    public int getAge() { return age; }
    public long getLaterCount() { return laterCount; }
}