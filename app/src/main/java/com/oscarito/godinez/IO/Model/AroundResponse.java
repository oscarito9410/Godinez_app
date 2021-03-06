package com.oscarito.godinez.IO.Model;

/**
 * Created by oemy9 on 06/01/2017.
 */
public class AroundResponse {

        private int id;

        public int getId() { return this.id; }

        public void setId(int id) { this.id = id; }

        private String name;

        public String getName() { return this.name; }

        public void setName(String name) { this.name = name; }

        private double latitude;

        public double getLatitude() { return this.latitude; }

        public void setLatitude(double latitude) { this.latitude = latitude; }

        private double longitude;

        public double getLongitude() { return this.longitude; }

        public void setLongitude(double longitude) { this.longitude = longitude; }

        private String address;

        public String getAddress() { return this.address; }

        public void setAddress(String address) { this.address = address; }


        private CategoryResponse category;

        public CategoryResponse getCategory() { return this.category; }

        public void setCategory(CategoryResponse category) { this.category = category; }

        private String logo;

        public String getLogo() { return this.logo; }

        public void setLogo(String logo) { this.logo = logo; }

        private  double rating;

        public double getRating() {
                return rating;
        }

        public void setRating(double rating) {
                this.rating = rating;
        }
}
