package socialNetwork.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.group.Member;
import socialNetwork.repository.group.IMemberRepo;

@Service
public class MemberService implements IMemberService{

    @Autowired
    IMemberRepo iMemberRepo;

    @Override
    public void save(Member member) {
        iMemberRepo.save(member);
    }
}
