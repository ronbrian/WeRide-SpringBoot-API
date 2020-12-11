
public class transactions extends user {
    public void withdraw(user abcd,int withdrawAmount){
        abcd.amount = abcd.amount - withdrawAmount;
        System.out.println(abcd.name + ", the remaining balance in your account is "+abcd.amount);
    }

    public void deposit(user abcd, int depositAmount){
        abcd.amount = abcd.amount + depositAmount;
        System.out.println(abcd.name + ", the remaining balance in your account is "+abcd.amount);
    }
}
