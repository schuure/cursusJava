package com.example.Kermis;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static com.example.Kermis.Main.pakje;

public class Main {
    static PakjeKaarten pakje = new PakjeKaarten();

    public static void main(String[] args) {
        System.out.println("Welkom bij BlackJack");
        System.out.println("Dit zijn alle kaarten:");
        pakje.printPakjeKaarten();
        Spel spel = new Spel();
        spel.spelerSpeelSpel();
        System.out.println("bedankt voor het spelen van BlackJack");
    }
}

class Bank {

}

class Spel {
    ArrayList<Kaart> gepakteKaarten = new ArrayList();
    ArrayList<Kaart> aanwezigeKaarten = pakje.creeerSetKaarten();
    Scanner scanner = new Scanner(System.in);
    int count = 0;
    int tussenstand = 0;
    int aantalGewonnen = 0;

    void spelerSpeelSpel() {
        if (count == 0) {
//            Kaart kaart1 = pakje.setKaarten.get(pakWillekeurigeKaart());
//            Kaart kaart2 = pakje.setKaarten.get(pakWillekeurigeKaart());
            Kaart kaart1 = new Kaart();
            Kaart kaart2 = new Kaart();
            if(gepakteKaarten.contains(kaart2)) {
                kaart2= new Kaart();
            }
            System.out.println(kaart1);
            System.out.println(kaart2);
            gepakteKaarten.add(kaart1);
            count++;
            tussenstand += punten();

            aanwezigeKaarten.remove(aanwezigeKaarten.indexOf(kaart1));

            gepakteKaarten.add(kaart2);
            count++;
            tussenstand += punten();
            aanwezigeKaarten.remove(aanwezigeKaarten.indexOf(kaart2));

            System.out.println("tussenstand in punten is " + tussenstand);
        }
        System.out.println("Wat wil je doen? Kaart pakken(k), passen (p) of stoppen (q)");
        String keuze = scanner.next();

        switch (keuze) {
            case "k":
//                Kaart kaart3 = pakje.setKaarten.get(pakWillekeurigeKaart());
                Kaart kaart3 = new Kaart();
                if (gepakteKaarten.contains(kaart3)) {
                    kaart3 = new Kaart();  //kan dit nog anders opgelost, want kans bestaat dat er nog een keer dezelfde kaart komt!
                }
                System.out.println(kaart3);
                gepakteKaarten.add(kaart3);
                System.out.println(gepakteKaarten);
                count++;
                aanwezigeKaarten.remove(aanwezigeKaarten.indexOf(kaart3));
                System.out.println(aanwezigeKaarten);
                tussenstand += punten();
                System.out.println("Tussenstand in punten is: " + tussenstand);
                if (controle(tussenstand) == true) {
                    spelerSpeelSpel();
                } else {
                    nogEenKeer();
                }
                break;
            default:
                System.out.println("stop spel");
                break;
            case "p":
                System.out.println("Je hebt: " + tussenstand + " punten");
                if (controle(tussenstand) == true) {
                    System.out.println("gefeliciteerd");
                    aantalGewonnen++;
                    System.out.println("Je hebt al " + aantalGewonnen + " keer gewonnen");
                }
                nogEenKeer();
        }
    }

    private boolean controle(int punten) {
        boolean goOn = true;
        if (punten > 21) {
            goOn = false;
            System.out.println("helaas, je hebt meer dan 21 punten. Het spelt stopt");
        }
        return goOn;
    }

    private void nogEenKeer() {
        System.out.println("Wil je nog een keer spelen? y of n");
        String yOrN = scanner.next();
        if (yOrN.equals("y")) {
            gepakteKaarten.clear();
            aanwezigeKaarten=pakje.creeerSetKaarten();
            count = 0;
            tussenstand = 0;
            spelerSpeelSpel();
        }
    }

    private int punten() {
        int punten = 0;
        String waarde = gepakteKaarten.get(count - 1).getWaarde();
        if (waarde.equals("2")) {
            punten += 2;
        } else if (waarde.equals("3")) {
            punten += 3;
        } else if (waarde.equals("4")) {
            punten += 4;
        } else if (waarde.equals("5")) {
            punten += 5;
        } else if (waarde.equals("6")) {
            punten += 6;
        } else if (waarde.equals("7")) {
            punten += 7;
        } else if (waarde.equals("8")) {
            punten += 8;
        } else if (waarde.equals("9")) {
            punten += 9;
        } else if (waarde.equals("10") | waarde.equals("B") | waarde.equals("V") | waarde.equals("H")) {
            punten += 10;
        } else if (waarde.equals("A")) {
            if ((tussenstand + 11) > 21) {
                punten += 1;
            } else {
                punten += 11;
            }
        }
        return punten;
    }

    private int pakWillekeurigeKaart() {
        Random r = new Random();
        int eenKaart = r.nextInt(51 - count);

        return eenKaart;
    }
}

class PakjeKaarten {

    ArrayList<Kaart> setKaarten;

    public PakjeKaarten() {
        setKaarten = creeerSetKaarten();
    }

    public ArrayList creeerSetKaarten() {
        ArrayList<Kaart> set = new ArrayList<>();
        String[] kleuren = new Kaart().kleuren;
        String[] waarde = new Kaart().waarden;
        for (int i = 0; i < kleuren.length; i++) {
            for (int j = 0; j < waarde.length; j++) {
                set.add(new Kaart(kleuren[i], waarde[j]));
            }
        }
        return set;
    }

    public void printPakjeKaarten() {
        ArrayList<Kaart> willekeurig = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < 52; i++) {
            Random r = new Random();
            int random = r.nextInt(52 - count);
            willekeurig.add(setKaarten.get(random));
            setKaarten.remove(random);
            count++;
        }
        System.out.println(willekeurig);

    }
}

class Kaart {
    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;
        if (object != null && object instanceof Kaart) {
            isEqual = ((this.getKleur() == ((Kaart) object).getKleur()) && (this.getWaarde() == ((Kaart) object).getWaarde()));
        }
        return isEqual;
    }

    private final String kleur;
    private final String waarde;
    int randomWaarde = setRandomWaarde();
    int randomKleur = setRandomKleur();

    String[] kleuren = { "S", "H", "K", "R" };
    String[] waarden = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "B", "V", "H", "A" };

    public Kaart(String kleur, String toevoeging) {
        this.kleur = kleur;
        this.waarde = toevoeging;
    }

    public String getKleur() {
        return kleur;
    }

    public Kaart() {

        this.kleur = kleuren[this.randomKleur];
        this.waarde = waarden[this.randomWaarde];
    }

    private int setRandomKleur() {
        Random r = new Random();
        int eenKleur = r.nextInt(3);
        return eenKleur;
    }

    private int setRandomWaarde() {
        Random r = new Random();
        int eenWaarde = r.nextInt(12);
        return eenWaarde;
    }

    public String getWaarde() {
        return waarde;
    }

    @Override
    public String toString() {
        return kleur + waarde;
    }

}
