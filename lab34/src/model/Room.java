package model;

import exceptions.IllegalInteractionException;
import records.Explosion;
import exceptions.AlreadyDestroyedException;
import model.roomobjects.*;
import model.persons.*;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String name;
    private final List<Person> characters;
    private final List<RoomObject> objects;

    // Ссылки на конкретные объекты
    private final Bed bedForZnaika;
    private final Bed bedForZvezdochkin;
    private final Blanket blanket;
    private final Walls walls;
    private final GlassWindow glassWindow;
    private final Electricity electricity;
    private final Ceiling ceiling;

    private final Znaika znaika;
    private final Zvezdochkin zvezdochkin;

    public Room(String name) {
        this.name = name;
        this.characters = new ArrayList<>();
        this.objects = new ArrayList<>();

        this.bedForZnaika = new Bed("Кровать Знайки");
        this.bedForZvezdochkin = new Bed("Кровать профессора Звёздочкина");
        this.blanket = new Blanket();
        this.walls = new Walls();
        this.glassWindow = new GlassWindow();
        this.electricity = new Electricity();
        this.ceiling = new Ceiling();

        this.znaika = new Znaika();
        this.zvezdochkin = new Zvezdochkin();

        objects.add(bedForZnaika);
        objects.add(bedForZvezdochkin);
        objects.add(blanket);
        objects.add(walls);
        objects.add(glassWindow);
        objects.add(electricity);
        objects.add(ceiling);

        characters.add(znaika);
        characters.add(zvezdochkin);
    }

    public void executeStory() {

        System.out.println("Комната: " + name);
        System.out.println("Персонажей: " + characters.size());
        System.out.println("Объектов: " + objects.size());
        System.out.println("-----");

        System.out.println("Уже было далеко за полночь...");

        Calculations calcZnaika = new Calculations("Масса");
        Calculations calcZvezdochkin = new Calculations("Ускорение");

        znaika.calculate(calcZnaika);
        zvezdochkin.calculate(calcZvezdochkin);

        electricity.turnOff(znaika);

        bedForZnaika.lieDown(znaika);
        bedForZvezdochkin.lieDown(zvezdochkin);

        blanket.take(znaika);

        Explosion explosion = new Explosion(calcZnaika.getResult() * calcZvezdochkin.getResult());
        try {
            simulateExplosion(explosion);
        } catch (AlreadyDestroyedException e) {
            System.out.println(e.getMessage());
        }

        try {
            blanket.take(znaika);
        } catch (IllegalInteractionException e) {
            System.out.println(e.getMessage());
        }

        printResults();

        Explosion explosionSmall = new Explosion(5);
        try {
            simulateExplosion(explosionSmall);
        } catch (AlreadyDestroyedException e) {
            System.out.println(e.getMessage());
        }

        printResults();

    }

    private void simulateExplosion(Explosion explosion) throws AlreadyDestroyedException {
        System.out.println("Как раз в это время раздался ВЗРЫВ!!!");
        for (RoomObject obj : objects) {
            try {
                obj.takeDamage(explosion.getPower());
            } catch (AlreadyDestroyedException e) {
                System.out.println(e.getMessage());
            }
        }

        for (Person person : characters) {
            try {
                person.takeDamage(explosion.getPower());
            } catch (AlreadyDestroyedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printResults() {
        System.out.println("\nРЕЗУЛЬТАТЫ:");
        System.out.println("Стены: " + walls.toString());
        System.out.println("Окно: " + glassWindow.toString());
        System.out.println("Потолок: " + ceiling.toString());
        System.out.println("Кровать Знайки: " + bedForZnaika.toString());
        System.out.println("Кровать Звёздочкина: " + bedForZvezdochkin.toString());
        System.out.println("Одеяло: " + blanket.toString());
        System.out.println("Знайка: " + znaika.getState() +
                ", здоровье: " + String.format("%.1f", znaika.getHealth().getCurrent()));
        System.out.println("Звездочкин: " + zvezdochkin.getState() +
                ", здоровье: " + String.format("%.1f", zvezdochkin.getHealth().getCurrent()));
        System.out.println("\n");
    }
}