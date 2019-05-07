public class Trainer extends Person{

    private String specialty;

    public Trainer() {}

    public Trainer(String email, String name, String address, String gender, String specialty) {
        super(email, name, address, gender);
        this.specialty = specialty;
    }

    /**
     * Builds a String representing a user friendly representation of trainer info
     * @return Details of the trainer
     */
    public String toString()
    {
        return "Trainer name: " + getName()
                + ", email: " + getEmail()
                + ", address: " + getAddress()
                + ", gender: " + getGender();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
