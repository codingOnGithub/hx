package com.hotent.org.persistence.query;

import com.hotent.base.api.query.Direction;

public class OrderBy {
	private String property;

	private Direction direction;

	public String getProperty() {
		return property;
	}

	public Direction getDirection() {
		return direction;
	}

	protected OrderBy(String property, Direction direction) {
		this.direction = direction;
		this.property = property;
	}
	
	public static void main(String[] args) {
		System.out.println(Direction.ASC);
	}
}
