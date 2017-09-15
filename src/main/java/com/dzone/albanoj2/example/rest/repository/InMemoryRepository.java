package com.dzone.albanoj2.example.rest.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dzone.albanoj2.example.rest.domain.Identifiable;


public abstract class InMemoryRepository<T extends Identifiable> {

	private static long NEXT_ID = 1;
	private List<T> elements = new ArrayList<>();

	public T create(T element) {
		elements.add(element);
		element.setId(getNextId());
		return element;
	}

	private static long getNextId() {
		return NEXT_ID++;
	}

	public boolean delete(Long id) {
		return elements.removeIf(element -> element.getId().equals(id));
	}

	public List<T> findAll() {
		return elements;
	}

	public Optional<T> findById(Long id) {
		return elements.stream().filter(e -> e.getId().equals(id)).findFirst();
	}

	public int getCount() {
		return elements.size();
	}

	public void clear() {
		elements.clear();
	}

	public boolean update(Long id, T updated) {
		
		Optional<T> element = findById(id);
		
		element.ifPresent(original -> updateIfExists(original, updated));
		
		return element.isPresent();
	}
	
	protected abstract void updateIfExists(T original, T desired);

}
