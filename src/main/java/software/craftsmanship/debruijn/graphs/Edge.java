package software.craftsmanship.debruijn.graphs;

import software.craftsmanship.debruijn.combinatorics.Word;

public class Edge<T> {
    private final Word<T> source;
    private final Word<T> sink;
    private T label;

    public Edge(Word<T> source, Word<T> sink) {
        this.source = source;
        this.sink = sink;
    }

    public void label(T label) {
        this.label = label;
    }

    public T label() {
        return label;
    }

    public Word<T> source() {
        return source;
    }

    public Word<T> sink() {
        return sink;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sink == null) ? 0 : sink.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
	    return true;
	}
        if (obj == null) {
	    return false;
	}
        if (getClass() != obj.getClass()) {
	    return false;
	}
        @SuppressWarnings("rawtypes")
        Edge other = (Edge) obj;
        if (sink == null) {
            if (other.sink != null) {
		return false;
	    }
        } else if (!sink.equals(other.sink)) {
	    return false;
	}
        if (source == null) {
            if (other.source != null) {
		return false;
	    }
        } else if (!source.equals(other.source)) {
	    return false;
	}
        return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("{ ");
	builder.append(source);
	builder.append(" -> ");
	builder.append(sink);
	builder.append(" ; ");
	builder.append(label);
	builder.append(" }");
	return builder.toString();
    }
}
