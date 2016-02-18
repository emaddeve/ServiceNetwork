package tests;



public class TestPersonDBStub {

    public static void main (String [] args) {
        System.out.print("Testing class PersonDBStub... ");
        try {
            persons.TestPersonDB.test(new persons.PersonDBStub());
        } catch (Throwable e) {
            System.out.println("\nTests failed: "+e);
            e.printStackTrace();
            System.exit(1);
        }
        System.out.print("OK");
    }

}

