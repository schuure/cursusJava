package com.example.Kermis;


import java.util.Scanner;

public class Bank {

    double balans = 100.0;
    int count= 0;
    boolean pasgeblokkeerd = false;

    public static void main(String[] args) {
        System.out.println("Welkom bij de bank");
        Mens klant = new Mens(10);
        Bank abn = new Bank();
        abn.geldOpnemen(klant);
        System.out.println("de bank is gesloten");
    }

    void geldOpnemen(Mens klant) {
        if (controleerBalans() &&  !pasgeblokkeerd ) {
            System.out.println("U wilt geld opnemen. Wat is uw pincode?");
            Scanner scanner = new Scanner(System.in);
            int pincode = scanner.nextInt();

            if (controleerPincode(pincode)) {
                System.out.println("Uw pincode is correct, hoeveel geld wilt u opnemen?");
                double opname = scanner.nextDouble();
                if (this.balans - opname > 0) {
                    this.balans -= opname;
                    System.out.println(balans);
                    klant.portomonnee += opname;
                    geldOpnemen(klant);
                } else {
                    System.out.println("de bank is failliet en u kunt weer naar huis");
                    pasgeblokkeerd=true;
                }
            } geldOpnemen(klant);
        }
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
