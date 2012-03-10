package software.craftsmanship.debruijn.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Combination<T> {
    public static <U> Combination<U> combination(U... elements) {
        return new Combination<U>(Arrays.asList(elements));
    }

    public static <U> Combination<U> empty(Class<? extends U> aClass) {
        return new Combination<U>(new ArrayList<U>());
    }

    public static <U> Combination<U> empty() {
        return new Combination<U>(new ArrayList<U>());
    }

    private final List<T> elements;

    private Combination(List<T> elements) {
        this.elements = Collections.unmodifiableList(copyOf(elements));
    }

    private List<T> copyOf(List<T> elements) {
        List<T> copy = new ArrayList<T>();
        copy.addAll(elements);
        return copy;
    }

    public Combination<T> append(T letter) {
        List<T> copy = copyOf(elements);
        copy.add(letter);
        return new Combination<T>(copy);
    }

    public Combination<T> pipe(T letter) {
        List<T> copy = copyOf(elements);
        copy.remove(0);
        copy.add(letter);
        return new Combination<T>(copy);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((elements == null) ? 0 : elements.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        @SuppressWarnings("rawtypes")
        Combination other = (Combination) obj;
        if (elements == null) {
            if (other.elements != null) return false;
        } else if (!elements.equals(other.elements)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ");
        for (T element : elements) {
            builder.append(element);
            builder.append(" ");
        }
        builder.delete(builder.length() - 1, builder.length());
        builder.append(" ]");
        return builder.toString();
    }
}
