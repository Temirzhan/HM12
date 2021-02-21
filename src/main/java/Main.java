import java.util.concurrent.ExecutionException;

public class Main {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE/2;


    public static void main(String[] args) throws InterruptedException {
        float[] arr = new float[SIZE];
        float[] arrs = new float[SIZE];
        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];

        for (int i = 0; i < arr.length; i++){
            arr[i] = 1;
        }

        for (int i = 0; i < arr.length; i++){
            arrs[i] = 1;
        }



        long a = System.currentTimeMillis();
        fillArr(arr,0);
        System.out.println(System.currentTimeMillis()-a);

        a = System.currentTimeMillis();



        Thread firstThread = new Thread(() -> {
            System.arraycopy(arrs,0,arr1,0,HALF);
            fillArr(arr1,0);
            System.arraycopy(arr1, 0, arrs, 0, HALF);
        });

        Thread secondThread = new Thread(() -> {

            System.arraycopy(arrs,HALF,arr2,0,HALF);

            fillArr(arr2,HALF);
            System.arraycopy(arr2, 0, arrs, HALF, HALF);


        });


        firstThread.start();
        firstThread.join(500);
        secondThread.start();
        secondThread.join(500);
        System.out.println(System.currentTimeMillis()-a);

        printArr(arr);
        printArr(arrs);


    }

    public static void fillArr(float[] arr,int j){
        for (int i = 0; i < arr.length; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (i+j) / 5) * Math.cos(0.2f + (i+j) / 5) * Math.cos(0.4f + (i+j) / 2));
        }
    }
    public static void printArr(float[] arr){

        System.out.print(arr[0] + " ");
        System.out.print(arr[arr.length-1] + " ");
        System.out.println();
    }
}


