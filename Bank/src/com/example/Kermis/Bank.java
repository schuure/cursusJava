package com.example.Kermis;


import java.util.Scanner;

public class Bank {

    double balans = 100.0;
    int count = 0;
    boolean pasgeblokkeerd = false;
    Scanner scanner = new Scanner(System.in);
    Mens klant = new Mens(10);

    public static void main(String[] args) {
        System.out.println("Welkom bij de bank");
        Bank abn = new Bank();
        abn.vraagStellen();
        System.out.println("de bank is gesloten");
    }

    void vraagStellen() {

        if (!pasgeblokkeerd) {
            System.out.println("Wat wilt u doen? opnemen of storten?");
            String invoer = scanner.next();

            if (invoer.equals("opnemen")) {
                geldOpnemen(klant);
            } else if (invoer.equals("storten")) {
                storten(klant);
            } else {
                System.out.println("dit is een onjuiste keuze");
                vraagStellen();
            }
        }
    }

    void geldOpnemen(Mens klant) {
        if (controleerBalans() && !pasgeblokkeerd) {
            System.out.println("U wilt geld opnemen. Wat is uw pincode?");
            int pincode = scanner.nextInt();

            if (controleerPincode(pincode)) {
                System.out.println("Uw pincode is correct, hoeveel geld wilt u opnemen?");
                double opname = scanner.nextDouble();
                if (this.balans - opname > 0) {
                    this.balans -= opname;
                    String balansText = String.format("%.2f", balans);
                    System.out.println("De bank heeft nog :" + balansText);
                    klant.portomonnee += opname;
                    String portomonneeText= String.format("%.2f", klant.portomonnee);
                    System.out.println("Uw portomonnee bevat nu " + portomonneeText);
                    vraagStellen();
                } else {
                    System.out.println("de bank is failliet en u kunt weer naar huis");
                    pasgeblokkeerd = true;
                }
            }
            vraagStellen();
        }
    }

    void storten(Mens klant) {
        System.out.println("Hoeveel geld wilt u storten?");
        double storting = scanner.nextDouble();
        if (klant.portomonnee - storting >= 0) {
            this.balans += storting;
            String balansText = String.format("%.2f", balans);
            System.out.println("De bank heeft nu: " + balansText);
            klant.portomonnee -= storting;
            String portomonneeText = String.format("%.2f", klant.portomonnee);
            System.out.println("Uw portomonnee bevat nu " + portomonneeText);
        } else {
            System.out.println("U heeft maar " + klant.portomonnee + " in uw portomonnee, de storting gaat niet door");
        }
        vraagStellen();
    }

    boolean controleerPincode(int code) {
        if (count < 2) {
            if (code == 1234) {
                return true;
            }
            count++;
            System.out.println("uw pincode is fout");
            return false;
        } else {
            System.out.println("uw pas is geblokkeerd");
            pasgeblokkeerd = true;
        }
        return false;
    }

    boolean controleerBalans() {
        if (this.balans > 0) {
            return true;
        }
        return false;
    }
}

class Mens {
    double portomonnee;

    public Mens(double portomonnee) {
        this.portomonnee = portomonnee;
    }
}
