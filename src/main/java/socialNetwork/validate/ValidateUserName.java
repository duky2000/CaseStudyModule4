package socialNetwork.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import socialNetwork.model.AppUser;
import socialNetwork.service.IAppUserService;
import socialNetwork.service.Implement.AppUserService;

import java.util.List;

@Service
public class ValidateUserName implements Validator {

    @Autowired
    IAppUserService iAppUserService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Iterable<AppUser> list = iAppUserService.findAll();
        AppUser appUser = (AppUser) target;
        for (AppUser a : list) {
            if (a.getUsername().equals(appUser.getUsername())) {
                errors.rejectValue("username", "same_name");
                break;
            }
        }
    }
}
