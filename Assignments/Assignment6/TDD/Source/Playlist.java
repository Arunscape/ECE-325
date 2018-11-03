/**
 * Assignment 6: Test Driven Development <br />
 * The {@code Playlist} class
 */
@SuppressWarnings("serial")
public class Playlist<E extends Song> extends java.util.Vector<E> {
    java.util.Iterator<E> itr = this.iterator();       // Generic Iterator; Use it whenever you need it!
    
    private String title;
    
    Playlist(String title){
    	this.title = title;
    }
    
    public String getTitle() {
    	return this.title;
    }
    
    public Boolean addtoPlist(E s) {
    	int i = this.indexOf(s);
    			
    	if (s == null) {
    		return false;
    	}
    	else if (i == -1) {
    		this.add(s);
    		return true;
    	}
    	else {
    		return false;
    	}
//    	this.add(s);
//    	return true;
    }
    
    public Boolean removeFromPlist(E s) {
    	int i = this.indexOf(s);
    	
    	if (i == -1) {
    		return false;
    	}
    	else {
    		this.remove(i);
    		return true;
    	}
    	
    }
    
    public Song getSong(int i) {
    	return this.elementAt(i);
    }
    
    public Boolean hasTitle(String t) {
    	while (itr.hasNext()) {
    		if (itr.next().getTitle().toLowerCase().equals(t)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public Boolean hasArtist(String t) {
    	while (itr.hasNext()) {
    		if (itr.next().getArtist().toLowerCase().equals(t)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public int numberOfSongs() {
    	return this.size();
    }
    
    public int numberOfArtists() {
    	java.util.Set<String> artists = new java.util.HashSet<String>();
    	while (itr.hasNext()) {
    		artists.add(itr.next().getArtist());
    	}
    	return artists.size();
    }
    
    public int numberOfTitles() {
    	java.util.Set<String> titles = new java.util.HashSet<String>();
    	while (itr.hasNext()) {
    		titles.add(itr.next().getTitle().toLowerCase());
    	}
    	return titles.size();
    }
    
    public double playTime() {
    	double t = 0;
    	while (itr.hasNext()) {
    		t += itr.next().getLength();
    	}
    	return t;
    }
    
    public int findSong(Song s) {
    	return this.indexOf(s);
    }
    
    
   
}
