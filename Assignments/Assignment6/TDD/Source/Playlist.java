import java.util.Collections;

/**
 * Assignment 6: Test Driven Development <br />
 * The {@code Playlist} class
 */
@SuppressWarnings("serial")
public class Playlist<E extends Song> extends java.util.Vector<E> {
    
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
    	return this.title.equalsIgnoreCase(t);
    }
    
    public Boolean hasArtist(String a) {
    	for (Song s: this) {
		if(s.getArtist().equalsIgnoreCase(a)) {
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
    	for (Song s: this) {
    		artists.add(s.getArtist().toLowerCase());
    	}
    	return artists.size();
    }
    
    public int numberOfTitles() {
    	java.util.Set<String> titles = new java.util.HashSet<String>();
    	for (Song s: this) {
    		titles.add(s.getTitle().toLowerCase());
    	}
    	return titles.size();
    }
    
    public double playTime() {
    	double t = 0;
    	for (Song s: this) {
    		t += s.getLength();
    	}
    	return t;
    }
    
    public int findSong(Song s) {
    	return this.indexOf(s);
    }
    
    public void sortByArtist() {
    	
    	Collections.sort(this, new java.util.Comparator<Song>() {

			@Override
			public int compare(Song s1, Song s2) {
				return s1.getArtist().compareTo(s2.getArtist());
			}
    		
    	});
    	
    }
    
    public void sortByTitle() {
    	
    	Collections.sort(this, new java.util.Comparator<Song>() {

			@Override
			public int compare(Song s1, Song s2) {
				return s1.getTitle().compareTo(s2.getTitle());
			}
    		
    	});
    	
    }
   
}
