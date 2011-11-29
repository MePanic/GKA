package GKA;

import Interfaces.Vertex;

public class VertexImpl implements Vertex {

	private int id;

	public static Vertex valueOf(int id) {
		return new VertexImpl(id);
	}

	private VertexImpl(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return ((Integer) id).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VertexImpl other = (VertexImpl) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
