package com.nosreme.starter.pojo;

import java.util.Set;

public class Group
{
	private int id;
	private String name;
	private Set<Group> groups;
	private Set<User> users;
}
