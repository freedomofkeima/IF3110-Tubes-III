package com.frexescwebservice.model;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import com.frexescwebservice.controller.DbConnection;

public class BarangBean {
	private long id;
	private long id_category;
	private String name;
	private String picture;
	private int price;
	private String description;
	private int total_item;

	public BarangBean() {
	}

	public BarangBean(long id, long id_category, String name, String picture,
			int price, String description, int total_item) {
		super();
		this.id = id;
		this.id_category = id_category;
		this.name = name;
		this.picture = picture;
		this.price = price;
		this.description = description;
		this.total_item = total_item;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_category() {
		return id_category;
	}

	public void setId_category(long id_category) {
		this.id_category = id_category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotal_item() {
		return total_item;
	}

	public void setTotal_item(int total_item) {
		this.total_item = total_item;
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("id", id);
			json.put("id_category", id_category);
			json.put("name", name);
			json.put("picture", picture);
			json.put("price", price);
			json.put("description", description);
			json.put("total_item", total_item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public void save() {
		DbConnection dbConnection = new DbConnection();
		Connection connection = dbConnection.mySqlConnection();

		String insertQuery = "INSERT INTO barang (id_kategori, nama_barang, gambar, harga_barang, keterangan, jumlah_barang) VALUES ('"
				+ id_category
				+ "','"
				+ name
				+ "','"
				+ picture
				+ "','"
				+ price
				+ "','" + description + "','" + total_item + "')";

		try {
			connection.createStatement().executeUpdate(insertQuery);
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
