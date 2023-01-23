import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.time.LocalDateTime;

public class demo {

    public static void main(String[] args) {
        printMenu();
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        doOption(input);

    }

    public static void printMenu() {
        System.out.println("-------------------------------------------------");
        System.out.println("Welcome to zoo");
        System.out.println(" 1 - Add animal");
        System.out.println(" 2 - Print zoo full report");
        System.out.println(" 3 - Find animal by ID");
        System.out.println(" 4 - Print live animals");
        System.out.println(" 5 - Print dead animals");
        System.out.println(" 6 - Registration of hunting report");
        System.out.println(" 7 - Registration of live status");
        System.out.println(" 8 - Export zoo full report");
        System.out.println(" 0 - Exit");
        System.out.println("-------------------------------------------------");
        System.out.print("Please enter option number: ");
    }

    public static void doOption(int value) {
        while (true) {
            switch (value) {
                case 0:
                    System.exit(0);
                case 1:
                    menu_addAnimal();
                    break;
                case 2:
                    Manager.getFullReport();
                    break;
                case 3:
                    menu_findAnimalByID();
                    break;
                case 4:
                    System.out.println("\nLive animals: ");
                    Manager.findAnimal(true);
                    break;
                case 5:
                    System.out.println("\nDead animals: ");
                    Manager.findAnimal(false);
                    break;
                case 6:
                    menu_registerHuntReport();
                    break;
                case 7:
                    menu_registerLiveStatus();
                    break;
                case 8:
                    menu_exportFullReport();
                    break;

            }
            break;
        }
        System.out.println("-------------------------------------------------");
        System.out.println("0 - MainMenu\n1 - Continue");
        System.out.println("-------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        int close = scanner.nextInt();
        switch (close) {
            case 0:
                main(null);
                break;
            case 1:
                doOption(value);
                break;
        }

    }

    public static void menu_addAnimal() {
        System.out.println("\n1 - Cat\n2 - Dog\n3 - Lion\n4 - Snake\n5 - Mouse\n6 - Plant");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter animal number : ");
        int choice = scanner.nextInt();
        String animal;
        switch (choice) {
            case 1:
                animal = "Cat";
                break;
            case 2:
                animal = "Dog";
                break;
            case 3:
                animal = "Lion";
                break;
            case 4:
                animal = "Snake";
                break;
            case 5:
                animal = "Mouse";
                break;
            case 6:
                animal = "Plant";
                break;
            default:
                System.out.println("Enter valid number ):");
                return;

        }
        System.out.printf("Enter count for %s : ", animal);
        int count = scanner.nextInt();
        Manager.addAnimal(animal, count);

    }

    public static void menu_findAnimalByID() {
        System.out.print("Enter animal ID: ");
        Scanner scanner = new Scanner(System.in);
        int ID = scanner.nextInt();
        System.out.println(Manager.findAnimal(ID));
    }

    public static void menu_registerHuntReport() {
        System.out.print("Enter hunter ID: ");
        Scanner scanner = new Scanner(System.in);
        int hunterID = scanner.nextInt();
        Animal hunter = Manager.findAnimal(hunterID);
        if (hunter == null)
            return;
        System.out.print("Enter victim ID: ");
        int victimID = scanner.nextInt();
        Animal victim = Manager.findAnimal(victimID);
        if (victim == null)
            return;
        System.out.printf("Are you shre submit %s ID: %d eated  %s ID: %d?\n", hunter.getAnimalName(),
                hunterID, victim.getAnimalName(), victimID);
        System.out.println("0 - NO\n1 - YES");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                System.out.println("Hunt report Canceled |:");
                break;
            case 1:
                Manager.setHuntReport(hunter, victim);
                System.out.println("Hunt report submited (: ");
                break;

        }

    }

    public static void menu_registerLiveStatus() {
        System.out.print("Enter animal ID: ");
        Scanner scanner = new Scanner(System.in);
        int animalID = scanner.nextInt();
        Animal animal = Manager.findAnimal(animalID);
        if (animal == null)
            return;
        System.out.println("1 - Alive\n2 - Dead");
        System.out.print("Enter animal status for set: ");
        int statusChoice = scanner.nextInt();
        switch (statusChoice) {
            case 1:
                Manager.setLiveAnimal(animal, true);
                break;
            case 2:
                Manager.setLiveAnimal(animal, false);
                break;

        }
    }

    public static void menu_exportFullReport() {
        // Source :
        // https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
        try {

            // Create a stream to hold the output
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            // IMPORTANT: Save the old System.out!
            PrintStream old = System.out;
            // Tell Java to use your special stream
            System.setOut(ps);
            // Print some output: goes to your special stream
            Manager.getFullReport();
            // Put things back
            System.out.flush();
            System.setOut(old);
            // Create a FileWrite write output stream in a file
            FileWriter writer = new FileWriter("Report.txt");
            // Write to Report.txt
            writer.write(baos.toString());
            writer.close();
            System.out.println("Report file created :)");

        } catch (Exception e) {
            e.printStackTrace();
        }

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
                System.out.printf("Cat is added to zoo. ID : %d\n", new Cat().getAnimalID());
            }
        } else if (animal.equalsIgnoreCase("dog")) {
            for (int i = 0; i < count; i++) {
                System.out.printf("Dog is added to zoo. ID : %d\n", new Dog().getAnimalID());
            }
        } else if (animal.equalsIgnoreCase("lion")) {
            for (int i = 0; i < count; i++) {
                System.out.printf("Lion is added to zoo. ID : %d\n", new Lion().getAnimalID());
            }
        } else if (animal.equalsIgnoreCase("snake")) {
            for (int i = 0; i < count; i++) {
                System.out.printf("Snake is added to zoo. ID : %d\n", new Snake().getAnimalID());
            }
        } else if (animal.equalsIgnoreCase("mouse")) {
            for (int i = 0; i < count; i++) {
                System.out.printf("Mouse is added to zoo. ID : %d\n", new Mouse().getAnimalID());
            }
        } else if (animal.equalsIgnoreCase("plant")) {
            for (int i = 0; i < count; i++) {
                System.out.printf("Plant is added to zoo. ID : %d\n", new Plant().getAnimalID());
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
     * call eatAnimal method in hunter parameter and submit hunt victim by
     * hunter.
     * hunter : Animal
     * victim : Animal
     */
    public static void setHuntReport(Animal hunter, Animal victim) {
        hunter.eatAnimal(victim);
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
        for (Animal animal : Animal.getLiveAnimalsList()) {
            if (animal.getAnimalID() == animalID) {
                return animal;
            }
        }
        for (Animal animal : Animal.getDeadAnimalsList()) {
            if (animal.getAnimalID() == animalID) {
                return animal;
            }
        }
        System.out.printf("Not found animal with ID : %d ):\n", animalID);
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
            if (Animal.getLiveAnimalsList().size() == 0) {
                System.out.println("Not found live animal ):");
                return;
            }
            for (Animal animal : Animal.getLiveAnimalsList()) {
                System.out.println(" " + animal);
            }
        }

        else {
            if (Animal.getDeadAnimalsList().size() == 0) {

                System.out.println("Not found dead animal ):");
                return;
            }
            for (Animal animal : Animal.getDeadAnimalsList()) {
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
        if (Animal.getLiveAnimalsList().size() == 0) {
            System.out.println("Not found a animal ):");
            return;
        }
        int catCount = 0, dogCount = 0, lionCount = 0, snakeCount = 0, mouseCount = 0, plantCount = 0;
        for (Animal animal : Animal.getLiveAnimalsList()) {
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
                catCount, dogCount, lionCount, snakeCount, mouseCount, plantCount, Animal.getLiveAnimalsList().size());
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
    private static List<Animal> liveAnimals = new ArrayList<Animal>();
    // define a static ArrayList for manage live animals
    private static List<Animal> deadAnimals = new ArrayList<Animal>();
    // livingStatus is a massage contains live or dead status
    private String livingStatus;
    // AnimalName is object class name
    private String animalName;
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
        // get object class name and set to animalName
        animalName = this.getClass().getSimpleName();
    }

    // eat is a abstract method because different animals have different eat
    // behavior
    abstract protected void eatAnimal(Animal victim);

    /*
     * dieByHunting method call when this animal eat by the other animals
     * hunter : Animal
     */
    protected void dieByHunting(Animal hunter) {
        // call setIsLive setter and set false status
        setIsLive(false);
        // get hunter object class name
        String hunterName = hunter.getAnimalName();
        // get hunter object animalID
        int hunterID = hunter.getAnimalID();
        // get this object class name
        String victimName = this.getAnimalName();
        // get this object animalID
        int victimID = this.getAnimalID();
        // call setLivingStatus and send formated String with hunter name , hunter ID ,
        // victim name ,victim ID and date of eat.
        setLivingStatus(String.format("The %s with ID: %d eat the %s with ID: %d in %s ):", hunterName, hunterID,
                victimName,
                victimID,
                dtf.format(LocalDateTime.now())));

    }

    // getLiveAnimalsList method is a getter for liveAnimals property
    public static List<Animal> getLiveAnimalsList() {
        return liveAnimals;
    }

    // getDeadAnimalsList method is a getter for deadAnimals property
    public static List<Animal> getDeadAnimalsList() {
        return deadAnimals;
    }

    // getAnimalName method is a getter for animlaName property
    public String getAnimalName() {
        return animalName;
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
        String objectName = this.getAnimalName();
        /*
         * if this.isAlive() true : return formated string with this form : this object
         * class name , this object animalID ,Live
         * if this.isAlive() fals : retuen formated string with this form : this object
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

// Create Dog type as animal
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

// Create Snake type as animal
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

// Create Lion type as animal
class Lion extends Animal {

    @Override
    // impeliment eatAnimal behavior ; call victim.dieByHunting method.
    // The lion eats everything here. even grass (:
    protected void eatAnimal(Animal victim) {
        victim.dieByHunting(this);

    }

}

// Create Mouse type as animal
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

// Create Plant type as animal
class Plant extends Animal {

    @Override
    // impeliment eatAnimal behavior ; print error massage. Because Plant not eat
    // anything.
    protected void eatAnimal(Animal victim) {
        System.out.println("The plant does not eat anything <:");

    }

}
