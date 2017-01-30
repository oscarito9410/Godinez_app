package com.oscarito.godinez.IO.Model;

import java.util.ArrayList;

/**
 * Created by oemy9 on 28/01/2017.
 */

public class DetailsResponse
{
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private String latitude;

    public String getLatitude() { return this.latitude; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    private String longitude;

    public String getLongitude() { return this.longitude; }

    public void setLongitude(String longitude) { this.longitude = longitude; }

    private String address;

    public String getAddress() { return this.address; }

    public void setAddress(String address) { this.address = address; }

    private String phone;

    public String getPhone() { return this.phone; }

    public void setPhone(String phone) { this.phone = phone; }

    private CategoryResponse category;

    public CategoryResponse getCategory() { return this.category; }

    public void setCategory(CategoryResponse category) { this.category = category; }

    private String logo;

    public String getLogo() { return this.logo; }

    public void setLogo(String logo) { this.logo = logo; }

    private int ranking;

    public int getRanking() { return this.ranking; }

    public void setRanking(int ranking) { this.ranking = ranking; }

    private ServiceResponse service;

    public ServiceResponse getService() { return this.service; }

    public void setService(ServiceResponse service) { this.service = service; }

    private ArrayList<MenuResponse> menu;

    public ArrayList<MenuResponse> getMenu() { return this.menu; }

    public void setMenu(ArrayList<MenuResponse> menu) { this.menu = menu; }
}