package com.tikal.lazychopper;

import java.util.Map;

public class GraphTraverser {

	public interface GraphNodeVisitor {
		public Map<Integer,Object> visitAndGetDescendants(Object node) throws TraverseException;
		public boolean isUnvisited(Object node);
	}

	public static class TraverseException extends Exception {
		
		
		public TraverseException(Throwable cause) {
			super(cause);
		}
	}
	public void traverse(Object node, GraphNodeVisitor visitor) throws TraverseException {
		if (node == null)
			return;
		for (Object child : visitor.visitAndGetDescendants(node).values()) 
			if (visitor.isUnvisited(child)) 
				traverse(child, visitor);

	}
}
