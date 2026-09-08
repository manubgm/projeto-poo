package jogodamemoria.main;

import jogodamemoria.model.Carta;
import jogodamemoria.util.SpriteSheet;

public class TesteCarta {

    public static void main(String[] args) {

        SpriteSheet sheet = new SpriteSheet();

        Carta carta = new Carta(
                1,
                sheet.getSprite(0, 0, 96, 144),
                sheet.getSprite(0, 5, 96, 144)
        );

        System.out.println("ID: " + carta.getId());

        System.out.println("Virada? " + carta.isVirada());

        carta.virar();

        System.out.println("Virada após virar()? " + carta.isVirada());

        carta.esconder();

        System.out.println("Virada após esconder()? " + carta.isVirada());

    }
}