package software.craftsmanship.debruijn.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Word<T> {
    public static <U> Word<U> word(U... letters) {
        return new Word<U>(Arrays.asList(letters));
    }

    public static <U> Word<U> empty(Class<? extends U> aClass) {
        return new Word<U>(new ArrayList<U>());
    }

    public static <U> Word<U> empty() {
        return new Word<U>(new ArrayList<U>());
    }

    private final List<T> letters;

    private Word(List<T> letters) {
        this.letters = Collections.unmodifiableList(copyOf(letters));
    }

    private List<T> copyOf(List<T> letters) {
        List<T> copy = new ArrayList<T>();
        copy.addAll(letters);
        return copy;
    }

    public Word<T> append(T letter) {
        List<T> copy = copyOf(letters);
        copy.add(letter);
        return new Word<T>(copy);
    }

    public Word<T> pipe(T letter) {
        List<T> copy = copyOf(letters);
        copy.remove(0);
        copy.add(letter);
        return new Word<T>(copy);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((letters == null) ? 0 : letters.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        @SuppressWarnings("rawtypes")
        Word other = (Word) obj;
        if (letters == null) {
            if (other.letters != null) return false;
        } else if (!letters.equals(other.letters)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ");
        for (T letter : letters) {
            builder.append(letter);
            builder.append(" ");
        }
        builder.delete(builder.length() - 1, builder.length());
        builder.append(" ]");
        return builder.toString();
    }
}
