//package socialNetwork.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import socialNetwork.service.user.IUserService;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserControllerAPI {
//    @Autowired
//    IUserService iUserService;
//    @GetMapping("/{name}")
//    public ResponseEntity findByName(@PathVariable String name) {
//        return new ResponseEntity(iUserService.findAllByUsernameContaining(name), HttpStatus.OK);
//    }
//}
