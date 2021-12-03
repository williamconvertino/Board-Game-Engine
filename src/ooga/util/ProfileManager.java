package ooga.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import ooga.exceptions.PlayerProfileException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class handles creating, updating, and logging into player profiles.
 *
 * @author Jordan Castleman
 *
 * @since 0.0.1
 */
public class ProfileManager {

    private final String filePath = "data/profiles/playerProfiles.csv";

    public ProfileManager() {

    }

    /**
     * Method to add a new player profile to the records.
     * @param newProfile- a list of strings containing all the necessary data to construct a player profile.
     * @throws IOException- should the player profile records file not exist.
     */
    public void createNewPlayerProfile(String[] newProfile) throws IOException, PlayerProfileException {
        List<String[]> allProfiles = getAllPlayerProfiles();
        if (profileUsernameAlreadyExists(newProfile[0], allProfiles)) {
            throw new PlayerProfileException();
        }
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(filePath));
            allProfiles.add(newProfile);
            writer.writeAll(allProfiles);
            writer.close();
        }
        //if something goes wrong when adding a new player profile
        catch (Exception e) {
            throw new PlayerProfileException();
        }
    }

    public boolean profileUsernameAlreadyExists(String username, List<String[]> existingProfiles) {
        for (String[] profile: existingProfiles) {
            if (profile[0] == username) {
                return true;
            }
        }
        return false;
    }

    //TODO
    public void updatePlayerProfile(String username, String password, String imageString) {

    }

    /**
     * Method to login to a player profile.
     * @param username- the username of the profile to be logged into.
     * @param password- the password of the profile to be logged into.
     * @return- A list of strings representing the player profile logged into.  The first element of the list is the username,
     * the second element is the password, the third is the player's avatar.
     * @throws FileNotFoundException- should the player profile file not exist or cannot be found.
     * @throws PlayerProfileException- should there be no existing profiles or if the username/password does not match an existing profile.
     */
    public String[] loginPlayerProfile(String username, String password) throws FileNotFoundException, PlayerProfileException {
        List<String[]> existingProfiles = getAllPlayerProfiles();
        //if no player profiles
        if (existingProfiles.size() == 0) {
            throw new PlayerProfileException();
        }
        for (String[] profile: existingProfiles) {
            if (profile[0] == username && profile[1] == password) {
                return profile;
            }
        }
        //if profile not found
        throw new PlayerProfileException();
    }

    /**
     * Method to get all existing player profiles.
     * @return- A list of lists of strings each of which represents a player profile.
     * @throws FileNotFoundException- should the player profile file not exist.
     * @throws PlayerProfileException- should there be no existing player profiles.
     */
    public List<String[]> getAllPlayerProfiles() throws FileNotFoundException {
        try {
            FileReader filereader = new FileReader(filePath);
            CSVReader csvReader = new CSVReaderBuilder(filereader).build();
            List<String[]> allProfiles = csvReader.readAll();
            return allProfiles;
        }
        catch (Exception e) {
            throw new FileNotFoundException();
        }
    }
}
