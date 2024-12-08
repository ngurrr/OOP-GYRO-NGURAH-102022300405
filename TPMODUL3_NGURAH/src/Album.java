import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Album {
    private final StringProperty albumName;
    private final StringProperty artist;
    private final IntegerProperty total;
    private final IntegerProperty available;
    private final IntegerProperty rented;
    public Album(String albumName, String artist, int total, int available, int rented) {
        this.albumName = new SimpleStringProperty(albumName);
        this.artist = new SimpleStringProperty(artist);
        this.total = new SimpleIntegerProperty(total);
        this.available = new SimpleIntegerProperty(available);
        this.rented = new SimpleIntegerProperty(rented);
    }
    public String getAlbumName() {
        return albumName.get();
    }
    public void setAlbumName(String albumName) {
        this.albumName.set(albumName);
    }
    public StringProperty albumNameProperty() {
        return albumName;
    }
    public String getArtist() {
        return artist.get();
    }
    public void setArtist(String artist) {
        this.artist.set(artist);
    }
    public StringProperty artistProperty() {
        return artist;
    }
    public int getTotal() {
        return total.get();
    }
    public void setTotal(int total) {
        this.total.set(total);
    }
    public IntegerProperty totalProperty() {
        return total;
    }
    public int getAvailable() {
        return available.get();
    }
    public void setAvailable(int available) {
        this.available.set(available);
    }
    public IntegerProperty availableProperty() {
        return available;
    }
    public int getRented() {
        return rented.get();
    }
    public void setRented(int rented) {
        this.rented.set(rented);
    }
    public IntegerProperty rentedProperty() {
        return rented;
    }
}