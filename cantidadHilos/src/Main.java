public class Main {

    public static void main(String[] args) {
        int availableCores = Runtime.getRuntime().availableProcessors();

        System.out.println(availableCores);
    }
}