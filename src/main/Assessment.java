public class Assessment {

    private Float weight;
    private Float thigh;
    private Float waist;
    private String comment;
    private Trainer trainer;

    public Assessment() {}

    public Assessment(Float weight, Float thigh, Float waist, Trainer trainer){
        setWeight(weight);
        setThigh(thigh);
        setWaist(waist);
        setTrainer(trainer);
        comment = "None";
    }

    public Assessment(Float weight, Float thigh, Float waist, String comment) {
        setWeight(weight);
        setThigh(thigh);
        setWaist(waist);
        setComment(comment);
    }

    public Assessment(Float weight, Float thigh, Float waist, String comment, Trainer trainer) {
        setWeight(weight);
        setThigh(thigh);
        setWaist(waist);
        setComment(comment);
        setTrainer(trainer);
    }

    /**
     * Adds a comment String to the assessment.
     */
    public void addComment(String comment, Trainer trainer) {
        setComment(comment);
        setTrainer(trainer);
    }

    /**
     * Builds a String representing a user friendly representation of assessment info.
     *
     * @return Details of the assessment
     */
    public String toString() {
        return "\n" +
                "//-------------------------------------------------------//\n" +
                "   Weight: " + weight + " kg     Waist: " + waist + " cm     Thigh: " + thigh + " cm\n" +
                "   Comment: " + comment + "\n" +
                "   Trainer: " + trainer.getName() + " - " + trainer.getSpecialty() + " specialist" +
                "\n//-------------------------------------------------------//";
    }

    //-----------------------getters & setters-----------------------//


    public Float getWeight() {
        return weight;
    }

    public Float getThigh() {
        return thigh;
    }

    public Float getWaist() {
        return waist;
    }

    public String getComment() {
        return comment;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setThigh(Float thigh) {
        this.thigh = thigh;
    }

    public void setWaist(Float waist) {
        this.waist = waist;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
