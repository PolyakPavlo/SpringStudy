package lab1.practice;

public class MessagePrinter implements Printer {
    private String message;

    @InjectRandom(10)
    private int count;

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.println(message);
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
