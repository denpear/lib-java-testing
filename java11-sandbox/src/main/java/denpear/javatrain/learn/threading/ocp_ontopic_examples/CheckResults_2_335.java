package denpear.javatrain.learn.threading.ocp_ontopic_examples;

// + sleep()
public class CheckResults_2_335 {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                CheckResults_2_335.counter++;
            }
        }).start();
        while (CheckResults_2_335.counter < 100) {
            System.out.println("Еще не достигнут предел");
            Thread.sleep(1000);
        }
        System.out.println("Достигнуто!");
    }
}
