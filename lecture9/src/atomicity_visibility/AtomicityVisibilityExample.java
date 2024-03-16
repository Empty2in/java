package atomicity_visibility;

public class AtomicityVisibilityExample {
    public static void main(String[] args) {
        Incrementor incrementor = new Incrementor();
        new Thread1(incrementor);
        new Thread2(incrementor);
    }
}
