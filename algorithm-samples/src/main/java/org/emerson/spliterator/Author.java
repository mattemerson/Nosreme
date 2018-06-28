package org.emerson.spliterator;

public class Author {
	private int id;
	private String name;
	private int relatedArticleId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRelatedArticleId()
	{
		return this.relatedArticleId;
	}
	
	public Author()
	{
		
	}
	
	public Author(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
