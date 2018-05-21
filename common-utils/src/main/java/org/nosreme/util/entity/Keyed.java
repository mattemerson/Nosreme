package org.nosreme.util.entity;

public interface Keyed<T>
{
	T getID();
	void setID(T id);
}
