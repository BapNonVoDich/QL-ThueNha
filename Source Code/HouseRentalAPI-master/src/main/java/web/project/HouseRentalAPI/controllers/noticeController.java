package web.project.HouseRentalAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.project.HouseRentalAPI.models.notice;
import web.project.HouseRentalAPI.service.noticeService;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
@CrossOrigin
public class noticeController {
//    @Autowired
//    private noticeRepository noticeRepository;
//    @Autowired
//    public noticeController(noticeRepository noticeRepository){
//        this.noticeRepository= noticeRepository;
//    }

    @Autowired
    noticeService noticeService;

    @GetMapping("/2/{id}")
    public ResponseEntity<?> getNoticeById2(@PathVariable String id) {
        return new ResponseEntity<>(noticeService.getnotice(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createNotice(@RequestBody notice user) {
        return new ResponseEntity<>(noticeService.addNotice(user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNoticeById(@PathVariable String id) {
//        Optional<notice> noticeOptional = noticeRepository.findById(id);
//        if (noticeOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notice not found");
//
        return new ResponseEntity<>(noticeService.getNoticeById(id), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<notice>> getAllNotices() {
//        List<notice> notices = noticeRepository.findAll();
//        if (notices.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
        return new ResponseEntity<>(noticeService.getAllnotice(), HttpStatus.OK);
    }

    @GetMapping("/inactive/{id}")
    public ResponseEntity<?> inactiveUserById(@PathVariable String id) {
//        if(!noticeRepository.existsById(id)){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notice not found");
//        }
//        Optional<notice> noticeOptional = noticeRepository.findById(id);
//        if (noticeOptional.isPresent()) {
//            notice updateNotice = noticeOptional.get();
//            updateNotice.setActive(false);
//            noticeRepository.save(updateNotice);
//            return ResponseEntity.ok("Notice Inactivated.");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tenant not found");
//        }
        return new ResponseEntity<>(noticeService.inactiveUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
//        if(!noticeRepository.existsById(id)){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notice not found");
//        }
//
//        noticeRepository.deleteById(id);
        return new ResponseEntity<>(noticeService.deleteNotice(id), HttpStatus.OK);
    }

}
