import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class demo {

    public static void main(String[] args) {
        Animal mouse = new Mouse();
        Animal cat1 = new Cat();
        Animal cat2 = new Cat();
        Animal snake = new Snake();
        Animal plant = new Plant();
        // Manager.addAnimal("plant", 1);
        Manager.setLiveAnimal(mouse, false);
        mouse.eatAnimal(Manager.findAnimal(plant.getAnimalID()));
        System.out.println(mouse.getHuntingStatus());
        Manager.getFullReport();

    }

}

/*
 * The manager class defines the zoo management section and all its methods are
 * static defined
 */
class Manager {

    /*
     * addAnimal method with two parameters animal and count:
     * With a condition, it checks the name of the sent animal and if it is
     * accepted, adds it to the list of zoo animals(in class block initializer) by
     * count and if animal not valid
     * print error massage
     * animal : String
     * count : int
     */
    public static void addAnimal(String animal, int count) {

        if (animal.equalsIgnoreCase("cat")) {
            for (int i = 0; i < count; i++) {
                new Cat();
            }
        } else if (animal.equalsIgnoreCase("dog")) {
            for (int i = 0; i < count; i++) {
                new Dog();
            }
        } else if (animal.equalsIgnoreCase("lion")) {
            for (int i = 0; i < count; i++) {
                new Lion();
            }
        } else if (animal.equalsIgnoreCase("snake")) {
            for (int i = 0; i < count; i++) {
                new Snake();
            }
        } else if (animal.equalsIgnoreCase("mouse")) {
            for (int i = 0; i < count; i++) {
                new Mouse();
            }
        } else if (animal.equalsIgnoreCase("plant")) {
            for (int i = 0; i < count; i++) {
                new Plant();
            }
        } else {
            System.out.println("Please enter a valid name |:");
        }

    }

    /*
     * The setLiveAnimal method with two parameter animal and status :
     * call setLiveStatus method in animal parameter and set live status to animal
     * parameter.
     * animal : Animal
     * status : bolean
     */
    public static void setLiveAnimal(Animal animal, boolean status) {
        animal.setLiveStatus(status);
    }

    /*
     * The setHuntReport method with two parameter hunter and victim :
     * call sdieByHunting method in victim parameter and submit hunt victim by
     * hunter.
     * hunter : Animal
     * victim : Animal
     */
    public static void setHuntReport(Animal hunter, Animal victim) {
        victim.dieByHunting(hunter);
    }

    /*
     * The findAnimal method with an animalID parameter :
     * itrate to Animal.liveAnimals for find and print live animal with animalID
     * property == animalID parameter.
     * itrate to Animal.deadAnimals for find and print dead animal with animalID
     * property == animalID parameter.
     * animalID : int
     * return : if found Animal object , if not found print error massage and
     * null
     */
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

    /*
     * The findAnimal method with an animalID parameter :
     * if liveStatus true : itrate to Animal.liveAnimals for find and print live
     * animals with status live
     * if liveStatus false : itrate to Animal.deadAnimals for find and print live
     * animals with status dead
     * if not found print error massage
     * liveStatus : boolean
     */
    public static void findAnimal(Boolean liveStatus) {

        if (liveStatus) {
            if (Animal.liveAnimals.size() == 0) {
                System.out.println("Not found live animal ):");
                return;
            }
            for (Animal animal : Animal.liveAnimals) {
                System.out.println(" " + animal);
            }
        }

        else {
            if (Animal.deadAnimals.size() == 0) {

                System.out.println("Not found dead animal ):");
                return;
            }
            for (Animal animal : Animal.deadAnimals) {
                System.out.println(" " + animal);

            }
        }

    }

    public static void getFullReport() {
        int catCount = 0, dogCount = 0, lionCount = 0, snakeCount = 0, mouseCount = 0, plantCount = 0;
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
            else if (animal instanceof Plant)
                plantCount++;
        }
        System.out.println("-------------------------------------------");
        System.out.printf(
                "Animal Count:\n  Cat: %d \n  Dog: %d \n  Lion: %d \n  Snake: %d \n  Mouse: %d \n  Plant: %d \n \tTotal: %d\n",
                catCount, dogCount, lionCount, snakeCount, mouseCount, plantCount, Animal.liveAnimals.size());
        System.out.println("-------------------------------------------");
        System.out.println("Live Animals :");
        findAnimal(true);
        System.out.println("-------------------------------------------");
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
    private boolean isAlive = true;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private LocalDateTime now = LocalDateTime.now();

    {
        this.animalID = (int) (Math.random() * 10) + 100 + (int) (Math.random() * 100);
        liveAnimals.add(this);
    }

    abstract protected void eatAnimal(Animal victim);

    void dieByHunting(Animal hunter) {
        setLiveStatus(false);
        String hunterName = hunter.getClass().getSimpleName();
        int hunterID = hunter.getAnimalID();
        String victimName = this.getClass().getSimpleName();
        int victimID = this.getAnimalID();

        setHuntingStatus(String.format("The %s with ID: %d eat the %s with ID: %d in %s ):", hunterName, hunterID,
                victimName,
                victimID,
                dtf.format(now)));

    }

    // setLiveStatus method is a setter for isAlive property
    public void setLiveStatus(boolean status) {
        isAlive = status;
        if (status == false) {
            liveAnimals.remove(liveAnimals.indexOf(this));
            deadAnimals.add(this);
            setHuntingStatus("This animal is dead ): ");
        }

    }

    // getLiveStatus method is a getter for isAlive property
    public boolean getLiveStatus() {
        return isAlive;

    }

    // getAnimalID method is a getter for animalID property
    public int getAnimalID() {
        return animalID;

    }

    public String getHuntingStatus() {
        return getLiveStatus() ? "This animal is health (: " : huntingStatus;
    }

    public void setHuntingStatus(String reason) {
        huntingStatus = reason;
    }

    @Override
    public String toString() {
        String objectName = this.getClass().getSimpleName();
        if (getLiveStatus())
            return String.format("Animal type : %s - ID : %d - Status : Live", objectName,
                    getAnimalID());
        else
            return String.format("Animal type : %s - ID : %d - Status : ( %s )", objectName,
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
                System.out.println("Impossible |:");
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
                System.out.println("Impossible |:");
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
                System.out.println("Impossible |:");
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
                System.out.println("Impossible |:");
        } else {
            victim.dieByHunting(this);
        }

    }

}

class Plant extends Animal {

    @Override
    protected void eatAnimal(Animal victim) {
        System.out.println("The plant does not eat anything <:");

    }

}
