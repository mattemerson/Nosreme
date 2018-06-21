package org.emerson.objects;

//Java program to demonstrate Objects.toString(Object o) 
//and Objects.toString(Object o, String nullDefault) methods

import java.util.Objects;

class Pair<K, V> {
	public K key;
	public V value;

	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public void setKey(K key) {
		this.key = Objects.requireNonNull(key);
	}

	public void setValue(V value) {
		this.value = Objects.requireNonNull(value, "no value");
	}

	public int hashCodeOption2() {
		return (Objects.hashCode(key) ^ Objects.hashCode(value));

		/*
		 * without Objects.hashCode(Object o) method return (key == null ? 0 :
		 * key.hashCode()) ^ (value == null ? 0 : value.hashCode());
		 */
	}

	@Override
	public int hashCode() {
		return (Objects.hash(key, value));
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pair)) {
			return false;
		}
		Pair<?, ?> p = (Pair<?, ?>) o;
		return Objects.equals(p.key, key) && Objects.equals(p.value, value);

	}

	@Override
	public String toString() {
		return "Pair {key = " + Objects.toString(key) + ", value = " + Objects.toString(value, "no value") + "}";

		/*
		 * without Objects.toString(Object o) and Objects.toString(Object o, String
		 * nullDefault) method return "Pair {key = " + (key == null ? "null" :
		 * key.toString()) + ", value = " + (value == null ? "no value" :
		 * value.toString()) + "}";
		 */
	}
}