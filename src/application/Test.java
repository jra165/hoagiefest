package application;

import java.util.ArrayList;

public class Test {

	
	public static void main(String[] args) {
		Chicken chicken = new Chicken();
		//Extra Tomatoes = new Extra("Tomatoes");
		chicken.add(Extra.TOMATOES);
		chicken.add(Extra.GOLD);
		System.out.println(chicken.price());
	}
}
