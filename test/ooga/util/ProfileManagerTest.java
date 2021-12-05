package ooga.util;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ProfileManagerTest {

    ProfileManager profileManager;
    private String[] profile1;
    private String[] profile2;
    private String[] profile3;

    private final String filePath = "data/testdata/testPlayerProfiles.csv";

    @BeforeEach
    void setUp() throws IOException {
        profileManager = new ProfileManager();
        List<String[]> allProfiles = new LinkedList<>();
        profile1 = new String[]{"jordanuser", "jordanPassword", "jordanimagestring", "jordan"};
        profile2 = new String[]{"caseyuser", "caseyPassword", "caseyimagestring", "casey"};
        profile3 = new String[]{"willuser", "willPassword", "willimagestring", "will"};
        allProfiles.add(profile1);
        allProfiles.add(profile2);
        allProfiles.add(profile3);
        CSVWriter writer = new CSVWriter(new FileWriter(filePath));
        writer.writeAll(allProfiles);
        writer.close();

    }

    @Test
    void testCreateNewProfileWithNewUsername() {
        String[] newProfile = {"newuser", "newPassword", "newimagestring", "newname"};
        try {
            profileManager.createNewPlayerProfile(newProfile, filePath);
        }
        catch (Exception e) {
        }
        try {
            List<String[]> allProfiles = profileManager.getAllPlayerProfiles(filePath);
            int isNewUser = 0;
            int isOldUser = 0;
            for (String[] profile: allProfiles) {
                if (profile[0].equals(newProfile[0])) {
                    isNewUser++;
                }
                else {
                    isOldUser++;
                }
            }
            assertEquals(isNewUser, 1);
            assertEquals(isOldUser, allProfiles.size() - 1);
            assertEquals(profileManager.getAllPlayerProfiles(filePath).size(), 4);
        }
        catch (Exception e) {
        }
    }

    @Test
    void testCreateNewProfileWithExistingUsername() throws FileNotFoundException {
        String[] newProfile = {"jordanuser", "jordanPassword", "jordanimagestring", "jordan"};
        try {
            profileManager.createNewPlayerProfile(newProfile, filePath);
        }
        catch (Exception e) {
            assertTrue(true);
            assertEquals(profileManager.getAllPlayerProfiles(filePath).size(), 3);
        }
    }

    @Test
    void testUpdatePlayerProfileWithExistingUsername() throws FileNotFoundException {
        String[] newProfile = {"jordanuser", "jordanNewPassword", "jordanNewImageString", "nadroj"};
        try {
            profileManager.updatePlayerProfile(newProfile, filePath);
        }
        catch (Exception e) {
        }
        List<String[]> allProfiles = profileManager.getAllPlayerProfiles(filePath);
        String[] updatedProfile = new String[4];
        for (String[] profile: allProfiles) {
            if (profile[0].equals(newProfile[0])) {
                updatedProfile[0] = profile[0];
                updatedProfile[1] = profile[1];
                updatedProfile[2] = profile[2];
                updatedProfile[3] = profile[3];
                break;
            }
        }
        assertEquals(updatedProfile[1], "jordanNewPassword");
        assertEquals(updatedProfile[2], "jordanNewImageString");
        assertEquals(updatedProfile[3], "nadroj");
    }

    @Test
    void testUpdatePlayerProfileWithNonexistantUsername() {
        String[] newProfile = {"newuser", "jordanNewPassword", "jordanNewImageString", "nadroj"};
        try {
            profileManager.updatePlayerProfile(newProfile, filePath);
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void testLoginPlayerProfileWithCorrectCredentials() {
        try {
            String[] profile = profileManager.loginPlayerProfile("jordanuser", "jordanpassword", filePath);
            assertEquals(profile[0], "jordanuser");
            assertEquals(profile[1], "jordanpassword");
            assertEquals(profile[2], "jordanimagestring");
            assertEquals(profile[3], "jordan");
        }
        catch (Exception e) {
        }
    }

    @Test
    void testLoginPlayerProfileWithIncorrectCredentials() {
        try {
            String[] profile = profileManager.loginPlayerProfile("jordanuser", "jordanwrongpassword", filePath);
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void testGetAllPlayerProfilesCorrectFilePath() {
        try {
            List<String[]> allProfiles = profileManager.getAllPlayerProfiles(filePath);
            assertEquals(allProfiles.size(), 3);
        }
        catch (Exception e) {
        }
    }

    @Test
    void testGetAllPlayerProfilesIncorrectFilePath() {
        try {
            List<String[]> allProfiles = profileManager.getAllPlayerProfiles("data/testdata/nonexistant.csv");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }
}
