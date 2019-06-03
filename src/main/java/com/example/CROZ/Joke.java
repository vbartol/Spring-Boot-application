package com.example.CROZ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "joke")
public class Joke {

	@Id
	@NotNull
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	
	@Size(min=2, max=255)
	@Column(name = "content")
	private String content;
	
	@NotNull
	@Column(name = "category")
	private String category;
	
	@Column(name = "likes")
	private int likes;
	
	@Column(name = "dislikes")
	private int dislikes;
	
	@Column(name = "difference")
	private int difference;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	} 
	public int getDifference() {
		return difference;
	}
	public void setDifference(int difference) {
		this.difference = difference;
	}
}
