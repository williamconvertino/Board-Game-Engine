package ooga.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import ooga.exceptions.PlayerProfileException;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

    //TODO
    public void createNewPlayerProfile(String username, String password, String imageString) {

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
            List<String[]> parsedData = csvReader.readAll();
            return parsedData;
        }
        catch (Exception e) {
            throw new FileNotFoundException();
        }
    }
}
