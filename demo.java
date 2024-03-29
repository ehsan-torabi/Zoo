import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.time.LocalDateTime;

/**
 * @author Ehsan Torabi Farsani
 */

public class demo {
    // Create a scanner for get inputs in demo class
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Print options and number for select
        printMenu();
        // Get a number option from user
        int input = scanner.nextInt();
        // Send user input number to doOption and process user select
        doOption(input);

    }

    // Print options and number for select
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

    // doOptions method contains a switch-case to control and process user selected
    // options
    public static void doOption(int value) {
        while (true) {
            switch (value) {
                case 0:
                    // Exit program if user input 0
                    System.exit(0);
                case 1:
                    // Call menu_addAnimal for add animal if user input 1
                    menu_addAnimal();
                    break;
                case 2:
                    // Call Manager.getFullReport() for print full report from zoo animals if user
                    // input 2
                    Manager.getFullReport();
                    break;
                case 3:
                    // Call menu_findAnimalByID for get animalID and find animal with ID if user
                    // input 3
                    menu_findAnimalByID();
                    break;
                case 4:
                    System.out.println("\nLive animals: ");
                    // Call Manager.findAnimal and send true for print live animals if user input 4
                    Manager.findAnimal(true);
                    break;
                case 5:
                    System.out.println("\nDead animals: ");
                    // Call Manager.findAnimal and send false for print dead animals if user input 5
                    Manager.findAnimal(false);
                    break;
                case 6:
                    // Call menu_registerHuntReport for submit hunt animals if user input 6
                    menu_registerHuntReport();
                    break;
                case 7:
                    // Call menu_registerLiveStatus for submit change live status a animal if user
                    // input 7
                    menu_registerLiveStatus();
                    break;
                case 8:
                    // Call menu_exportFullReport for export zoo animals report to a txt file if
                    // user input 8
                    menu_exportFullReport();
                    break;

            }
            break;
        }
        menu_endOptins(value);

    }

    private static void menu_endOptins(int value) {
        System.out.println("\n-------------------------------------------------");
        // Print end process optins
        System.out.println("0 - MainMenu\n1 - Continue");
        System.out.println("-------------------------------------------------");
        // Get user choice number optins
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                // call demo.main with send null argument for back to main menu
                main(null);
                break;
            case 1:
                // call this method again for repeat process and continue
                doOption(value);
                break;
            default:
                main(null);
                break;

        }
    }

    // menu_addAnimal for add animal
    private static void menu_addAnimal() {
        // Print options and number for select
        System.out.println("\n1 - Cat\n2 - Dog\n3 - Lion\n4 - Snake\n5 - Mouse\n6 - Plant\n");
        System.out.print("Enter animal number : ");
        // Get user choice number optins
        int choice = scanner.nextInt();
        // define animal String for save animal name for send to Manager.addAnimal
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
        // Get count for add animal and send to Manager.addAnimal
        System.out.printf("Enter count for %s : ", animal);
        int count = scanner.nextInt();
        // Call Manager.addAnimal and send animal and count variable to add animal
        // process
        Manager.addAnimal(animal, count);

    }

    // menu_findAnimalByID for get animalID and find animal with ID
    private static void menu_findAnimalByID() {
        System.out.print("Enter animal ID: ");
        // Get ID for send to Manager.findAnimal and get Animal with this ID
        int ID = scanner.nextInt();
        // Print returned animal(call toString method in returned Animal)
        System.out.println(Manager.findAnimal(ID));
    }

    // menu_registerHuntReport for submit hunt animals
    private static void menu_registerHuntReport() {
        System.out.print("Enter hunter ID: ");
        // Get ID for send to Manager.findAnimal and get Animal (hunter) with this ID
        int hunterID = scanner.nextInt();
        // Save returned Animal (hunter)
        Animal hunter = Manager.findAnimal(hunterID);
        // Check null status returned Animal (hunter)
        if (hunter == null)
            return;
        System.out.print("Enter victim ID: ");
        // Get ID for send to Manager.findAnimal and get Animal (victim) with this ID
        int victimID = scanner.nextInt();
        // Save returned Animal (victim)
        Animal victim = Manager.findAnimal(victimID);
        // Check null status returned Animal (victim)
        if (victim == null)
            return;
        // Display message to finalize processing
        System.out.printf("Are you shre submit %s ID: %d eated  %s ID: %d?\n", hunter.getAnimalName(),
                hunterID, victim.getAnimalName(), victimID);
        // Show options for select
        System.out.println("0 - NO\n1 - YES");
        // Get user input number option
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                // Show massage to user breked of submit hunt report
                System.out.println("Hunt report Canceled |:");
                break;
            case 1:
                // Send hunter and victim to Manager.setHuntReport for submit gunt report
                Boolean isValid =  Manager.setHuntReport(hunter, victim);
                if (isValid)
                    System.out.println("Hunt report submited (: ");
                break;

        }

    }

    // menu_registerLiveStatus for submit change live status a animal
    private static void menu_registerLiveStatus() {
        System.out.print("Enter animal ID: ");
        // Get ID for send to Manager.findAnimal and get Animal with this ID
        int animalID = scanner.nextInt();
        // Save returned Animal
        Animal animal = Manager.findAnimal(animalID);
        // Check null status returned Animal
        if (animal == null)
            return;
        // Show options for select
        System.out.println("1 - Alive\n2 - Dead");
        // Get user input number option
        System.out.print("Enter animal status for set: ");
        int statusChoice = scanner.nextInt();
        switch (statusChoice) {
            case 1:
                // Send animal and true to Manager.setLiveAnimal if user input 1 (Alive)
                Manager.setLiveAnimal(animal, true);
                break;
            case 2:
                // Send animal and true to Manager.setLiveAnimal if user input 1 (Dead)
                Manager.setLiveAnimal(animal, false);
                break;

        }
    }

    // menu_exportFullReport for export zoo animals report to a txt file
    private static void menu_exportFullReport() {
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
    public static boolean setHuntReport(Animal hunter, Animal victim) {
        hunter.eatAnimal(victim);
        if (victim.isAlive()){
            return false;
        }
        else {
            return true; 
        }
    }

    /*
     * The findAnimal method with an animalID parameter :
     * itrate to Animal.getAllAnimals() for find and print animal with animalID
     * property == animalID parameter.
     * animalID : int
     * return : if found Animal object , if not found print error massage and
     * null
     */
    public static Animal findAnimal(int animalID) {

        for (Animal animal : Animal.getAllAnimals()) {
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
            if (Animal.getLiveAnimalsList().isEmpty()) {
                System.out.println("Not found live animal ):");
                return;
            }
            for (Animal animal : Animal.getLiveAnimalsList()) {
                System.out.println(" " + animal);
            }
        }

        else {
            if (Animal.getDeadAnimalsList().isEmpty()) {

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
        List<Animal> liveAnimals = Animal.getLiveAnimalsList();
        if (liveAnimals.isEmpty()) {
            System.out.println("Not found an animal ):");
            return;
        }

        Map<String, Integer> animalCounts = new HashMap<>();
        for (Animal animal : liveAnimals) {
            String className = animal.getClass().getSimpleName();
            animalCounts.put(className, animalCounts.getOrDefault(className, 0) + 1);
        }

        System.out.println("-------------------------------------------");
        System.out.println("Animal Count:");
        for (String className : animalCounts.keySet()) {
            System.out.printf("  %s: %d\n", className, animalCounts.get(className));
        }
        System.out.printf("\tTotal: %d\n", liveAnimals.size());
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
    // livingStatusMassge is a massage contains live or dead status
    private String livingStatusMassge;
    // AnimalName is object class name
    private String animalName;
    // AnimalID is a primary key for an object as Animal class
    private int animalID;
    // isAlive a variable to manage dead or live animal status. default true(live)
    private boolean isAlive = true;
    // dtf is a time formatter for customize date and time format printing
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    {
        // Generate nearly random ID
        animalID = generateID();
        // add to live Animals list in first object creation(Animal.liveAnimals)
        liveAnimals.add(this);
        // get object class name and set to animalName
        animalName = this.getClass().getSimpleName();
    }

    // eat is a abstract method because different animals have different eat
    // behavior
    abstract protected void eatAnimal(Animal victim);

    // itrate to liveAnimals and deadAnimals list and return a union list
    public static ArrayList<Animal> getAllAnimals() {
        ArrayList<Animal> resulList = new ArrayList<Animal>();
        for (Animal deadAnimal : deadAnimals) {
            resulList.add(deadAnimal);
        }
        for (Animal liveAnimal : liveAnimals) {
            resulList.add(liveAnimal);
        }
        return resulList;
    }

    // Generate a uniqe ID
    private int generateID() {
        // generate nearly ID and temp save
        int tempID = (int) (Math.random() * Math.random() * 10) + 101 + (int) (Math.random() * 100);
        // check ID in live animals
        if (!getAllAnimals().isEmpty()) {
            for (Animal animal : getAllAnimals()) {
                if (animal.getAnimalID() == tempID)
                    generateID();
            }
        }
        return tempID;
    }

    /*
     * dieByHunting method call when this animal eat by the other animals
     * hunter : Animal
     */
    protected void dieByHunting(Animal hunter) {
        // get hunter object class name
        String hunterName = hunter.getAnimalName();
        // get hunter object animalID
        int hunterID = hunter.getAnimalID();
        // get this object class name
        String victimName = this.getAnimalName();
        // get this object animalID
        int victimID = this.getAnimalID();
        // Checking the life of the animal and performing appropriate operations with it
        if (!isAlive()) {
            // call setLivingStatusMassage and send formated String with hunter name ,
            // hunter ID
            setLivingStatusMassage(String.format(
                    "This animal was already dead, but now its carcass was eaten by %s with ID %d .", hunterName,
                    hunterID));
        } else {
            setIsLive(false);
            // call setLivingStatusMassage and send formated String with hunter name ,
            // hunter ID ,
            // victim name ,victim ID and date of eat.
            setLivingStatusMassage(
                    String.format("The %s with ID: %d eat the %s with ID: %d in %s ):", hunterName, hunterID,
                            victimName,
                            victimID,
                            dtf.format(LocalDateTime.now())));
        }

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
     * if status false : remove this animal from liveAnimals list and add to
     * deadAnimals list. also call setLivingStatusMassage and set reason of dead to
     * "This animal is dead ):"
     * if status true : check this animal live status if false : remove this animal
     * from deadAnimals list and add to liveAnimals list and setLivingStatusMassage
     * to "Live (: "
     */
    public void setIsLive(boolean status) {
        String reasonError = status ? "Alive" : "Dead";
        if (isAlive == status) {
            System.out.printf("This animal in current is %s\n", reasonError);
            return;
        }

        if (!status) {
            liveAnimals.remove(liveAnimals.indexOf(this));
            deadAnimals.add(this);
            setLivingStatusMassage("Dead ): ");
            isAlive = status;
        } else if (status) {
            if (!this.isAlive()) {
                deadAnimals.remove(deadAnimals.indexOf(this));
                liveAnimals.add(this);
                setLivingStatusMassage("Live (: ");
                isAlive = status;
            }
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
        return isAlive() ? "Live (: " : livingStatusMassge;
    }

    // setLivingStatusMassage method is a setter for reason property
    public void setLivingStatusMassage(String message) {
        livingStatusMassge = message;
    }

    @Override
    public String toString() {

        /*
         * return formated string with this form : this object
         * class name , this object animalID (getAnimalName()) ,getLivingStatus
         */
        return String.format(" %s - ID : %d - Status : %s ", getAnimalName(),
                getAnimalID(),
                getLivingStatus());
    }

}

// Create Cat type as animal
class Cat extends Animal {
    @Override
    // implement eatAnimal behavior ; check isAlive this and if victim is a Mouse
    // or if Snake call or Snake victim.dieByHunting method. else print error
    // massage.
    protected void eatAnimal(Animal victim) {
        if (victim.isAlive()) {
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
    // implement eatAnimal behavior ; check isAlive this and if victim is a Cat
    // call or Snake victim.dieByHunting method. else print error massage.
    protected void eatAnimal(Animal victim) {
        if (victim.isAlive()) {
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
    // implement eatAnimal behavior ; check isAlive this and if victim is a Mouse
    // call or Snake victim.dieByHunting method. else print error massage.
    protected void eatAnimal(Animal victim) {
        if (victim.isAlive()) {
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
    // implement eatAnimal behavior ; call victim.dieByHunting method.
    // The lion eats everything here. even grass (:

    protected void eatAnimal(Animal victim) {
        if (!(victim instanceof Lion))
            victim.dieByHunting(this);
        else
            System.out.println("Impossible |:");
    }

}

// Create Mouse type as animal
class Mouse extends Animal {

    @Override
    // implement eatAnimal behavior ; check isAlive this and if victim is a Plant
    // call or Snake victim.dieByHunting method. else print error massage.
    protected void eatAnimal(Animal victim) {
        if (victim.isAlive()) {
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
    // implement eatAnimal behavior ; print error massage. Because Plant not eat
    // anything.
    protected void eatAnimal(Animal victim) {
        System.out.println("The plant does not eat anything <:");

    }

}
