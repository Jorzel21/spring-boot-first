package jorzel.jorzelweb.controller;

import jorzel.jorzelweb.handler.BusinessException;
import jorzel.jorzelweb.model.User;
import jorzel.jorzelweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping()
    public List<User> getUsers() {
        return repository.listAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return repository.finById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        repository.remove(id);
    }

    @PostMapping()
    public void store(@RequestBody User user) {
        repository.save(user);
    }

    @PutMapping()
    public void update(@RequestBody User user) {
        if(user.getLogin() == null)
            throw new BusinessException("O campo login é obrigatório.");

        repository.update(user);
    }
}
