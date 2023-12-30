package web.project.HouseRentalAPI.service;

import web.project.HouseRentalAPI.models.notice;

import java.util.List;
import java.util.Optional;

public interface noticeService {

    Optional<notice> getnotice(String id);

    Object addNotice(notice user);

    Object getNoticeById(String id);

    List<notice> getAllnotice();

    Object inactiveUserById(String id);

    Object deleteNotice(String id);
}
