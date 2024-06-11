package LiqueurDepartment.model;

public class Admin {
    private static final String ADMIN_ID = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    public static boolean isAdmin(String id, String password) {
        return ADMIN_ID.equals(id) && ADMIN_PASSWORD.equals(password);
    }
}
