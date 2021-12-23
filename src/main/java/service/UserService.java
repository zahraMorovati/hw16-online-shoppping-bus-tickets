package service;

import dao.DaoUser;
import model.User;
import view.GetValidData;

import java.util.List;

public class UserService {

    static DaoUser daoUser = new DaoUser();

    public User getUserByUsernameAndPassword(String username,String password){
        List<User> results = daoUser.findByUsernameAndPassword(username, password);
        if(results.isEmpty())
            throw new RuntimeException("wrong username or password!");
        else return results.get(0);
    }

    public static boolean isValidUsername(String username){
        if(!daoUser.isUsernameDupplicated(username))
            return true;
        else return false;
    }

    public User getUserInfo(){
         String name= GetValidData.getValidName("name: ");
         String family=GetValidData.getValidName("family: ");
         double balance=GetValidData.getValidDouble("balance: ");
         String email=GetValidData.getValidString("email: ");
         String userName=GetValidData.getValidUsername("username: ");
         String password=GetValidData.getValidString("password: ");

         User user = User.UserBuilder.anUser()
                 .setName(name)
                 .setFamily(family)
                 .setBalance(balance)
                 .setEmail(email)
                 .setUserName(userName)
                 .setPassword(password).build();

         return user;

    }

    public void save(User user){
        daoUser.save(user);
    }

    public void update(User user){
        daoUser.update(user);
    }


    public void increaseBalance(int userId,double amount){
        daoUser.increaseBalance(amount,userId);
    }

}
