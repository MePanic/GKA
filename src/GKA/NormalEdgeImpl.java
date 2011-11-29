package GKA;

import java.util.Arrays;

import Interfaces.NormalEdge;

public class NormalEdgeImpl implements NormalEdge{

	private int[] id = new int[2];
	
	public static NormalEdge valueOf(int source, int target){
		return new NormalEdgeImpl(source, target);
	}
	
	private NormalEdgeImpl(int source, int target){
		this.id[0] = source;
		this.id[1] = target;
	}
		

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(id);
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
		NormalEdgeImpl other = (NormalEdgeImpl) obj;
		if (!Arrays.equals(id, other.id))
			return false;
		return true;
	}

	public int[] getId(){
		return this.id;
	}

	public Double getValue() {
		return 0.0;
	}
		
}
