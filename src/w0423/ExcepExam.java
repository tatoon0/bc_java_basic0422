package w0423;

public class ExcepExam {
    public static void main(String[] args) {
        int[] ints = new int[10];

        try {
            for (int i = 0; true; i++){
                ints[i] = i;
                System.out.println(ints[i]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
