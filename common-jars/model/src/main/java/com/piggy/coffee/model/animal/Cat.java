package com.piggy.coffee.model.animal;

public class Cat implements Move {

	@Override
	public void move() {
		System.out.println("run");

	}
	
	public void escape() {
		move();
	}
	

}
