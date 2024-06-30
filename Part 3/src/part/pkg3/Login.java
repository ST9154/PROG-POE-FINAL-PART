/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package part.pkg3;

/**
 *
 * @author gfff
 */
class Login {
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean loginUser(String username, String password) {
        // Implement your actual login logic here
        return this.username.equals(username) && this.password.equals(password);
    }
}
