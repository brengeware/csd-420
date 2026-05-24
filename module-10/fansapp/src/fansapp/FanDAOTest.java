//testing

package fansapp;

public class FanDAOTest {
    
    public static void main(String[] args) {
        FanDAO dao = new FanDAO();
        
        System.out.println("Test 1 - Get existing fan (ID 1):");
        Fan fan = dao.getFanByID(1);
        if (fan != null) {
            System.out.println("PASS " + fan.getFirstname() + " " + fan.getLastname() );
        } else {
            System.out.println("FAIL: Fan not found");
        }
        
        System.out.println("Test 2 - Get non-existent fan (ID 999): ");
        Fan missing = dao.getFanByID(999);
        if (missing == null) {
            System.out.println("PASS: Correctly returned null");
        } else {
            System.out.println("FAIL: Should've returned null.");
        }
        
        System.out.println("Test 3 - Update fan (ID 1):");
        Fan updated = new Fan(1, "Brengeware", "Smithtonson", "Cubs?");
        boolean result = dao.updateFan(updated);
        System.out.println(result ? "PASSED - Successfully updated" : "FAILED. Try again.");
        
        //verify the update stuck
        System.out.println("Test 4 - Verification of update");
        Fan verify = dao.getFanByID(1);
        if (verify != null && verify.getFirstname().equals("Brengeware")
                && verify.getFavoriteteam().equals("Cubs?")) {
            System.out.println("PASSED: Data matches.");
        } else {
            System.out.println("FAILED MISERABLY: The data don't match.");
        }
    }
}