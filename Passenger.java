package Project;

public class Passenger {
    String name;
    String nationality;
    String phone;
    String address;
    String aadhar;
    String gender;

    public Passenger(String name, String nationality, String phone, String address, String aadhar, String gender) {
        this.name = name;
        this.nationality = nationality;
        this.phone = phone;
        this.address = address;
        this.aadhar = aadhar;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Nationality: " + nationality +
                ", Phone: " + phone + ", Address: " + address +
                ", Aadhar: " + aadhar + ", Gender: " + gender;
    }
}
