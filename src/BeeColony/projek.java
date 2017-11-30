/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeeColony;

/**
 *
 * @author Uhuuy31
 */
import java.util.*;


public class projek {

    int populasi = 100;
    int iterasi ;
    public int mapel[] = {1, 2, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 10, 11, 12, 13};
    public int individu[][] = new int[populasi][109];
    public double fitness[] = new double[populasi];
    //int tampung[][] = new int[populasi][109];
    double tampungF[] = new double[populasi];
    int trial[] = new int[populasi];

    public int[] inisialisasi() {
        int[] temp = new int[109];
        for (int i = 0; i < 109; i++) {
            temp[i] = 0;
        }
        int random;
        for (int i = 0; i < 6; i++) {
            int j = 0;
            random = (int) Math.ceil(Math.random() * 18) - 1;
            //System.out.println(random);
            while (j < 18) {
                if (temp[18 * i + random] != 0) {
                    random = (int) Math.ceil(Math.random() * 18) - 1;
                    //System.out.println(i+" "+random);
                } else {
                    temp[18 * i + random] = mapel[j];
                    j++;

                }

            }
        }
//        for (int i = 0; i < 109; i++) {
//            System.out.print(" " + temp[i]);
//        }
        return temp;
    }

    public double hitungFitness(int[] data) {
        double fitness;
        double count = 0;
        for (int i = 0; i < 6; i = i + 2) {
            for (int j = 0; j < 18; j++) {
                if (data[(i * 18) + j] == data[((i + 1) * 18) + j]) {
                    count++;
                    //System.out.print(count);
                }

            }
        }
        fitness = 1 / (1 + count);
        return fitness;
    }

    public int getIndexSama(int[] data) {
        int i = 0;
        int j = 0;
        int a = 0;
        int b = 0;

        for (i = 0; i < 5; i = i + 2) {
            for (j = 0; j < 18; j++) {
                if (data[(i * 18) + j] == data[((i + 1) * 18) + j]) {
                    System.out.println("nilai : " + (i * 18 + j));
                    a = i;
                    b = j;

                }

            }
        }
        return a * 18 + b;

    }

    public int[] swapO(int data[]) {
        int index = getIndexSama(data);
        System.out.println("index : " + index);
        int[] tampung = data;
        int range = index / 18;
        int random = (int) Math.ceil(Math.random() * 18) - 1;
        int temp = 0;
        temp = data[index];
        data[index] = data[range * 18 + random];
        data[range * 18 + random] = temp;
        if (hitungFitness(data) < hitungFitness(tampung)) {
            data = tampung;
            data[108]++;
        } else {
            data[108] = 0;
        }
        return data;
    }

    public boolean perbandinganF(double data1, double data2) {
        //projek a = new projek();
        boolean hasil = false;
        if (data1 > data2) {
            hasil = true;
        } else {
            hasil = false;
        }
        return hasil;
    }

    public int[] swapS(int data[], int index) {

        int nse;
        nse = 2 * 108;
        int temp = 0;

        for (int i = 0; i < nse; i++) {
            int j = 0;
            int a = (int) Math.ceil(Math.random() * 6) - 1;
            int random2 = (int) Math.ceil(Math.random() * 18) - 1;
            int random = (int) Math.ceil(Math.random() * 18) - 1;
            //int temp = 0;
            //System.out.println(random);
            while (j < 1) {
                if (random == random2) {
                    random = (int) Math.ceil(Math.random() * 18) - 1;
                    //System.out.println(i+" "+random);
                } else {
                    temp = data[a * 18 + random];
                    data[a * 18 + random] = data[a * 18 + random2];
                    data[a * 18 + random2] = temp;
                    //
                    hitungFitness(data);
                    j++;

                }
            }
            System.out.println(hitungFitness(data));
            System.out.println(hitungFitness(individu[index]));
            if (hitungFitness(data) < hitungFitness(individu[index])) {
                data = data;
                data[108]++;
            } else {
                data[108] = 0;
            }
        }
        data = individu[index];
        return data;

    }

    public int[] insertO(int data[]) {
        int a = (int) Math.ceil(Math.random() * 6) - 1;
        int random = (int) Math.ceil(Math.random() * 18) - 1;//index yang akan di pindah
        int random2 = (int) Math.ceil(Math.random() * 18) - 1;
        int[] tampung = data;
        int temp = 0;
        int j = 0;
        while (j < 1) {
            if (random == random2) {
                random = (int) Math.ceil(Math.random() * 18) - 1;
            } else {
                if (random > random2) {
                    temp = data[a * 18 + random];
                    for (int i = a * 18 + random; i > a * 18 + random2; i--) {
                        data[i] = data[i - 1];

                    }
                    data[a * 18 + random2] = temp;
                    j++;
                }
                if (random < random2) {
                    temp = data[a * 18 + random];
                    for (int i = a * 18 + random; i < a * 18 + random2; i++) {
                        data[i] = data[i + 1];
                    }
                    data[a * 18 + random2] = temp;
                    j++;
                }

            }
        }
        if (hitungFitness(data) < hitungFitness(tampung)) {
            data = tampung;
            data[108]++;
        } else {
            data[108] = 0;
        }
        return data;

    }

    public int[] insertS(int data[]) {
        int nse;
        nse = 2 * 108;
        int temp = 0;

        int[] tampung = data;
        for (int i = 0; i < nse; i++) {
            int a = (int) Math.ceil(Math.random() * 6) - 1;
            int random = (int) Math.ceil(Math.random() * 18) - 1;//index yang akan di pindah
            int random2 = (int) Math.ceil(Math.random() * 18) - 1;
            int j = 0;
            while (j < 1) {
                if (random == random2) {
                    random = (int) Math.ceil(Math.random() * 18) - 1;
                } else {
                    if (random > random2) {
                        temp = data[a * 18 + random];
                        for (int k = a * 18 + random; k > a * 18 + random2; k--) {
                            data[k] = data[k - 1];

                        }
                        data[a * 18 + random2] = temp;
                        j++;
                    }
                    if (random < random2) {
                        temp = data[a * 18 + random];
                        for (int k = a * 18 + random; k < a * 18 + random2; k++) {
                            data[k] = data[k + 1];
                        }
                        data[a * 18 + random2] = temp;
                        j++;
                    }

                }

            }
            if (hitungFitness(data) < hitungFitness(tampung)) {
                data = tampung;
                data[108]++;
            } else {
                data[108] = 0;
            }
        }
        return data;
    }

    public static void main(String[] args) {
        projek a = new projek();
        Scanner input = new Scanner(System.in);
        System.out.println("Masukkan Populasi Yang Diinginkan : ");
        a.populasi = input.nextInt();
        System.out.println("Masukkan Iterasi Yang Diinginkan : ");
        a.iterasi = input.nextInt();
        
        //memasukkan inisialisasi ke array individu
        for (int i = 0; i < a.populasi; i++) {
            a.individu[i] = a.inisialisasi();
        }
        System.out.println("INISIALISASI  : ");
        for (int i = 0; i < a.populasi; i++) {
            System.out.print((i + 1) + " . ");
            for (int j = 0; j < 109; j++) {
                System.out.print(a.individu[i][j] + " ");
            }
            System.out.println("");
        }

        //memasukkan dan menampilkan fitness
        System.out.println("fitness : ");
        for (int i = 0; i < a.populasi; i++) {
            a.fitness[i] = a.hitungFitness(a.individu[i]);
            System.out.println(i + " " + a.fitness[i]);
        }
        for (int x = 0; x < a.iterasi; x++) {

            //melakukan operasi Swap
            System.out.println("Swap Operator : ");
            int tampung[][] = a.individu;
            for (int i = 0; i < a.populasi; i++) {
                tampung[i] = a.swapO(a.individu[i]);
            }
            for (int i = 0; i < a.populasi; i++) {
                for (int j = 0; j < 108; j++) {
                    System.out.print(" " + tampung[i][j]);
                }
                System.out.println("");
            }

            System.out.println("fitnes hasil Swap O : ");

            a.tampungF = a.fitness;
            for (int i = 0; i < a.populasi; i++) {
                a.fitness[i] = a.hitungFitness(tampung[i]);
                System.out.println(i + " " + a.fitness[i]);

            }
            //individu terpilih setelah Swap O

            for (int i = 0; i < a.populasi; i++) {
                if (a.perbandinganF(a.fitness[i], a.tampungF[i])) {
                    a.individu[i] = tampung[i];
                    //a.individu[i][108] = 0;
                    System.out.println("masuk");

                }

            }
            for (int j = 0; j < a.populasi; j++) {
                for (int k = 0; k < 108; k++) {
                    System.out.print(" " + a.individu[j][k]);
                }
                System.out.println("");
            }

            //melakukan Swap Squence
            a.tampungF = a.fitness;
            System.out.println("Hasil Swap S : ");
            tampung = a.individu;
            for (int i = 0; i < a.populasi; i++) {
                tampung[i] = a.swapS(tampung[i], i);
            }

            for (int i = 0; i < a.populasi; i++) {
                for (int j = 0; j < 108; j++) {
                    System.out.print(" " + tampung[i][j]);
                }
                System.out.println("");
            }

            System.out.println("fitnes hasil Swap S : ");

            for (int i = 0; i < a.populasi; i++) {
                a.fitness[i] = a.hitungFitness(tampung[i]);
                System.out.println(i + " " + a.fitness[i]);

            }

            //individu terpilih setelah SS
            for (int i = 0; i < a.populasi; i++) {
                if (a.perbandinganF(a.fitness[i], a.tampungF[i])) {
                    a.individu[i] = tampung[i];

                    System.out.println("masuk");

                }
            }

            for (int j = 0; j < a.populasi; j++) {
                for (int k = 0; k < 108; k++) {
                    System.out.print(" " + a.individu[j][k]);
                }
                System.out.println("");
            }

            //proses IO : 
            System.out.println("Proses IO : ");
            //a.tampungF = a.fitness;
            tampung = a.individu;
            for (int i = 0; i < a.populasi; i++) {
                tampung[i] = a.insertO(a.individu[i]);

            }
////        for (int i = 0; i < a.populasi; i++) {
////            for (int j = 0; j < 108; j++) {
////                System.out.print(" " + tampung[i][j]);
////            }
////            System.out.println("");
////        }

            System.out.println("fitnes hail Insert O : ");

            a.tampungF = a.fitness;
            for (int i = 0; i < a.populasi; i++) {
                a.fitness[i] = a.hitungFitness(tampung[i]);
                System.out.println(i + " " + a.fitness[i]);

            }
            //individu terpilih setelah insert O

            for (int i = 0; i < a.populasi; i++) {
                if (a.perbandinganF(a.fitness[i], a.tampungF[i])) {
                    a.individu[i] = tampung[i];
                    System.out.println("masuk");

                }

            }
            for (int j = 0; j < a.populasi; j++) {
                for (int k = 0; k < 108; k++) {
                    System.out.print(" " + a.individu[j][k]);
                }
                System.out.println("");
            }

            //proses insert S
            System.out.println("Proses IS : ");
            //a.tampungF = a.fitness;
            tampung = a.individu;
            for (int i = 0; i < a.populasi; i++) {
                tampung[i] = a.insertS(a.individu[i]);

            }
////        for (int i = 0; i < a.populasi; i++) {
////            for (int j = 0; j < 108; j++) {
////                System.out.print(" " + tampung[i][j]);
////            }
////            System.out.println("");
////        }

            System.out.println("fitnes hasil Insert S : ");

            a.tampungF = a.fitness;
            for (int i = 0; i < a.populasi; i++) {
                a.fitness[i] = a.hitungFitness(tampung[i]);
                System.out.println(i + " " + a.fitness[i]);

            }
            //individu terpilih setelah insert S

            for (int i = 0; i < a.populasi; i++) {
                if (a.perbandinganF(a.fitness[i], a.tampungF[i])) {
                    a.individu[i] = tampung[i];
                    System.out.println("masuk");

                }

            }
            for (int j = 0; j < a.populasi; j++) {
                for (int k = 0; k < 109; k++) {
                    System.out.print(" " + a.individu[j][k]);
                }
                System.out.println("");
            }

            System.out.println("fitnes individu terpilih : ");
            for (int i = 0; i < a.populasi; i++) {
                a.fitness[i] = a.hitungFitness(a.individu[i]);
                System.out.println(i + " " + a.fitness[i]);

            }
        }
    }

}
