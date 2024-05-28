package b_oop.b_inheritance.relationship_ex;

import b_oop.b_inheritance.relationship_ex.items.Album;
import b_oop.b_inheritance.relationship_ex.items.MediaItem;
import b_oop.b_inheritance.relationship_ex.items.Movie;
import b_oop.b_inheritance.relationship_ex.makers.Artist;
import b_oop.b_inheritance.relationship_ex.makers.Director;
import b_oop.b_inheritance.relationship_ex.makers.Maker;

import java.time.LocalDate;

public class OrderDriver {

    public static void main(String[] args) {

        Account acc1 = new Account(1, "jim@in.com", 50.50);

        MediaItem alb1 = new Album(10,
                "Album",
                56.89,
                true,
                12.0,
                new Artist("AC/DC", "Hells Bells"),
                "AC/DC Vol I",
                10);

        MediaItem mov1 = new Movie(112,
                "Movie",
                150.50,
                false,
                14.0,
                 new Director("Warner", LocalDate.now()),
                "Jaws");

        System.out.println(alb1.getMaker());    // calls appropriate version of maker
        System.out.println(mov1.getMaker());    // calls appropriate version of maker

        Order orderAcc1 = new Order(12);
        orderAcc1.addItems(alb1, acc1);
        orderAcc1.addItems(mov1, acc1);
        orderAcc1.showItems();
        System.out.println(orderAcc1);
    }
}
