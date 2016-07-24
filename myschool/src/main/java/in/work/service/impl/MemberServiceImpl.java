package in.work.service.impl;

import java.util.List;
import javax.inject.Inject;

import in.work.dao.MemberDao;
import in.work.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class MemberServiceImpl {
  private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
  
  @Inject
  private MemberDao memberDao;
  
  @RequestMapping(value = "/members", method = RequestMethod.POST)
  @ResponseBody
  public Member create(Member member) {
    logger.debug("Creating member with name : " + member.getName());
    try {
      memberDao.create(member);
      logger.debug("Create successful for Member : " + member.getId());
    } catch (Exception exc) {
    }
    return null;
  }
  
  @RequestMapping(value = "/member/{id}", method = RequestMethod.PUT)
  public Member save(@PathVariable Long id, Member member) {
    logger.debug("Updating member with name : " + member.getName());
    try {
      Member savedMemberModel = memberDao.edit(member);
      logger.debug("Update successful for Member : " + savedMemberModel.getId());
    } catch (Exception exc) {
      logger.error("Save error ", exc);
    }
    return null;
  }
  
  @RequestMapping(value = "/member/{id}", method = RequestMethod.GET)
  public Member getByKey(@PathVariable Long id) {
    logger.debug("Finding Member for key : " + id);
    try {
      Member member = memberDao.findByKey(id);
      logger.debug("Found member : " + member);
      return member;
    } catch (Exception exc) {
      logger.error("Search error ", exc);
    }
    return null;
  }
  
  @RequestMapping(value = "/members", method = RequestMethod.GET)
  public List<Member> findAll() {
    logger.debug("Finding all Member");
    try {
      List<Member> allMembers = memberDao.findAll();
      return allMembers;
    } catch (Exception exc) {
      logger.error("Findall error ", exc);
    }
    return null;
  }
  
  @RequestMapping(value = "/member/{id}", method = RequestMethod.DELETE)
  public void remove(@PathVariable Long id) {
    logger.debug("Deleting Member for kye " + id);
    try {
      memberDao.removeByKeys(id);
      logger.debug("Deletion successful");
    } catch (Exception exc) {
      logger.error("Delete error ", exc);
    }
  }
}
