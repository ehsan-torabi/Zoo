import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class demo {

    public static void main(String[] args) {
        Animal a = new Mouse();
        Animal b = new Cat();
        Animal c = new Cat();
        Animal g = new Snake();
        b.goToZoo();
        c.goToZoo();
        g.goToZoo();
        Manager.addAnimal(new Cat());
        Manager.addAnimal(a);
        Manager.addAnimal("plant", 1);
        System.out.println(a.getHuntingStatus());
        Manager.getReport();

    }

}

class Manager {
    public static void addAnimal(String animal, int count) {
        String animalNameLCase = animal.toLowerCase();
        if (animalNameLCase.equals("cat")) {
            for (int i = 0; i < count; i++) {
                Animal.liveAnimals.add(new Cat());
            }
        } else if (animalNameLCase.equals("dog")) {
            for (int i = 0; i < count; i++) {
                Animal.liveAnimals.add(new Dog());
            }
        } else if (animalNameLCase.equals("lion")) {
            for (int i = 0; i < count; i++) {
                Animal.liveAnimals.add(new Lion());
            }
        } else if (animalNameLCase.equals("snake")) {
            for (int i = 0; i < count; i++) {
                Animal.liveAnimals.add(new Snake());
            }
        } else if (animalNameLCase.equals("mouse")) {
            for (int i = 0; i < count; i++) {
                Animal.liveAnimals.add(new Mouse());
            }
        } else if (animalNameLCase.equals("plant")) {
            for (int i = 0; i < count; i++) {
                Animal.liveAnimals.add(new Plant());
            }
        } else {
            System.out.println("Please enter a valid name |:");
        }

    }

    public static void addAnimal(Animal animal) {
        Animal.liveAnimals.add(animal);
    }

    public static void setLiveAnimal(Animal animal, boolean status) {
        animal.setLiveStatus(status);
    }

    public static void setHuntReport(Animal hunter, Animal victim) {
        victim.dieByHunting(hunter);
    }

    public static Animal findAnimal(int animalID) {
        for (Animal animal : Animal.liveAnimals) {
            if (animal.getAnimalID() == animalID) {
                return animal;
            }
        }
        for (Animal animal : Animal.deadAnimals) {
            if (animal.getAnimalID() == animalID) {
                return animal;
            }
        }
        System.out.printf("Not found animal with ID : %d ):", animalID);
        return null;
    }

    public static void findAnimal(Boolean liveStatus) {

        if (liveStatus) {
            if (Animal.liveAnimals.size() == 0) {
                System.out.println("Not found live animal ):");
                return;
            }
            for (Animal animal : Animal.liveAnimals) {
                System.out.println("\s" + animal);
            }
        }

        else {
            if (Animal.deadAnimals.size() == 0) {

                System.out.println("Not found dead animal ):");

                return;
            }
            for (Animal animal : Animal.deadAnimals) {
                System.out.println("\s" + animal);

            }
        }

    }

    public static void getReport() {
        int catCount = 0, dogCount = 0, lionCount = 0, snakeCount = 0, mouseCount = 0;
        for (Animal animal : Animal.liveAnimals) {
            if (animal instanceof Cat)
                catCount++;
            else if (animal instanceof Dog)
                dogCount++;
            else if (animal instanceof Lion)
                lionCount++;
            else if (animal instanceof Snake)
                snakeCount++;
            else if (animal instanceof Mouse)
                mouseCount++;
        }
        System.out.println("-------------------------------------------");
        System.out.printf("Animal Count:\n \sCat: %d \n \sDog: %d \n \sLion: %d \n \sSnake: %d \n \sMouse: %d \n",
                catCount, dogCount, lionCount, snakeCount, mouseCount);
        System.out.println("-------------------------------------------");
        System.out.println("Live Animals :");
        findAnimal(true);
        System.out.println("Dead Animals :");
        findAnimal(false);
        System.out.println("-------------------------------------------");

    }

}

abstract class Animal {
    static List<Animal> liveAnimals = new ArrayList<Animal>();
    static List<Animal> deadAnimals = new ArrayList<Animal>();
    private String huntingStatus;
    private int animalID;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private LocalDateTime now = LocalDateTime.now();
    private boolean isAlive = true;

    {
        this.animalID = (int) (Math.random() * 10) + 100 + (int) (Math.random() * 100);
    }

    abstract protected void eatAnimal(Animal victim);

    void dieByHunting(Animal hunter) {
        isAlive = false;
        liveAnimals.remove(liveAnimals.indexOf(this));
        deadAnimals.add(this);
        String hunterName = hunter.getClass().getSimpleName();
        int hunterID = hunter.getAnimalID();
        String victimName = this.getClass().getSimpleName();
        int victimID = this.getAnimalID();

        huntingStatus = String.format("%s ID: %d in %s by %s ID: %d Hunted |:", victimName, victimID,
                dtf.format(now),
                hunterName,
                hunterID);

    }

    public void setLiveStatus(boolean status) {
        isAlive = status;

    }

    public boolean getLiveStatus() {
        return isAlive;

    }

    public int getAnimalID() {
        return animalID;

    }

    public void goToZoo() {
        liveAnimals.add(this);
    }

    public String getHuntingStatus() {
        return getLiveStatus() ? "This animal is health (: " : huntingStatus;
    }

    @Override
    public String toString() {
        if (getLiveStatus())
            return String.format("Type : %s - ID : %d - Status : Live", this.getClass().getSimpleName(),
                    getAnimalID());
        else
            return String.format("Type : %s - ID : %d - Status : ( %s )", this.getClass().getSimpleName(),
                    getAnimalID(),
                    getHuntingStatus());
    }

}

class Cat extends Animal {
    @Override
    protected void eatAnimal(Animal victim) {
        if (victim.getLiveStatus() == true) {
            if (victim instanceof Mouse || victim instanceof Snake)
                victim.dieByHunting(this);
            else
                System.out.println("Not possible |:");
        } else {
            victim.dieByHunting(this);
        }
    }

}

class Dog extends Animal {
    @Override
    protected void eatAnimal(Animal victim) {
        if (victim.getLiveStatus() == true) {
            if (victim instanceof Cat)
                victim.dieByHunting(this);
            else
                System.out.println("Not possible |:");
        } else {
            victim.dieByHunting(this);
        }

    }

}

class Snake extends Animal {

    @Override
    protected void eatAnimal(Animal victim) {
        if (victim.getLiveStatus() == true) {
            if (victim instanceof Mouse)
                victim.dieByHunting(this);
            else
                System.out.println("Not possible |:");
        } else {
            victim.dieByHunting(this);
        }

    }

}

class Lion extends Animal {

    @Override
    protected void eatAnimal(Animal victim) {
        victim.dieByHunting(this);

    }

}

class Mouse extends Animal {

    @Override
    protected void eatAnimal(Animal victim) {
        if (victim.getLiveStatus() == true) {
            if (victim instanceof Plant)
                victim.dieByHunting(this);
            else
                System.out.println("Not possible |:");
        } else {
            victim.dieByHunting(this);
        }

    }

}

class Plant extends Animal {

    @Override
    protected void eatAnimal(Animal victim) {
        System.out.println("Plant not eat <:");

    }

}