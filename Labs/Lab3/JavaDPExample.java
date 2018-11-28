/*DISCUSSION:
   1.
   Why do we use a static method in this
   situation?

   - we can call this method without the need to instantiate an object
   - it wouldn't make sense to do something like
    mouse.getAnimal("Big")
   and have the function output "Bison"

   2.
   The code implements a class-level (involving multiple classes) programming "good practice", commonly
   these practices are called design patterns in Java. Which design pattern is implemented?

   It uses the factory pattern
   https://www.tutorialspoint.com/design_pattern/factory_pattern.htm

   3.
   Explain why this is considered a good practice.
   The animal interface provides an abstraction
   New animals can be created easily by extending the animal interface.

   This also ensures that all classes which should be considered as an animal have
   the required methods for an animal defined.



 */
/**
 * Lab 3: Inheritance, Interfaces, Hash, Design Pattern and Big Number <br />
 * The {@code Animal} interface
 */
interface Animal {
/**
 * An animal speaks
 */
public void speak ();
}

/**
 * Lab 3: Inheritance, Interfaces, Hash, Design Pattern and Big Number <br />
 * The {@code Lion} class
 */
class Lion implements Animal {
/**
 * The lion speaks
 */
public void speak() {
        System.out.println("ROAR");
}
}

/**
 * Lab 3: Inheritance, Interfaces, Hash, Design Pattern and Big Number <br />
 * The {@code Mouse} class
 */
class Mouse implements Animal {
/**
 * The mouse speaks
 */
public void speak() {
        System.out.println("SQUEAK");
}
}

/**
 * Lab 3: Inheritance, Interfaces, Hash, Design Pattern and Big Number <br />
 * The {@code Bison} class
 */
class Bison implements Animal {
/**
 * The bison speaks
 */
public void speak() {
        System.out.println("BELLOW");
}
}

/**
 * Lab 3: Inheritance, Interfaces, Hash, Design Pattern and Big Number <br />
 * The {@code AnimalType} class
 */
class AnimalType {
/**
 * Create and return an animal
 * @param criteria      {@code String} how is the animal like
 * @return              {@code Animal} the animal
 */
public static Animal getAnimal(String criteria) {
        if (criteria.equals("small"))
                return new Mouse();
        else if (criteria.equals("big"))
                return new Bison();
        else if (criteria.equals("lazy"))
                return new Lion();
        return null;
}
}

/**
 * Lab 3: Inheritance, Interfaces, Hash, Design Pattern and Big Number <br />
 * The {@code JavaDPExample} class
 */
public class JavaDPExample {
/**
 * Main entry
 * @param args          {@code String[]} Command line arguments
 */
public static void main(String[] args) {
        AnimalType.getAnimal("small").speak();
        AnimalType.getAnimal("big").speak();
        AnimalType.getAnimal("lazy").speak();
}
// TODO: Lab 3 Part 3 -- Answer the design pattern questions
}
