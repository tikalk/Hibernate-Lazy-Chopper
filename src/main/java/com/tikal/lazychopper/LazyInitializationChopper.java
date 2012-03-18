package com.tikal.lazychopper;

import java.util.Collection;

public interface LazyInitializationChopper {

	/*
	 * method that run over all nodes and clear their unloaded children. 
	 * The API include
	 * 
	 * @Object node : root
	 */
	Object chop(Object node) throws LazyInitializationChopperException;

	boolean collectionContainsProxies(Collection node);

	Collection filterProxiesFromCollection(Collection node);

}