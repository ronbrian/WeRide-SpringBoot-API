public class user {
    public String name;
    public int age;
    public int amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSummary(){

        return "The User "+name +" is "+ age +" years old, and has "+amount+" in his bank account.";
    }




}
