package Model;

public class Hotel
{
    public int getId() {
        return id;
    }

    private int id;
    private String name;
    private String address;
    private String stars;
    private String phone_number;
    private String email;
    private String city;
    private String country;

    public Hotel(int id, String name, String address, String stars)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.stars = stars;
    }

    public Hotel(String name, String address, String stars)
    {
        this.name = name;
        this.address = address;
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getStars() {
        return stars;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Hotel(int id, String name, String address, String stars, String phone_number, String email, String city, String country)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.stars = stars;
        this.phone_number = phone_number;
        this.email = email;
        this.city = city;
        this.country = country;
    }
}
