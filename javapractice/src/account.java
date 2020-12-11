

interface account{
    public void createAccount();
}


class accountOperations  implements account, Runnable {

    int[] myNos = {1,2,3};

    public void createAccount(){

        System.out.println(myNos[1]);

       accountOperations obj = new accountOperations();
       Thread thread = new Thread(obj);

        try {
            Thread.sleep(5 * 1000); //thread sleeps for 5 seconds
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        thread.start();

    }
        public void run(){
            System.out.println("This is inside the thread ");
        }
    }




