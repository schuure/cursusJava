package com.example.Kermis;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Keuken {

    Ei ei = new Ei();
    Bakpan pan = new Bakpan();
    boolean goOn = true;
    static Eierdoos blijeEieren = new Eierdoos();

    public static void main(String[] args) {
        System.out.println("\u039F\u039F\u039F\u039F");// dit moet een ei voorstellen :)

        Keuken keuken = new Keuken();
        blijeEieren.creeerEierdoos();
        keuken.vraagStellen();
        System.out.println("De eieren zijn op of de pan is verbrand");
    }

    private void controleTemp() {
        if (pan.teHeet == true) {
            goOn = false;
        }
    }

    void vraagStellen() {
        if (goOn && blijeEieren.eieren.size() > 0) {
            System.out.println("wat wil je doen? bakken of vuur ophogen?");
            Scanner scanner = new Scanner(System.in);
            String invoer = scanner.next();
            if (invoer.equals("bakken")) {
                System.out.println("Hoeveel eieren wil je bakken?");
                int aantal = scanner.nextInt();
                if (aantal == 1) {

                    if (blijeEieren.eieren.size() > 0) {
                        pan.bakkenEenEi(ei);
                        blijeEieren.verwijderEi();
                    } else {
                        goOn = false;
                    }
                } else {
                    if (blijeEieren.eieren.size() >= aantal) {
                        pan.bakkenMeerEieren(aantal);
                        for (int i = 0; i < aantal; i++) {
                            blijeEieren.eieren.remove(0);
                        }
                        System.out.println(blijeEieren.eieren.size());
                    } else {
                        System.out.println("er zijn niet genoeg eieren");
                    }
                }
                vraagStellen();
            } else if (invoer.equals("ophogen")) {
                pan.ophogen();
                controleTemp();
                vraagStellen();
            } else {
                System.out.println("dit is een ongeldige keuze");
                vraagStellen();
            }
        }
    }


}

class Ei {
    public Ei() {
    }
}

class Bakpan {
    boolean teHeet = false;
    int temperatuur = 100;

    void bakkenEenEi(Ei blijEi) {

        if (temperatuur < 140) {
            System.out.println("De temperatuur is nu : " + temperatuur);
            System.out.println("Het is te koud om te bakken");
        } else if (temperatuur > 195) {
            System.out.println("De temperatuur is nu : " + temperatuur);
            System.out.println("De pan is te heet om te bakken");
        } else {
            System.out.println("Eitje bakken!");
        }
    }

    void bakkenMeerEieren(int aantal) {
        if (aantal >= 6) {
            System.out.println("Zoveel eieren zitten er niet in de doos");
        } else {
            Ei ei = new Ei();
            bakkenEenEi(ei);
        }
    }

    int ophogen() {
        Random r = new Random();
        int ophoging = r.nextInt(20) + 11;
        temperatuur += ophoging;
        System.out.println("de temperatuur is nu " + (temperatuur));
        if (temperatuur > 195) {
            teHeet = true;
        }
        return (temperatuur + ophoging);
    }
}

class Eierdoos {
    ArrayList<Ei> eieren = new ArrayList<>();

    void creeerEierdoos() {
        for (int i = 0; i < 6; i++) {
            eieren.add(new Ei());
        }
    }

    void verwijderEi() {
        eieren.remove(0);
    }
}

