package utils;

public class Pair<T> {

    private final T firstElement;
    private final T secondElement;

    public Pair(final T firstElement, final T secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    public T getFirstElement() {
        return firstElement;
    }

    public T getSecondElement() {
        return secondElement;
    }
}
