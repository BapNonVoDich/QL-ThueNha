package web.project.HouseRentalAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.project.HouseRentalAPI.models.notice;
import web.project.HouseRentalAPI.repositories.noticeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class noticeServiceImpl implements noticeService {
    @Autowired
    noticeRepository noticeRepository;

    @Override
    public Optional<notice> getnotice(String id) {
        return noticeRepository.findById(id);
    }

    @Override
    public String addNotice(notice notice) {
        noticeRepository.save(notice);
        return new String("Thành công");
    }

    @Override
    public Optional<notice> getNoticeById(String id) {
        return noticeRepository.findById(id);
    }

    @Override
    public List<notice> getAllnotice() {
        return noticeRepository.findAll();
    }

    @Override
    public String inactiveUserById(String id) {
        if (!noticeRepository.existsById(id)) {
            return "Notice not found";
        }
        Optional<notice> noticeOptional = noticeRepository.findById(id);
        if (noticeOptional.isPresent()) {
            notice updateNotice = noticeOptional.get();
            updateNotice.setActive(false);
            noticeRepository.save(updateNotice);
            return "Notice Inactivated.";
        } else {
            return "Tenant not found";
        }
    }

    @Override
    public String deleteNotice(String id) {
        if (!noticeRepository.existsById(id)) {
            return "Notice not found";
        }
        noticeRepository.deleteById(id);
        return "Notice Deleted";
    }
}
