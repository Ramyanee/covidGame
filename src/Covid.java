//package covid1;
import java.util.*;


class virus{
    private int life;
    private int attack;
    public void setLife(int life){
        this.life = life;
    }
    public void setAttack(int attack){
        this.attack= attack;
    }
    public int getLife(){
        return this.life;
    }
    public int getAttack(){
        return this.attack;
    }
}

abstract class vaccine{
    protected  int durability;
    protected int boost;

    protected abstract void inject(virus v);
    protected abstract void effect(virus v);
}


class covishield extends vaccine{

    public void inject(virus v){
        v.setLife(v.getLife()-10);
    }
    public void effect(virus v){
        v.setAttack(v.getAttack() - 10);
    }

    public void setDurability(int durability){
        this.durability = durability;
    }

    public void setBoost(int boost){
        this.boost = boost;
    }



}

class covaxin extends vaccine{

    public void inject(virus v){
        v.setLife(v.getLife()-5);
    }
    public void effect(virus v){
        v.setAttack(v.getAttack() - 5);
    }

    public void setDurability(int durability){
        this.durability = durability;
    }

    public void setBoost(int boost){
        this.boost = boost;
    }
}
class pfizer extends vaccine{

    public void inject(virus v){
        v.setLife(v.getLife()-6);
    }
    public void effect(virus v){
        v.setAttack(v.getAttack() - 4);
    }
    public void setDurability(int durability){
        this.durability = durability;
    }

    public void setBoost(int boost){
        this.boost = boost;
    }
}
class sputnik extends vaccine{

    public void inject(virus v){
        v.setLife(v.getLife()-4);
    }
    public void effect(virus v){
        v.setAttack(v.getAttack() - 8);
    }
    public void setDurability(int durability){
        this.durability = durability;
    }

    public void setBoost(int boost){
        this.boost = boost;
    }
}









class users {
    private String name;
    private String aadhar;
    private String vaccine;

    public void set_username(String x) {
        this.name = x;

    }

    public void set_aadhar(String val) {
        this.aadhar = val;
    }

    public void set_vaccine(String vax) {
        this.vaccine = vax;
    }

    public String get_aadhar() {
        return this.aadhar;
    }

    public String get_username() {
        return this.name;
    }

    public String get_vaccine() {
        return this.vaccine;
    }
}

class Covid {

    protected static ArrayList<users> db_users = new ArrayList<users>();

    public static int check_new_user(String val) {
        int is_string = 1;
        int len = val.length();
        int flag=1;

        for (int i = 0; i < len; i++) {


            if (val.charAt(i) >= '0'
                    && val.charAt(i) <= '9') {

            }
            else {
                is_string=0;
            }
        }

        for (int i = 0; i < db_users.size(); i++) {

            if (db_users.get(i).get_aadhar()== val) {
                flag=0;
            }
        }

        if (len == 4 && is_string == 1&&flag==1) {
            return 1;
        }


        return 0;

    }

    public static boolean check_existing_user(String name,String aadhar)
    {
        for (int i = 0; i < db_users.size(); i++)
        {

            if (db_users.get(i).get_aadhar().equals(aadhar)&&db_users.get(i).get_username().equals(name))
            {
                return true;
            }
        }

        return false;

    }
    public static String get_vaccine(int x)
    {
        String s=" ";
        if(x==1)
        {
            s="Covishield";
        }
        else if(x==2)
        {
            s="Covaxin";

        }

        else if(x==3)
        {
            s="Pfizer";
        }

        else if(x==4) s="Sputnik";

        else s="error";
        return s;

    }

    public static users return_user(String aadhar1) {
        int index=-1;
        for (int i = 0; i < db_users.size(); i++)
        {

            if (db_users.get(i).get_aadhar().equals(aadhar1))
            {
                index = i;
            }
        }
        return db_users.get(index);
    }

    public static void main(String[] args) {

        vaccination_portal();
    }

    public static void vaccination_portal() {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.println("Welcome To the Portal");
        System.out.println("Please select your option");
        System.out.println("1. New User");
        System.out.println("2. Existing User");
        System.out.println("3. Exit");
        choice = input.nextInt();
        if (choice == 1)
        {
            String x;
            String val;
            int vaccine;

            System.out.println("Enter Username:");
            input.nextLine();
            x = input.nextLine();
            System.out.println("Enter Aadhar Number:");
            val = input.nextLine();
            int correct = check_new_user(val);
            if (correct == 1)
            {


                System.out.println("Choose your vaccine:");
                System.out.println("1. Covishield");
                System.out.println("2. Covaxin");
                System.out.println("3. Pfizer");
                System.out.println("4. Sputnik");

                vaccine = input.nextInt();
                String vaccine_name=get_vaccine(vaccine);
                if(vaccine_name.equals("error"))
                {
                    System.out.println("You have choosen invalid vaccine, kindly try again later");
                    vaccination_portal();
                }

                else {
                    users c = new users();
                    c.set_username(x);
                    c.set_aadhar(val);
                    c.set_vaccine(vaccine_name);
                    System.out.println("Patient has been registered.");
                    System.out.println("Username - " + c.get_username());
                    System.out.println("Aadhar Number - " + c.get_username());
                    System.out.println("Vaccine Opted - " + c.get_vaccine());
                    db_users.add(c);
                    vaccination_portal();
                }


            }
            else if (correct == 0)
            {
                System.out.println("The details entered are invalid....Please try again");
                vaccination_portal();
            }

        }
        else if (choice == 2)
        {
            String name;
            String aadhar;
            System.out.println("Enter Registered Username:");
            input.nextLine();
            name = input.nextLine();
            System.out.println("Enter Aadhar Number:");
            aadhar=input.nextLine();

            boolean answer = check_existing_user(name,aadhar);
            System.out.println("Verifying ...");
            if (answer == true)
            {
                System.out.println("Patient Found !");
                users current = return_user(aadhar);
                System.out.println("Welcome " + current.get_username());
                System.out.println("You’re being tested for the 1st wave for the alpha variant of the virus");
                // build from here;
            }

            else
            {
                System.out.println("Patient not found !!! If you haven’t registered yet then please register first.");
                vaccination_portal();
            }
        }

        else {
            // add wave number;
            System.out.println("Thanks for your participation. Let’s get Vaccinated !!!");
            System.exit(0);
        }
        input.close();

    }

}
