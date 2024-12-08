import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
public class InventoryController {

    @FXML
    private TableView<Album> tableView;
    @FXML
    private TableColumn<Album, String> columnAlbum;
    @FXML
    private TableColumn<Album, String> columnArtist;
    @FXML
    private TableColumn<Album, Integer> columnTotal;
    @FXML
    private TableColumn<Album, Integer> columnAvailable;
    
    @FXML
    private TextField albumField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField totalField;
    @FXML
    private TextField availableField;
    @FXML
    private TextField rentedField;

    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;

    @FXML
    private void addAlbum() {
        try {
            String albumName = albumField.getText();
            String artist = artistField.getText();
            int total = Integer.parseInt(totalField.getText());
            int available = Integer.parseInt(availableField.getText());
            int rented = Integer.parseInt(rentedField.getText());

            if (albumName.isEmpty() || artist.isEmpty()) {
                throw new IllegalArgumentException("Album name and artist cannot be empty.");
            }

            Album newAlbum = new Album(albumName, artist, total, available, rented);
            tableView.getItems().add(newAlbum);

            clearFields();
            showAlert(AlertType.INFORMATION, "Album berhasil ditambahkan", "Album " + albumName + " berhasil ditambahkan!");

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Kesalahan dalam input data", "Silahkan cek data yang anda masukkan");
        } catch (IllegalArgumentException e) {
            showAlert(AlertType.ERROR, "Kesalahan dalam input data", e.getMessage());
        }
    }

    @FXML
    private void updateAlbum() {
        try {
            Album selectedAlbum = tableView.getSelectionModel().getSelectedItem();
            if (selectedAlbum == null) {
                showAlert(AlertType.WARNING, "Tidak ada pilihan", "Pilih album untuk update.");
                return;
            }

            String albumName = albumField.getText();
            String artist = artistField.getText();
            int total = Integer.parseInt(totalField.getText());
            int available = Integer.parseInt(availableField.getText());
            int rented = Integer.parseInt(rentedField.getText());

            if (albumName.isEmpty() || artist.isEmpty()) {
                throw new IllegalArgumentException("Album name and artist cannot be empty.");
            }

            selectedAlbum.setAlbumName(albumName);
            selectedAlbum.setArtist(artist);
            selectedAlbum.setTotal(total);
            selectedAlbum.setAvailable(available);
            selectedAlbum.setRented(rented);
            showAlert(AlertType.INFORMATION, "Album berhasil diupdate", "Album " + albumName + " berhasil diperbarui!");
            
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Kesalahan dalam input data", "Silahkan cek data yang anda masukkan");
        } catch (IllegalArgumentException e) {
            showAlert(AlertType.ERROR, "Kesalahan dalam input data", e.getMessage());
        }
    }

    @FXML
    private void deleteAlbum() {
        try {
            Album selectedAlbum = tableView.getSelectionModel().getSelectedItem();
            if (selectedAlbum == null) {
                showAlert(AlertType.WARNING, "Tidak ada pilihan", "Pilih album untuk delete.");
                return;
            }

            tableView.getItems().remove(selectedAlbum);
            String albumName = selectedAlbum.getAlbumName();
            showAlert(AlertType.INFORMATION, "Album berhasil didelete", "Album " + albumName + " berhasil dihapus!");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "Error terjadi ketika mendelete album.");
        }
    }

    @FXML
    private void rentAlbum() {
        try {
            Album selectedAlbum = tableView.getSelectionModel().getSelectedItem();
            if (selectedAlbum == null) {
                showAlert(AlertType.WARNING, "Tidak ada pilihan", "Pilih album untuk untuk disewa.");
                return;
            }

            if (selectedAlbum.getAvailable() > 0) {
                selectedAlbum.setAvailable(selectedAlbum.getAvailable() - 1);
                selectedAlbum.setRented(selectedAlbum.getRented() + 1);
                showAlert(AlertType.INFORMATION, "Album berhasil disewa", "Album " + selectedAlbum.getAlbumName() + " berhasil disewa!");
            } else {
                showAlert(AlertType.WARNING, "No Available Copies", "Tidak ada copy tersisa dari album yang dipilih.");
            }

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "Error terjadi ketika menyewa album.");
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        albumField.clear();
        artistField.clear();
        totalField.clear();
        availableField.clear();
        rentedField.clear();
    }

    @FXML
    private void handleTableRowClick(MouseEvent event) {
        Album selectedAlbum = tableView.getSelectionModel().getSelectedItem();
        if (selectedAlbum != null) {
            albumField.setText(selectedAlbum.getAlbumName());
            artistField.setText(selectedAlbum.getArtist());
            totalField.setText(String.valueOf(selectedAlbum.getTotal()));
            availableField.setText(String.valueOf(selectedAlbum.getAvailable()));
            rentedField.setText(String.valueOf(selectedAlbum.getRented()));
        }
    }

    @FXML
    public void initialize() {
        columnAlbum.setCellValueFactory(cellData -> cellData.getValue().albumNameProperty());
        columnArtist.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        columnTotal.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());
        columnAvailable.setCellValueFactory(cellData -> cellData.getValue().availableProperty().asObject());
    }
}
