package com.nosreme.svc.zcommon.pojo;

import java.util.Set;

public class Role
{
	private int id;
	private String name;
	
	private Set<Group> groups;
	private Set<User> users;
}
