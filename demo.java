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
        Manager.setLiveAnimal(mouse, false);
        mouse.eatAnimal(Manager.findAnimal(plant.getAnimalID()));
        System.out.println(mouse.getLivingStatus());
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
     * call setIsLive method in animal parameter and set live status to animal
     * parameter.
     * animal : Animal
     * status : bolean
     */
    public static void setLiveAnimal(Animal animal, boolean status) {
        animal.setIsLive(status);
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

    /*
     * The getFullReport defines for print full report:
     * first print animal counts and get Animals.liveAnimals.size() for total live
     * animals.secend print each animal with full informatins.
     */
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
    // define a static ArrayList for manage live animals
    static List<Animal> liveAnimals = new ArrayList<Animal>();
    // define a static ArrayList for manage live animals
    static List<Animal> deadAnimals = new ArrayList<Animal>();
    // livingStatus is a massage contains live or dead status
    private String livingStatus;
    // AnimalID is a primary key for an object as Animal class
    private int animalID;
    // isAlive a variable to manage dead or live animal status. default true(live)
    private boolean isAlive = true;
    // dtf is a time formatter for customize date and time format printing
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    { // Generate nearly random ID
        this.animalID = (int) (Math.random() * 10) + 100 + (int) (Math.random() * 100);
        // add to live Animals list in first object creation(Animal.liveAnimals)
        liveAnimals.add(this);
    }

    // eat is a abstract method because different animals have different eat
    // behavior
    abstract protected void eatAnimal(Animal victim);

    /*
     * dieByHunting method call when this animal eat by the other animals
     * hunter : Animal
     */
    void dieByHunting(Animal hunter) {
        // call setIsLive setter and set false status
        setIsLive(false);
        // get hunter object class name
        String hunterName = hunter.getClass().getSimpleName();
        // get hunter object animalID
        int hunterID = hunter.getAnimalID();
        // get this object class name
        String victimName = this.getClass().getSimpleName();
        // get this object animalID
        int victimID = this.getAnimalID();
        // call setLivingStatus and send formated String with hunter name , hunter ID ,
        // victim name ,victim ID and date of eat.
        setLivingStatus(String.format("The %s with ID: %d eat the %s with ID: %d in %s ):", hunterName, hunterID,
                victimName,
                victimID,
                dtf.format(LocalDateTime.now())));

    }

    /*
     * setIsLive method is a setter for isAlive property
     * if status false : remove this animal as liveAnimals list and add to
     * deadAnimals list. also call setLivingStatus and set reason of dead to
     * "This animal is dead ):"
     */
    public void setIsLive(boolean status) {
        isAlive = status;
        if (status == false) {
            liveAnimals.remove(liveAnimals.indexOf(this));
            deadAnimals.add(this);
            setLivingStatus("This animal is dead ): ");
        }

    }

    // isAlive method is a getter for isAlive property
    public boolean isAlive() {
        return isAlive;

    }

    // getAnimalID method is a getter for animalID property
    public int getAnimalID() {
        return animalID;

    }

    // getLivingStatus is a getter for living status property
    // return if isAlive() true "This animal is health (: " , else livingStatus.
    public String getLivingStatus() {
        return isAlive() ? "This animal is health (: " : livingStatus;
    }

    // setLivingStatus method is a setter for reason property
    public void setLivingStatus(String reason) {
        livingStatus = reason;
    }

    @Override
    public String toString() {
        // get object Class name
        String objectName = this.getClass().getSimpleName();
        /*
         * if this.isAlive() true : return formated string with this form : this object
         * class name , this object animalID ,Live
         * if this.isAlive() false : retuen formated string with this form : this object
         * name , this object animalID , this object livingStatus.
         */
        if (isAlive())
            return String.format("Animal type : %s - ID : %d - Status : Live", objectName,
                    getAnimalID());
        else
            return String.format("Animal type : %s - ID : %d - Status : ( %s )", objectName,
                    getAnimalID(),
                    getLivingStatus());
    }

}

// Create Cat type as animal
class Cat extends Animal {
    @Override
    // impeliment eatAnimal behavior ; check isAlive this and if victim is a Mouse
    // or if Snake call or Snake victim.dieByHunting method. else print error
    // massage.
    protected void eatAnimal(Animal victim) {
        if (victim.isAlive() == true) {
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
    // impeliment eatAnimal behavior ; check isAlive this and if victim is a Cat
    // call or Snake victim.dieByHunting method. else print error massage.
    protected void eatAnimal(Animal victim) {
        if (victim.isAlive() == true) {
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
    // impeliment eatAnimal behavior ; check isAlive this and if victim is a Mouse
    // call or Snake victim.dieByHunting method. else print error massage.
    protected void eatAnimal(Animal victim) {
        if (victim.isAlive() == true) {
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
    // impeliment eatAnimal behavior ; call victim.dieByHunting method.
    protected void eatAnimal(Animal victim) {
        victim.dieByHunting(this);

    }

}

class Mouse extends Animal {

    @Override
    // impeliment eatAnimal behavior ; check isAlive this and if victim is a Plant
    // call or Snake victim.dieByHunting method. else print error massage.
    protected void eatAnimal(Animal victim) {
        if (victim.isAlive() == true) {
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
    // impeliment eatAnimal behavior ; print error massage. Because Plant not eat
    // anything.
    protected void eatAnimal(Animal victim) {
        System.out.println("The plant does not eat anything <:");

    }

}
