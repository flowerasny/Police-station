package view;

public class Main {

    private static void nap(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }


    public static void main(String[] a) {
        final ApplicationWindow applicationWindow = new ApplicationWindow();
        applicationWindow.pack();
        applicationWindow.setVisible(true);

        new Thread(() -> {
            while (true) {
                nap(25);
                applicationWindow.repaint();
            }
        }).start();
    }

}
