import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GymAPI {
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;


    public GymAPI() {
        this.members = new ArrayList<Member>();
        this.trainers = new ArrayList<Trainer>();
    }

    public void addMember(Member member) {
        members.add(member);
    }


    public String listMembers() {
        if (members.size() == 0){
            return "\nNo members! This gym must be terrible...";
        }
        else{
            String listOfMembers = "";
            int index = 0;
            for (Member member : members){
                listOfMembers = listOfMembers + index + ": " + member + "\n";
                index ++;
            }
            return listOfMembers;
        }
    }

    public Member searchMembersByEmail (String emailEntered)
    {
        for (Member member : members)
        {
            if (member.getEmail().equals(emailEntered)) {
                return member;
            }
        }
        return null;
    }

    public ArrayList<String> searchMembersByName (String nameEntered)
    {
        ArrayList<String> nameMatches = new ArrayList<String>();
        for (Member member : members)
        {
            if (member.getName().contains(nameEntered) || member.getName().equals(nameEntered)) {
                nameMatches.add(member.getName());
            }
        }
        return nameMatches;
    }

    public ArrayList<Member> listMembersWithIdealWeight () {
        ArrayList<Member> idealMembers = new ArrayList<>();
        for (Member member : members) {
            if (!member.getAssessments().isEmpty()) {
                if (GymUtility.isIdealBodyWeight(member, member.latestAssessment()) == true) {
                    idealMembers.add(member);
                }
            }
        }
        return idealMembers;
    }

    public ArrayList<Member> listMembersBySpecificBMICategory (String category) {
        ArrayList<Member> membersBMI = new ArrayList<>();
        for (Member member : members ) {
            if (category.equals(GymUtility.determineBMICategory(GymUtility.calculateBMI(member, member.latestAssessment()))) ||
                category.toUpperCase().equals(GymUtility.determineBMICategory(GymUtility.calculateBMI(member, member.latestAssessment()))) ||
                GymUtility.determineBMICategory(GymUtility.calculateBMI(member, member.latestAssessment())).contains(category)) {
                membersBMI.add(member);
            }
        }
        return membersBMI;
    }

    public String listMemberDetailsImperialAndMetric () {
        String details = "";
        DecimalFormat df = new DecimalFormat("#.#");
        if (!members.isEmpty()) {
            for (Member member : members) {
                details = details + member.getName() + ": " + Math.round(member.latestAssessment().getWeight()) + " kg (" +
                          Math.round((member.latestAssessment().getWeight() * 2.2)) + " lbs) " + df.format(member.getHeight()) +
                          " metres (" + Math.round((member.getHeight() * 39.37)) + " inches).\n";
            }
            return details;
        } else {
            return "No registered members";
        }
    }

    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }

    public Trainer searchTrainersByEmail (String emailEntered)
    {
        for (Trainer trainer : trainers) {
            if (trainer.getEmail().equals(emailEntered)) {
                return trainer;
            }
        }
        return null;
    }

    public ArrayList<String> searchTrainersByName (String nameEntered)
    {
        ArrayList<String> nameMatches = new ArrayList<String>();
        for (Trainer trainer : trainers)
        {
            if (trainer.getName().contains(nameEntered) || trainer.getName().equals(nameEntered)) {
                nameMatches.add(trainer.getName());
            }
        }
        return nameMatches;
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());

        // ------------------ PREVENT SECURITY WARNINGS---------------------------
        // The Trainer and Member classes are what we are reading in.
        // Modify to to include others if needed.

        Class<?>[] classes = new Class[] { Trainer.class, Member.class, PremiumMember.class, StudentMember.class, Assessment.class};
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        // -----------------------------------------------------------------------

        ObjectInputStream membersInput = xstream.createObjectInputStream(new FileReader("members.xml"));
        members = (ArrayList<Member>) membersInput.readObject();
        membersInput.close();

        ObjectInputStream trainersInput = xstream.createObjectInputStream(new FileReader("trainers.xml"));
        trainers = (ArrayList<Trainer>) trainersInput.readObject();
        trainersInput.close();

    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());

        ObjectOutputStream membersOutput = xstream.createObjectOutputStream(new FileWriter("members.xml"));
        membersOutput.writeObject(members);
        membersOutput.close();

        ObjectOutputStream trainersOutput = xstream.createObjectOutputStream(new FileWriter("trainers.xml"));
        trainersOutput.writeObject(trainers);
        trainersOutput.close();

    }

    //-----------------------getters & setters-----------------------//

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public int numberOfMembers() {
        return members.size();
    }

    public int numberOfTrainerss() {
        return trainers.size();
    }

    public boolean isValidMemberIndex(int index) {
        if (!members.isEmpty() && index >= 0 && index <= members.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidTrainerIndex(int index) {
        if (!trainers.isEmpty() && index >= 0 && index <= trainers.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

}
