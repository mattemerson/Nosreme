package org.nosreme.util.entity;

public interface EntityReference<T> 
{
	T getDetail();
	void setDetail(T detail);
}
