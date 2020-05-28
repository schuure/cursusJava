import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Weekopdracht {

    public static void main(String[] args) {
        YathzeeSpel yathzeeSpel = new YathzeeSpel();
        yathzeeSpel.spelen();
        System.out.println("Bedankt voor het spelen");
    }
}

class YathzeeSpel {
    static Speler speler = new Speler();
    static ArrayList<Dobbelsteen> lijstDobbelstenen = new ArrayList<>();
    int[] blokkeerArray = { 0, 0, 0, 0, 0 };

    public YathzeeSpel() {
        for (int i = 1; i < 6; i++) {
            Dobbelsteen dobbelsteen = new Dobbelsteen();
            lijstDobbelstenen.add(dobbelsteen);
        }
    }

    public void spelen() {

        for (; true; ) {
            System.out.println("Voer q in om te stoppen, elke andere toets om te gooien");

            Scanner scanner = new Scanner(System.in);
            String invoer = scanner.next();

            if (invoer.equals("q")) {
                System.out.println("jammer");
                break;
            } else {
                worpCount(1);
                System.out.println("12345");
                for (Dobbelsteen dobbelsteen : lijstDobbelstenen) {
                    dobbelsteen.waarde = dobbelsteen.werpen();
                }
                vasthouden();
                worpCount(2);
                verder();
                vasthouden();
                worpCount(3);
                verder();
                System.out.println("\n bedankt voor het spelen");
                Worp worp = new Worp();
                worp.worpUitslag();
                System.out.println(YathzeeSpel.speler.worpHistory);
            }
        }
    }

    void worpCount(int count) {
        System.out.println("worp" + count);
    }

    void vasthouden() {
        Scanner scanner = new Scanner(System.in);
        int plek;
        System.out.println("");
        System.out.println("Welke posities wilt u vasthouden? 0 voor geen, anders bv 124");
        String parse = scanner.next();
        leegmaken();
        if (parse.equals("0")) {
        } else {
            for (int i = 0; i < parse.length(); i++) {
                String positie = parse.substring(i, i + 1);
                plek = Integer.parseInt(positie) - 1;
                this.blokkeerArray[plek] = 1;
            }
        }
    }

    void leegmaken() {
        blokkeerArray = new int[]{ 0, 0, 0, 0, 0 };
    }

    void verder() {
        System.out.println("12345");
        for (int i = 0; i < blokkeerArray.length; i++) {
            if (blokkeerArray[i] == 0) {
                lijstDobbelstenen.get(i).waarde = lijstDobbelstenen.get(i).werpen();
            } else {
                System.out.print(lijstDobbelstenen.get(i).waarde);
            }
        }
    }
}

class Dobbelsteen {

    int waarde;

    public int werpen() {
        Random r = new Random();
        waarde = r.nextInt(6) + 1;
        System.out.print(waarde);
        return waarde;
    }
}

class Worp {

    int[] arrayWorp = new int[5];

    public void worpUitslag() {
        Worp worp = new Worp();
        for (int i = 0; i < 5; i++) {
            worp.arrayWorp[i] = YathzeeSpel.lijstDobbelstenen.get(i).waarde;
        }
        System.out.println(Arrays.toString(worp.arrayWorp));
        YathzeeSpel.speler.worpHistory.add(worp);
    }

    public String toString(){
        return (Arrays.toString(arrayWorp)+"");
    }
}

class Speler {

    ArrayList<Worp> worpHistory = new ArrayList<>();
}
