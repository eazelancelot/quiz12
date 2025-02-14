package com.example.quiz12.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz")
public class Quiz {

	// @GeneratedValue 可參考投影片SpringBoot_01_基礎 的 P.26~28
	// 此 Annotation 使用上的差別在於若是使用 JAP 的 save 方法時，欄位是AI(Auto Incremental)時，//
	// 該欄位的資料型態:
	// 1. 是 int 時，可不加，但 save 方法的回傳值中，不會有最新的 AI 欄位值，加了才會有最新的AI欄位值，//
	// 2. 是 Integer 時，一定要加，不然會報錯
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "published")
	private boolean published;

	public Quiz() {
		super();
	}

	public Quiz(String name, String description, LocalDate startDate, LocalDate endDate, boolean published) {
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.published = published;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public boolean isPublished() {
		return published;
	}

}
