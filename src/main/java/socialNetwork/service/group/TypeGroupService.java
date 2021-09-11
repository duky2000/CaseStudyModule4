package socialNetwork.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.group.TypeGroup;
import socialNetwork.repository.group.ITypeGroupRepo;

import java.util.ArrayList;

@Service
public class TypeGroupService implements ITypeGroupService{

    @Autowired
    ITypeGroupRepo iTypeGroupRepo;

    @Override
    public ArrayList<TypeGroup> findAllType() {
        return (ArrayList<TypeGroup>) iTypeGroupRepo.findAll();
    }
}
