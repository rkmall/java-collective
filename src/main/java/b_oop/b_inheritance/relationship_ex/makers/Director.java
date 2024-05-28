package b_oop.b_inheritance.relationship_ex.makers;

import java.time.LocalDate;

public class Director extends Maker {
    private LocalDate debutDate;

    public Director(String name, LocalDate debutDate) {
        super(name);
        this.debutDate = debutDate;
    }

    public LocalDate getDebutDate() {
        return debutDate;
    }

    @Override
    public String toString() {
        return "Director: " + super.getName() +
                ", DebutDate: " + debutDate;
    }
}
