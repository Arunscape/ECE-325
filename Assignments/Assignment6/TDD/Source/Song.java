/**
 * Assignment 6: Test Driven Development <br />
 * The {@code Song} class
 */
public class Song {
    private String artist;
    private String title;
    private double length;

	Song(String artist, String title, double length){
		this.artist = artist;
		this.title = title;
		this.length = length;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public double getLength() {
		return this.length;
	}
	
	public boolean isArtist(String s) {
		return this.artist.toLowerCase().equals(s.toLowerCase()) ? true : false;
	}
	
	public boolean isTitle(String t) {
		return this.title.toLowerCase().equals(t.toLowerCase()) ? true : false;
	}
	
	@Override
	public boolean equals(Object s) {
		return ((Song) s).isArtist(this.artist) && 
			   ((Song) s).isTitle(this.title) && 
			   ((Song) s).getLength() == this.length
			   ? true : false;
					   
	}
	
	@Override
	public String toString() {
		return String.format("\"%s\" by %s: %f", this.title, this.artist, this.length);
	}

}
