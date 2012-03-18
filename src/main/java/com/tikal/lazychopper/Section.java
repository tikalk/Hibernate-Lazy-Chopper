package com.tikal.lazychopper;

import java.util.List;
@Chopped
public interface Section<T> {

	List<T> getResults();

	Long getCount();
	
	Long getTotalCount();

}