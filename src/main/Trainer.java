public class Trainer extends Person{

    public Trainer() {}

    public Trainer(String email, String name, String address, String gender) {
        super(email, name, address, gender);
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
}
