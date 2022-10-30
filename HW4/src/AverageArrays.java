public class AverageArrays {
    public static void main(String[] args) {
        double[] p1 = new double[18];
        double[] p2 = new double[18];
        double[] p3 = new double[18];
        double[] p4 = new double[18];
        double[] p5 = new double[18];
        double[] p6 = new double[18];
        double[] data1 = {2, 2, 4, 5, 6, 6, 17, 33, 37, 125, 374, 1006, 604, 1403,
                3579, 7278, 2184, 51647};
        double[] data2 = {1, 1, 4, 5, 8, 11, 30, 6, 44, 135, 439, 662, 1304, 1771,
                5632, 29, 5541, 56347};
        double[] data3 = {1, 2, 1, 2, 3, 14, 9, 56, 124, 169, 197, 974, 1814, 1355,
                3962, 11986, 12815, 22025};
        double[] data4 = {2, 3, 1, 3, 3, 16, 20, 63, 14, 125, 354, 623, 284, 2796,
                5808, 6346, 10146, 31136};
        double[] data5 = {4, 3, 6, 7, 10, 7, 12, 64, 60, 23, 126, 731, 567, 1394,
                6240, 1934, 13067, 44851};
        for (int i = 0; i < data1.length; i++){
            double toUse = (data1[i] + data2[i] + data3[i] + data4[i] + data5[i])/5;
            System.out.print((int)toUse + " ");
        }
    }
}
