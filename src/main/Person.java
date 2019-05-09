


public class Person {
    private String name;
    private String email;
    private String address;
    private String gender;


    public Person() {}

    public Person(String email, String name, String address, String gender) {
        setName(name);
        this.email = email;
        this.address = address;
        setGender(gender);
    }


    public void setName(String name) {
        if (name.length() > 30) {
            this.name = name.substring(0, 30);
        } else {
            this.name = name;
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        if (gender.charAt(0) == 'M' || gender.charAt(0) == 'm') {
            this.gender = "M";
        } else if (gender.charAt(0) == 'F' || gender.charAt(0) == 'f') {
            this.gender = "F";
        } else {
            this.gender = "Unspecified";
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

}
