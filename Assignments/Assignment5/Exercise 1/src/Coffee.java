/**
 * Assignment 5: Interfaces <br />
 * Part 1: The {@code Coffee} class
 */
class Coffee implements Comparable<Coffee> {
    private int strength;       // The strength of the coffee
    
    Coffee(int s){
    	strength = s;
    }
    
    public int getStrength() {
    	return strength;
    }
    
    @Override
    public int compareTo(Coffee c) {
    	return Integer.compare(strength, c.getStrength());
    }
    
    @Override
    public String toString() {
    	return String.format("Strength: %d", strength);
    }

}
