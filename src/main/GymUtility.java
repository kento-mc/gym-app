public class GymUtility {

    public static double calculateBMI(Member member, Assessment assessment)
    {
        double bmiValue = assessment.getWeight() / Math.pow(member.getHeight(), 2);
        return (int) (bmiValue *100) / 100.0;   // convert to two decimal places
    }

    public static String determineBMICategory(double bmiValue)
    {
        String str;
        if (bmiValue < 16) {
            str = "SEVERELY UNDERWEIGHT";
        } else if (bmiValue >= 16 && bmiValue < 18.5) {
            str = "UNDERWEIGHT";
        } else if (bmiValue >= 18.5 && bmiValue < 25) {
            str = "NORMAL";
        } else if (bmiValue >= 25 && bmiValue < 30) {
            str = "OVERWEIGHT";
        } else if (bmiValue >= 30 && bmiValue < 35) {
            str = "MODERATELY OBESE";
        } else {
            str = "SEVERELY OBESE";
        }
        return str;
    }

    public static boolean isIdealBodyWeight(Member member, Assessment assessment)
    {
        double maleIdealMemberWeight = (((member.getHeight() - 1.524) / .0254) * 2.3) + 50;
        double femaleIdealMemberWeight = (((member.getHeight() - 1.524) / .0254) * 2.3) + 45.5;

        if (member.getGender() == "M") {
            if (assessment.getWeight() <= maleIdealMemberWeight + .2 && assessment.getWeight() >= maleIdealMemberWeight - .2) {
                return true;
            } else {
                return false;
            }
        } else {
            if (assessment.getWeight() <= femaleIdealMemberWeight + .2 && assessment.getWeight() >= femaleIdealMemberWeight - .2) {
                return true;
            } else {
                return false;
            }
        }
    }
}
