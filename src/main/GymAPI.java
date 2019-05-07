import java.util.ArrayList;

public class GymAPI {
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;


    public GymAPI() {
        this.members = new ArrayList<Member>();
        this.trainers = new ArrayList<Trainer>();
    }


    public ArrayList<Member> getMembers() {
        return members;
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


    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
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
