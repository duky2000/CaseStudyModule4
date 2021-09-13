//package socialNetwork.validate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//import socialNetwork.model.User;
//import socialNetwork.service.IUserService;
//
//import java.util.ArrayList;
//
//@Service
//public class ValidateUserName implements Validator {
//
//    @Autowired
//    IUserService iAppUserService;
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return false;
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        ArrayList<User> list = (ArrayList<User>) iAppUserService.findAll();
//        User appUser = (User) target;
//        for (User a : list) {
//            if (a.getUsername().equals(appUser.getUsername())) {
//                errors.rejectValue("username", "same_name");
//                break;
//            } else if (a.getPhone().equals(appUser.getPhone())) {
//                errors.rejectValue("phone", "same_phone_number");
//                break;
//            }
//        }
//
//
//    }
//}
